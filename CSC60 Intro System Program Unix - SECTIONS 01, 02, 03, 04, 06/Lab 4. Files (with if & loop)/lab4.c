/*-------------------------------------------------*/
/* Xavier Howell                                   */
/* Lab 4                                           */
/* Figure the perimeter and area of a polygon      */
/* surrounded by a circle                          */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define FILE_IN “lab4.dat”
#define FILE_OUT “lab4.out”

int main(void)
{
	double dr, ds, dp, da;
	FILE * Inf;
	FILE * Outf;

	Inf = fopen(FILE_IN, "r");
	if (Inf == NULL)
	{
		printf("Error on opening the data file\n");
	}

	Outf = fopen(FILE_OUT, "w");	
	if (Outf == NULL)
	{
		printf("Error on opening the output file\n");

	}


      /* All these fprintf statements use the file pointer
         of answer_file.  Feel free to use your own variable
         or the one I used.  You may delete this comment. */

	printf(Outf,"\nXavier Howell.  Lab 4.\n\n");
	printf(Outf,"            Number      Perimeter      Area Of  \n");
	printf(Outf," Radius    Of Sides    Of Polygon      Polygon  \n");
	printf(Outf,"--------   --------   ------------   ----------- \n");
	
	while((fscanf(In, "%f%f", &n, &r)) == 2 );
	{
		dp = 2 * ds * dr * sin(M_PI / ds);
		da = 1/2 * ds dr * dr * sin(2 * M_PI) / ds;		
		fprintf(Outf," %f%f%f%f", dr, ds, dp, da);
	}
	
	fclose(Inf);
	fclose(outf);
	return EXIT_SUCCESS;
}

		
		
		



/*------------------------------------------------------------------*/
