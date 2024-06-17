//Xavier Howell
//2.15.2019
//Mod1 Final 

import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Module1Program
{	
	public static void main(String[] args)
	{
		try
		{
			Scanner input = new Scanner( new File("data.txt"));
			int [] dataArray = new int [8];
			int count = 0;
			
			while(input.hasNext())
			{
				if(input.hasNextInt())
				{
					int number = input.nextInt();
					if(count == dataArray.length)
					{
					dataArray = doubleAndCopy(dataArray);	
					}
					dataArray[count] = number;
					count++;
				}
				else
				{
					input.next();
				}
			}

			Arrays.sort(dataArray,0,count);
			for (int x=0; x<count; x++)
			{
				System.out.println(dataArray[x]);
			}					
		}
		
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
	}
	public static int[] doubleAndCopy(int[] dataArray) 
	{
		int[] doubleArray = new int[2*dataArray.length];					
		for ( int i = 0; i < dataArray.length; i++ )
			doubleArray[i] = dataArray[i];
			dataArray = doubleArray;
		return doubleArray;					
	}
}