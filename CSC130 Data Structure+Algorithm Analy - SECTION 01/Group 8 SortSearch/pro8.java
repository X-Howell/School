//Xavier Howell
//CSC130
//Group8
//SortSearch

import java.util.Scanner;

public class pro8 
{
	public static int[][] a= {{5,3,2,16},{9,8,10,17},{4,7,11,18},{2,5,9,12},{7,9,4,10}};
	
	int[][] array2(int a[][]) 
	{
		int[][] arr=new int[a.length][a[0].length];
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				arr[i][j]=a[i][j];
			}
		}
		return arr;
	}

	void print(int a[][])
	{
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}


	void bubble_sort(int arr[][])
	{
		arr=array2(arr);
		System.out.println("After Bubble Sort (Ascending Order");
		int n = arr.length;
		for (int i = 0; i < n-1; i++)
		{
			for (int j = 0; j < n-i-1; j++)
			{
				if (arr[j][0] > arr[j+1][0])
				{
					int temp = arr[j][0];
					arr[j][0] = arr[j+1][0];
					arr[j+1][0] = temp;
				}
			}
		}
		print(arr);
	}

	void selection_sort(int arr[][])
	{
		arr=array2(arr);
		System.out.println("After Selection Sort (Decending Order)");
		int n = arr.length;
		for (int i = 0; i < n-1; i++)
		{
			int min_idx = i;
			for (int j = i+1; j < n; j++)
			{
				if (arr[j][1] > arr[min_idx][1])
				min_idx = j; 
			}		
			int temp = arr[min_idx][1];
			arr[min_idx][1] = arr[i][1];
			arr[i][1] = temp;
		}	  
		print(arr);
	}

	void shell_sort(int arr[][])
	{
		arr=array2(arr);
		System.out.println("After Shell Sort (Ascending Order)");
		int n = arr.length;		
		for (int gap = n/2; gap > 0; gap /= 2)
		{
			for (int i = gap; i < n; i += 1)
			{
				int temp = arr[i][2];
				int j;
				for (j = i; j >= gap && arr[j - gap][2] > temp; j -= gap)
				{
					arr[j][2] = arr[j - gap][2];
				}
					arr[j][2] = temp;
			}
		}
		print(arr);
		
	}

	void insertion_sort(int arr[][])
	{
		System.out.println("After Insertion Sort (Ascending Order)");
		int n = arr[4].length;
		for (int i = 1; i < n; ++i)
		{
			int key = arr[4][i];
			int j = i - 1;
			while (j >= 0 && arr[4][j] > key)
			{
				arr[4][j + 1] = arr[4][j];
				j = j - 1;
			}
			arr[4][j + 1] = key;
		}
		print(arr);
	}
	
	int b_search(int x)
	{
		int l = 0, r = a[4].length - 1;
		while (l <= r) 
		{
			int m = l + (r - l) / 2;
			if (a[4][m] == x)
			return m;
			if (a[4][m] < x)
			l = m + 1;
			else r = m - 1;
		}
		return -1;
	}

	public static void main(String[] args)
	{
		System.out.println("Original array");
		new pro8().print(a);
		System.out.println();
		new pro8().bubble_sort(a);
		System.out.println();
		new pro8().selection_sort(a);
		System.out.println();
		new pro8().shell_sort(a);
		System.out.println();
		new pro8().insertion_sort(a);
		System.out.println();
		System.out.println("What number are you searching for in the 5th row? ");
		int n;
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		int in=new pro8().b_search(n);
		for(int i=0;i<a.length;i++) 
		{
			System.out.println(a[i][in]);
		}
	}
}