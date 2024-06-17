/*-------------------------------------------------*/
/* Xavier Howell                                   */
/* Lab 4                                           */
/* Figure the perimeter and area of a polygon      */
/* surrounded by a circle                          */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define FILE_IN "lab4.dat"
#define FILE_OUT "lab4.out"

int main(void)
{
	double dr, ds, dp, da;
	int count;
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

	fprintf(Outf,"\nXavier Howell.  Lab 4.\n\n");
	fprintf(Outf,"            Number      Perimeter      Area Of  \n");
	fprintf(Outf," Radius    Of Sides    Of Polygon      Polygon  \n");
	fprintf(Outf,"--------   --------   ------------   ----------- \n");
	
	
	while((fscanf(Inf,"%lf%lf", &dr, &ds)) == 2);
	{
		dp = 2 * ds * dr * sin(M_PI / ds);
		da = .5  * ds * (dr* dr) * (sin((2*M_PI)/ds));		
		fprintf(Outf,"%7.2lf%11.2lf%15.4lf%14.4f\n", dr,ds,dp,da);
	}
	
	fclose(Inf);
	fclose(Outf);
	return EXIT_SUCCESS;
}

/*------------------------------------------------------------------*/
