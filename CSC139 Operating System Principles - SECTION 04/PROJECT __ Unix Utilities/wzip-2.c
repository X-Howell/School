//Xavier Howell
//Project:Unix Utilities
//wzip.c

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) 
{
    if (argc < 2) 
    {
        printf("wzip: file1 [file2 ...]\n");
        return 1;
    }

    //Initiate varables for characters and count
    char prev_char = '\0';
    char current_char;
    int count = 0;

    for (int i = 1; i < argc; i++) 
    {
        //Open files in read mode
        FILE *fp = fopen(argv[i], "r");
        if (!fp) 
        {
            perror("Error opening file");
            return 1;
        }

        while ((current_char = fgetc(fp)) != EOF) 
        {
            if (current_char == prev_char) 
            {
                count++;
            } else {
                if (count > 0) 
                {
                    //Print count as a 4-byte integer in binary format
                    fwrite(&count, sizeof(count), 1, stdout);
                    //Print the character
                    fputc(prev_char, stdout);
                }
                //Rerun
                count = 1;
                prev_char = current_char;
            }
        }

        fclose(fp);
    }

    //EOR
    if (count > 0) 
    {
        fwrite(&count, sizeof(count), 1, stdout);
        fputc(prev_char, stdout);
    }
    return 0;
}