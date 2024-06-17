//Xavier Howell
//Project:Unix Utilities
//wunzip

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) 
{
    if (argc < 2) 
    {
        printf("wunzip: file1 [file2 ...]\n");
        return 1;
    }

    //Default to standard output
    FILE *fp = stdout;
    for (int i = 1; i < argc; i++) 
    {
        //Open files in binary mode
        FILE *compressed_file = fopen(argv[i], "rb");
        if (!compressed_file) 
        {
            perror("Error opening file");
            return 1;
        }
        while (1) 
        {
            //Count as a 4-byte integer
            unsigned int count;
            size_t bytes_read = fread(&count, sizeof(count), 1, compressed_file);

            if (bytes_read != 1) 
            {
                if (feof(compressed_file)) 
                {
                    break; //End of file
                } else {
                    perror("Error reading run length");
                    return 1;
                }
            }

            //Character (1-byte ASCII)
            char character;
            bytes_read = fread(&character, sizeof(character), 1, compressed_file);

            if (bytes_read != 1) 
            {
                perror("Error reading character");
                return 1;
            }
            for (unsigned int j = 0; j < count; j++) 
            {
                //Write character to outputfile
                fputc(character, fp);
            }
        }
        fclose(compressed_file);
    }
    return 0;
}
