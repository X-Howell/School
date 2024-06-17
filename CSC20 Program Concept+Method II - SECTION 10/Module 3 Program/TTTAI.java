/**
 * Xavier Howell
 * 4/17/19
 * TTTAI.java Final
 */

public class TTTAI{

	public static Boolean isFull(TTTBoard board)
	{			   
		Boolean isFull = true;
		for(int i=0;i<board.size;i++)
		{		    	   
			for(int j=0;j<board.size;j++)
			{		        	   
				if(board.get(i,j)==' ')
				{		            	   
					isFull = false;		                   
					break;		                 
				}
			}
		}
		return isFull;
	}

	public static Boolean tryToWin(TTTBoard board, char who)
	{
       int size = board.size;

		// Check the columns
		for(int j=0;j<size;j++)
		{
           int favorableCells = 0;
           int emptyI=-1, emptyJ=-1;
           for(int i=0;i<size;i++){
               if(board.get(i,j)==who){
                   favorableCells++;
               }else if(board.get(i,j)==' '){
                   // Empty cell can be filled
                   emptyI = i;
                   emptyJ = j;
               }
           }
           if(favorableCells==size-1 && emptyJ!=-1){
               board.set(emptyI,emptyJ,who);
               return true;
           }
       }

       // Check the rows
       for(int i=0;i<size;i++){
           int favorableCells = 0;
           int emptyI=-1, emptyJ=-1;
           for(int j=0;j<size;j++){
               if(board.get(i,j)==who){
                   favorableCells++;
               }else if(board.get(i,j)==' '){
                   // Empty cell can be filled
                   emptyI = i;
                   emptyJ = j;
               }
           }
           if(favorableCells==size-1 && emptyJ!=-1){
               board.set(emptyI,emptyJ,who);
               return true;
           }
       }
       // Check diagonals
       int favorableCells = 0;
       int emptyI=-1, emptyJ=-1;
       for(int i=0,j;i<size;i++){
           j = i;
           if(board.get(i,j)==who){
               favorableCells++;
           }else if(board.get(i,j)==' '){
               // Empty cell can be filled
               emptyI = i;
               emptyJ = j;
           }
       }
       if(favorableCells==size-1 && emptyJ!=-1){
           board.set(emptyI,emptyJ,who);
           return true;
       }
       favorableCells = 0;
       emptyI=-1; emptyJ=-1;
       for(int i=0,j;i<size;i++){
           j = size-1-i;
           if(board.get(i,j)==who){
               favorableCells++;
           }else if(board.get(i,j)==' '){
               // Empty cell can be filled
               emptyI = i;
               emptyJ = j;
           }
       }
       if(favorableCells==size-1 && emptyJ!=-1){
           board.set(emptyI,emptyJ,who);
           return true;
       }
       return false;
   }

   public static Boolean trytoNotLoose(TTTBoard board, char who){
       int size = board.size;
       // Check the columns
       for(int j=0;j<size;j++){
           int nonFavorableCells = 0;
           int emptyI=-1, emptyJ=-1;
           for(int i=0;i<size;i++){
               char cell = board.get(i,j);
               if(cell!=who && cell!=' '){
                   nonFavorableCells++;
               }else if(cell==' '){
                   // Empty cell can be filled
                   emptyI = i;
                   emptyJ = j;
               }
           }
           if(nonFavorableCells==size-1 && emptyJ!=-1){
               board.set(emptyI,emptyJ,who);
               return true;
           }
       }
       // Check the rows
       for(int i=0;i<size;i++){
           int nonFavorableCells = 0;
           int emptyI=-1, emptyJ=-1;
           for(int j=0;j<size;j++){
               char cell = board.get(i,j);
               if(cell!=who && cell!=' '){
                   nonFavorableCells++;
               }else if(cell==' '){
                   // Empty cell can be filled
                   emptyI = i;
                   emptyJ = j;
               }
           }
           if(nonFavorableCells==size-1 && emptyJ!=-1){
               board.set(emptyI,emptyJ,who);
               return true;
           }
       }
       // Check diagonals
       int nonFavorableCells = 0;
       int emptyI=-1, emptyJ=-1;
       for(int i=0,j;i<size;i++){
           j = i;
           char cell = board.get(i,j);
           if(cell!=who && cell!=' '){
               nonFavorableCells++;
           }else if(cell==' '){
               // Empty cell can be filled
               emptyI = i;
               emptyJ = j;
           }
       }
       if(nonFavorableCells==size-1 && emptyJ!=-1){
           board.set(emptyI,emptyJ,who);
           return true;
       }
       nonFavorableCells = 0;
       emptyI=-1; emptyJ=-1;
       for(int i=0,j;i<size;i++){
           j = size-1-i;
           char cell = board.get(i,j);
           if(cell!=who && cell!=' '){
               nonFavorableCells++;
           }else if(cell==' '){
               // Empty cell can be filled
               emptyI = i;
               emptyJ = j;
           }
       }
       if(nonFavorableCells==size-1 && emptyJ!=-1){
           board.set(emptyI,emptyJ,who);
           return true;
       }
       return false;
   }

   public static void randomTurn(TTTBoard board, char who){
       int size = board.size;
       for(int i=0;i<size;i++){
           for(int j=0;j<size;j++){
               if(board.get(i,j)==' '){
                   board.set(i,j,who);
                   return;
               }
           }
       }
   }

   public static void move(TTTBoard board, char who){
       
   if(board.winner()!= ' ')
   
           throw new IllegalArgumentException("Board already has a winner");
   
       if(isFull(board))
           throw new IllegalArgumentException("Board is full");
       if(!tryToWin(board,who)){
           if(!trytoNotLoose(board,who)){
               randomTurn(board,who);
           }
       }
       
   }
   
}