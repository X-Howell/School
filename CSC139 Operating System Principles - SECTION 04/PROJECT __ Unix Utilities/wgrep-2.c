//Xavier Howell
//Project:Unix Utilities
//wgrep

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) 
{
    if (argc < 2) 
    {
        printf("wgrep: searchterm [file ...]\n");
        return 1;
    }

    //Get search term
    char *search_term = argv[1];

    //Initialize buffer for getline()
    char *line = NULL;
    size_t line_size = 0;

    //Iterate through each file or read from stdin
    for (int i = 2; i < argc; i++) 
    {
        //Open files in read mode
        FILE *fp = fopen(argv[i], "r");
        if (fp == NULL) 
        {
            printf("wgrep: cannot open file\n");
            return 1;
        }

        //Read lines from the file and search for the search term
        while (getline(&line, &line_size, fp) != -1) 
        {
            if (strstr(line, search_term)) 
            {
                printf("%s", line);
            }
        }

        //Close the file
        fclose(fp);
    }

    //If no files are specified read from stdin
    if (argc == 2) 
    {
        while (getline(&line, &line_size, stdin) != -1) 
        {
            if (strstr(line, search_term)) 
            {
                printf("%s", line);
            }
        }
    }

    //Free allocated memory
    free(line);

    return 0;
}
