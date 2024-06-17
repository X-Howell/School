/*Xavier Howell
CSC139 Project-Unix Shell
Ostep-Projects Processes-Shell
Wish.c*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

#define VECTOR_CAP 10

// Define a struct for a dynamic string vector
typedef struct Vector
{
    char** data;
    int capacity;
    int size;
} 

Vector;
Vector create_vector() // Function to create an empty vector
{
    Vector v;
    v.data = (char**)malloc(VECTOR_CAP * sizeof(char*));
    v.capacity = VECTOR_CAP;
    v.size = 0;
    return v;
}

void push(Vector* v, char* val) // Function to add a string to the vector
{
    if (v->capacity == v->size) 
    {
        // If vector is full, double its capacity
        char** new_data = (char**)malloc((v->capacity << 1) * sizeof(char*));
        int x = v->size;
        for (int i = 0; i < x; i++)
        {
            new_data[i] = v->data[i];
        }
        free(v->data);
        v->data = new_data;
        v->capacity <<= 1;
    }
    v->data[v->size++] = val;
}

void pop(Vector* v) // Function to remove the last element from the vector
{
    if (v->size > 0)
    {
        v->size--;
    }
}

char* get(Vector* v, int index) // Function to get the element at a specific index in the vector
{

    if (index < 0 || index >= v->size)
    {
        return NULL;
    }
    return v->data[index];
}

int search_key(Vector* v, char* key) // Function to search for a key in the vector and return its index
{
    int x = v->size;
    for (int i = 0; i < x; i++) 
    {
        if (strcmp(key, v->data[i]) == 0)
            return i;
    }
    return -1;
}

void destroy(Vector* v) // Function to free the memory used by the vector
{
    free(v->data);
}

/* Global Vector and functions to parse a command line
and run checks, if not throw error.
*/
Vector PATH;
Vector parse (char* line);
int Delimiter (char);
int Same_File (int, int);
int Redirection (Vector tokens);
int Ampersand (Vector tokens);
char* concat (char* a, char* b);
int RunC (Vector tokens);
void showError ();

int main (int argc, char* argv[])
{

    FILE* input_file = NULL;
    for (int i = 1; i < argc; i++) 
    {
        FILE* cur_file = fopen(argv[i], "r");
        if (cur_file == NULL) 
        {
            showError();
            exit(1);
        }

        if (input_file == NULL) 
        {
            input_file = cur_file;
        } else {
            if (!Same_File(fileno(input_file), fileno(cur_file))) 
            {
                showError();
                exit(1);
            }
        }
    }

    if (input_file != NULL) stdin = fdopen(fileno(input_file), "r");
    push(&PATH, "/bin");

    while (1)
    {
        if (input_file == NULL) printf("wish> ");
        char* line = NULL;
        size_t n = 0;
        int len = getline(&line, &n, stdin);
        if (len == -1) 
        {
            if (input_file == NULL) continue;
            break;
        }

        line[len-1] = '\0';
        Vector tokens = parse(line);
        if (!Ampersand(tokens)) // Check for '&'
        {
            continue;
        }

        int commandsCount = 1;
        for (int i = 0; i < tokens.size; i++) 
        {
            if (strcmp("&", get(&tokens, i)) == 0) commandsCount++;
        }

        Vector command = create_vector();
        int processIds[commandsCount];
        int sz = 0;
        for (int i = 0; i < tokens.size; i++) 
        {
            if (strcmp("&", get(&tokens, i)) != 0) 
            {
                push(&command, get(&tokens, i));
            } else {
                continue;
            }
            if (i == tokens.size - 1 || strcmp("&", get(&tokens, i+1)) == 0) 
            {
                if (!Redirection(command)) 
                {
                    showError();
                    break;
                }
                int res = RunC(command);
                if (res != -1) processIds[sz++] = res;
                command = create_vector();
            }
        }
        // Execute commands
        for (int i = 0; i < sz; i++) waitpid(processIds[i], NULL, 0);
        free(line);
        destroy(&tokens);
        destroy(&command);
    }

return 0;
}

Vector parse (char* line) 
{
    Vector ans = create_vector();
    int n = strlen(line);
    char* s = NULL;
    int start = -1;
    for (int i = 0; i < n; i++) 
    {
        if (line[i] == '>') 
        {
            push(&ans, ">");
            continue;
        }
        if (line[i] == '&') 
        {
            push(&ans, "&");
            continue;
        }
        if (!Delimiter(line[i])) 
        {
            if (i == 0 || Delimiter(line[i-1])) start = i;
            if (i == n-1 || Delimiter(line[i+1])) 
            {
                s = strndup(line+start, i-start+1);
                push(&ans, s);
            }
        }
    }
    return ans;
}

int Delimiter (char c) 
{
    return (c == ' ') || (c == '\t') || (c == '>') || (c == '&');
}

int Same_File (int f1, int f2) 
{
    struct stat stat1, stat2;
    if(fstat(f1, &stat1) < 0) return -1;
    if(fstat(f2, &stat2) < 0) return -1;
    return (stat1.st_dev == stat2.st_dev) && (stat1.st_ino == stat2.st_ino);
}

int Redirection (Vector tokens) 
{
    int n = tokens.size;
    for (int i = 0; i < n; i++) 
    {
        if (strcmp(">", get(&tokens, i)) == 0) 
        {
            if (i == 0 || i == n-1 || n-1-i > 1) return 0;
            if (i != n-1 && strcmp(">", get(&tokens, i+1)) == 0) return 0;
        }
    }
    return 1;
}

int Ampersand (Vector tokens) 
{
    int n = tokens.size;
    for (int i = 0; i < n; i++) 
    {
        if (strcmp("&", get(&tokens, i)) == 0) 
        {
            if (i == 0) return 0;
            if (i != n-1 && strcmp("&", get(&tokens, i+1)) == 0) return 0;
        }
    }
    return 1;
}

char* concat (char* a, char* b) 
{
    char* ans = (char*) malloc((strlen(a) + strlen(b) + 1) * sizeof(char));
    strcpy(ans, a);
    strcat(ans, b);
    return ans;
}

int RunC (Vector tokens) 
{
    char* command = get(&tokens, 0);
    if (strcmp(command, "exit") == 0) 
    {
        if (tokens.size != 1) showError();
        else exit(0);
        return -1;
    }
    if (strcmp(command, "cd") == 0) 
    {
        if (tokens.size != 2) showError();
        else chdir(get(&tokens, 1));
        return -1;
    }
    if (tokens.size >= 1 && strcmp("path", command) == 0) 
    {
        Vector set = create_vector();
        for (int i = 1; i < tokens.size; i++) push(&set, get(&tokens, i));
        PATH = set;
        return -1;
    }
    int y = search_key(&tokens, ">");
    if (y == -1) y = tokens.size;
    for (int i = 0; i < PATH.size; i++) 
    {
        char* p = concat(get(&PATH, i), concat("/", command));
        if (access(p, X_OK) == 0) 
        {
            char* argv[y+1];
            for (int i = 0; i < y; i++) argv[i] = get(&tokens, i);
            argv[y] = NULL;
            
            int i = fork();
            if (i == 0) 
            {
                if (y != tokens.size) 
                {
                    close(STDOUT_FILENO);
                    open(get(&tokens, tokens.size - 1), O_CREAT | O_WRONLY | O_TRUNC, S_IRWXU);
                }
                execv(p, argv);
            }
            return i;
        }
    }
    showError();
    return -1;
}

void showError () 
{
    char error_message[30] = "An error has occurred\n";
    write(STDERR_FILENO, error_message, strlen(error_message)); 
}