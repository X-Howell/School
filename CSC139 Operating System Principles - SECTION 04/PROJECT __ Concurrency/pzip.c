//Xavier Howell
//CSC139
//Concurrency
//Parallel Zip: pzip.c
/*Project parallizes compression and 
prints outputs of results for each thread*/ 

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>

#define CHUNK_SIZE 1024

//Structure to hold information for each thread
typedef struct
{
    char *input;
    size_t begin;
    size_t end;
} ThreadData;

//Mutex for synchronization
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void *compress_chunk(void *data)
{
    ThreadData *threadData = (ThreadData *)data;

    //Allocate memory for the compressed chunk
    char *compressed_chunk = malloc((threadData->end - threadData->begin) * 5);

    char prev_char = '\0';
    int count = 0;
    size_t compressed_index = 0;

    for (size_t i = threadData->begin; i < threadData->end; i++)
    {
        char current_char = threadData->input[i];

        if (current_char == prev_char)
        {
            count++;
        } else {
            if (count > 0)
            {
                //Convert the run length to ASCII characters
                char count_str[5];
                snprintf(count_str, sizeof(count_str), "%d", count);

                //Add the run length as ASCII characters
                strcpy(&compressed_chunk[compressed_index], count_str);
                compressed_index += strlen(count_str);

                //Add the character
                compressed_chunk[compressed_index++] = prev_char;
            }

            //Reset count for the next run
            count = 1;
            //Update the previous character
            prev_char = current_char;
        }
    }

    //Handle last run
    if (count > 0)
    {
        char count_str[5];
        snprintf(count_str, sizeof(count_str), "%d", count);

        strcpy(&compressed_chunk[compressed_index], count_str);
        compressed_index += strlen(count_str);

        compressed_chunk[compressed_index++] = prev_char;
    }

    //Lock
    pthread_mutex_lock(&mutex);
    //Print compressed chunk
    for (size_t i = 0; i < compressed_index; i++)
    {
        printf("%c", compressed_chunk[i]);
    }
    //Unlock
    pthread_mutex_unlock(&mutex);

    //Free
    free(compressed_chunk);

    pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        perror("wzip: file1 [file2 ...]\n");
        return 1;
    }

    //Determine the number of available processors
    int num_processors = sysconf(_SC_NPROCESSORS_ONLN);
    if (num_processors <= 0)
    {
        perror("Error determining the number of processors");
        return 1;
    }

    //Create an array of threads
    pthread_t *threads = malloc(num_processors * sizeof(pthread_t));

    for (int i = 0; i < argc - 1; i++)
    {
        //Open the file and determine its size
        int fd = open(argv[i + 1], O_RDONLY);
        if (fd == -1)
        {
            perror("Error opening file");
            return 1;
        }

        struct stat sb;
        if (fstat(fd, &sb) == -1)
        {
            perror("Error getting file size");
            return 1;
        }

        char *file_data = mmap(NULL, sb.st_size, PROT_READ, MAP_PRIVATE, fd, 0);
        if (file_data == MAP_FAILED)
        {
            perror("Error mapping file to memory");
            return 1;
        }

        //Divide the file into chunks
        size_t chunk_size = sb.st_size / num_processors;

        for (int j = 0; j < num_processors; j++)
        {
            ThreadData *threadData = malloc(sizeof(ThreadData));
            threadData->input = file_data;
            threadData->begin = j * chunk_size;
            threadData->end = (j == num_processors - 1) ? sb.st_size : (j + 1) * chunk_size;

            //Create threads and assign tasks
            pthread_create(&threads[j], NULL, compress_chunk, (void *)threadData);
        }

        //Wait for threads to finish
        for (int j = 0; j < num_processors; j++)
        {
            pthread_join(threads[j], NULL);
        }

        //Cleanup memory mapping
        munmap(file_data, sb.st_size);
        close(fd);
    }

    free(threads);

    return 0;
}