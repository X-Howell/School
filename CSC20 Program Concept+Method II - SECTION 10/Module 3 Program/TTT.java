/**
 * Xavier Howell
 * 4/17/19
 * TTT.java Final
 */

import java.util.*;

public class TTT
{

	public static void main(String[] args)
	{
		
		System.out.println("   ***** Welcome to my TicTacToe Board game! *****");
		System.out.print("Please enter the Dimensions for the Board: \n\n");		
		Scanner console = new Scanner(System.in);	
		int n = console.nextInt();		
		TTTBoard newBoard = new TTTBoard(n);				
		System.out.println("\nPlayer 1 = X (First)     Player 2 = O (Second) ");
		System.out.println("\nWould you like to be Player 1?(yes / no)\n" );
											
		while(console.hasNext())
		{
					
			String word = console.next();	
				if(word.contentEquals("yes"))
				{					
					do
					{	
						
						System.out.println(" ");						
						System.out.println("Please enter the row");						
						int r = console.nextInt();						
						System.out.println("Please enter the column");						
						int c = console.nextInt();
												
						while(newBoard.get(r, c) != ' ')
						{
							System.out.println("\nSpot is already filled, please choose a valid move\n");
							System.out.println(" ");							
							System.out.println("Please enter the row");							
							r = console.nextInt();							
							System.out.println("Please enter the column");						
							c = console.nextInt();						
						}
						
						if(newBoard.get(r, c) == ' ') 
						{
							newBoard.set(r , c , 'X' );
							System.out.println(" ");
							System.out.println(newBoard);
						}
						
						if(newBoard.winner()!= 'X') 
						{
							System.out.println("\nThe Computer made the follwing move, Your turn !\n");
							TTTAI.move(newBoard,'O');
							System.out.print(newBoard);						
						}					
		
				}while(newBoard.winner() == ' ');
									
		}else if (word.contentEquals("no"))
		{
			
			do 
			{
					
				if(newBoard.winner()!= 'X') 
				{
					System.out.println("\nThe Computer made the follwing move, Your turn again!\n");
					TTTAI.move(newBoard,'O');
					System.out.print(newBoard);
				}
				
					System.out.println(" ");
					System.out.println("Please enter the row");					
					int r = console.nextInt();					
					System.out.println("Please enter the column");					
					int c = console.nextInt();
										
					while(newBoard.get(r, c) != ' ') 
					{						
						System.out.println("\nSpot is already filled, please choose a valid move\n");
						System.out.println(" ");						
						System.out.println("Please enter the row");				
						r = console.nextInt();						
						System.out.println("Please enter the column");						
						c = console.nextInt();
					}
				
							if(newBoard.get(r, c) == ' ') 
							{
								newBoard.set(r , c , 'X' );
								System.out.println(" ");
								System.out.println(newBoard);
							}
													
				}while(newBoard.winner() == ' ');		
		
		}else{				
			
			System.out.println("Please enter yes or no ");	
		}
				System.out.println("\nThe winner of the game is:" + " " + "Player "+ newBoard.winner());
		}
		
		System.out.println("CATS GAME");
	}
}