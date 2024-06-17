/**
*Xavier Howell
*March 6, 2019
*Tic Tac Toe Board
*Mod 2 
**/

public class TTTBoardF
{
	int size;
	private char board[][];
	public static final int DEAFULT_SIZE = 3;
	
	public TTTBoard() 
	{		
		this(DEAFULT_SIZE);	
	}
	
	public TTTBoard(int size) 
	{
		if (size < 1) 
		{ 
			throw new IllegalArgumentException("Standard size board of 1 or higher ");
		}
		board = new char[size][size];	
	}
	
	public char get(int r, int c) 
	{		
		if ((r > (size - 1)) || ((c > (size - 1))))
		{		
			throw new IndexOutOfBoundsException("Specified index is not in the specified parameter of TTTBoard");
		}		
		return board[r][c];
	}
	
	public void set(int r, int c, char ch) 
	{		
		if ((r > (size-1)) || (size < 0))
		{			
			throw new IndexOutOfBoundsException("Specified index in not in the specified parameter of TTTBoardd");			
		}		
		board[r][c] = ch;	
	}
	
	public int size () 
	{		
		return size;		
	}

	public char winner()
	{		
		int count = 0;	       
		//Horizontal check	       
		for(int i=0;i< size;i++)
		{	    	   
			count=0;
			for(int j=0; j< size;j++) 
			{        	   
				if(board[i][j]=='O')
				{
					count++;
				}
			}
			
			if(count == size) 
			{
				return 'O';
			}
		}

		for(int i = 0 ; i< size;i++) 
		{
			count=0;
			for(int j=0; j< size;j++)
			{        	   
				if(board[i][j]=='X') 
				{
					count++;
				}
			}
		
			if(count == size) 
			{
				return 'X';
			}
		}

		//column check
		for(int i=0; i < size;i++) 
		{
			count=0;                   
			for(int j=0;j< size;j++) 
			{
				if(board[j][i]=='O') 
				{
					count++;
				}
			}
			
			if(count == size) 
			{
				return 'O';
			}
		}

		for(int i = 0;i < size;i++)
		{
			count=0;
			for(int j=0;j < size;j++) 
			{
				if(board[j][i]=='X') 
				{
					count++;
				}
			}
			if(count == size) 
			{
				return 'X';
			}
		}

		//diagonal check
		count=0;
		for(int i = 0;i < size;i++) 
		{            	   
			if(board[i][i]=='O') 
			{
				count++;
			}
		}
		
		if(count == size)
		{
			return 'O';
		}               
		count=0;
		for(int i=0;i < size;i++) 
		{
			if(board[i][i]=='X') 
			{
				count++;
			}
		}
		if(count == size) 
		{
			return 'X';
		}
		return ' ';
	}
}

public String toString()
{
	for(int z=0; z<2*size-1; z++)
	{
		if(z % 2 == 0)
		{
			for(int x=0; x<2*size-1;x++)
			{
				if(x % 2 == 0)
				{
					System.out.print(" ");
				}
				else
				{
					System.out.print("|");
				}
			}
			System.out.println();
		}
		else
		{	
			for(int x=0; x<2*size-1;x++)
			{
				if(x % 2 == 0)
				{
					System.out.print("-");
				}		
				else
				{
					System.out.print("+");
				}
			}
			System.out.println();
		}
	}
}