//Xavier Howell
//Project:Unix Utilities
//wcat

#include <stdio.h>
#include <stdlib.h>
#define BUFFER_SIZE 1000
 
int main (int argc, char *argv[])
{
for (int i=1; i<argc;i++)
	{
	//Open files in read mode 
	FILE *fp = fopen(argv[i], "r");
	
	//If unspecified file throw error
	if (fp == NULL)
		{
		    printf("wcat: cannot open file\n");
		    return 1; 
		}
	//Read line by line and print it 	
	else
		{
			char buffer[BUFFER_SIZE]; //
			fgets(buffer,BUFFER_SIZE,fp);

			//Print till end eof		
			while (!feof(fp))
			{
				printf("%s", buffer);
				fgets(buffer,BUFFER_SIZE,fp);
			}
		
		//Close file
		fclose(fp);
		}
	}
return 0;
}