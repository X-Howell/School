import java.util.Random;

import java.util.Scanner;

public class CrapGame
{

   public static void main(String[] args) 
   {
      description();
      play();
         
   }
   
   private static void play() 
   {
   
      int numOfwin = 0, numOfLoss = 0, thePoint = 0, comeOutRoll = 0, numOfPlays = 0;
      boolean playAgain = true;
      Random random = new Random();
      Scanner sc = new Scanner(System.in);
      
         while (playAgain) 
         {
          
            for (int i = 0; i < 10000; i++) 
            {   
               int random1 = random.nextInt(6) + 1;  
               int random2 = random.nextInt(6) + 1; 
               comeOutRoll = random1 + random2; 
               String result = winOrLoss(comeOutRoll);
                  
               if (result.equals("win")) 
               {     
                  numOfwin++;     
                  numOfPlays++;
               } 
            
               else if (result.equals("Loss")) 
               {
                  numOfLoss++;
                  numOfPlays++;
               } 
               
               else 
               {
                  thePoint = comeOutRoll;         
                  String result2 = keepRolling(thePoint, random);
                          
                  if (result2.equals("seven")) 
                  {         
                     numOfLoss++;         
                     numOfPlays++;         
                  } 
                  
                  else 
                  {         
                     numOfwin++;         
                     numOfPlays++;         
                  }
               }      
            }
         
            System.out.println("In the simulation we:");
            System.out.println("\t won " + numOfwin + " times");
            System.out.println("\t lost " + numOfLoss + " times");            
            System.out.println("\t for a probabilty of " + winProbabilty(numOfwin, numOfLoss));            
            System.out.println("Hit enter to continue");            
            sc.nextLine();            
            numOfwin = 0;           
            numOfLoss = 0;            
            thePoint = 0;
            comeOutRoll = 0;
            numOfPlays = 0;
            String choice = "yes";
            
            while(true) 
            {            
               System.out.print("Would you like to play another game yes/no?");            
               choice = sc.nextLine();
               
               if(!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no")) 
               {
                  continue;
               
               } 
               
               else if(choice.equals("no")) 
               {
                  playAgain = false;
                  break;
                  
               } 
               
               else 
               {
                  playAgain = true;
                  break;
               
               }
            }
            
            System.out.println();
         
         }
      
   System.out.println("Have a nice day. GoodBye");
   sc.close();
   
   }
   
   private static String winOrLoss(int comeOutRoll) 
   {
      if (comeOutRoll == 7 || comeOutRoll == 11) 
      {
         return "win";
      
      } 
      
      else if (comeOutRoll == 2 || comeOutRoll == 3 || comeOutRoll == 12) 
      {
         return "Loss";
      
      } 
      
      else 
      {
         return "the point";
      
      }
   
   }
   
   private static void description() 
   {
      System.out.println("Computer will play a game for you. Here are the rules of the game: ");
      System.out.println(" Two six sided dice is rolled");
      System.out.println(" Come out roll: The first roll of the dice in a round");
      System.out.println("A come out roll of 7 or 11 automatically wins");
      System.out.println("A come out roll of 2, 3, or 12 automatically loses");
      System.out.println(
      "A come out roll of 4, 5, 6, 8, 9, 10 becomes The Point If player gets the point he/she will keep playing \nby rolling the dice until he/she gets a 7 or the point");
      System.out.println("If the point is rolled first, then player wins the bet.");
      System.out.println("If a 7 is rolled first, then the player loses.\n\n");
      System.out.println("Let's start playing\n");
   }   

   private static String keepRolling(int thePoint, Random random) 
   {
   
      while (true) 
      {
      
         int random1 = random.nextInt(6) + 1;
         int random2 = random.nextInt(6) + 1;
         thePoint = random1 + random2;
         
         if (thePoint == 7) 
         {
            return "seven";
         
         } 
         
         else if (thePoint == 4 || thePoint == 5 || thePoint == 6 || thePoint == 8 || thePoint == 9
         
         || thePoint == 10) {
         
         return "thePoint";
         
         }
      
      }
      
   }   
   private static double winProbabilty(int numOfwin, int numOfLoss)
   {
      double numOfWinInDouble = numOfwin;
      return numOfWinInDouble / (numOfWinInDouble + numOfLoss);
   
   }
   
}