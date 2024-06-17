import java.util.Scanner;
public class PrintCalenderShell2
{
   /*The main method has only one line of code and it calls the process method*/
   public static void main(String[] args)
   {
       process();         
   }
   /*This method ask the required info and calls the appropriate methods based on the user's input to either print
   the calendar for the month or for the year*/
   public static void process()
   {
         Scanner scanner = new Scanner(System.in);
         System.out.print("How many times do you want to try this app? ");
         int runTimes = scanner.nextInt();
         while(runTimes!=0){
         System.out.print("To print the calender choose one of the following options:\n");
         System.out.println("1.Year\n2.Month\nEnter your choice -->");
         String choice = scanner.next();
         if(choice.equals("year")){
         // Prompt the user to enter year
         System.out.print("Enter full year (e.g., 2016): ");
         int year = scanner.nextInt();
         // Prompt the user to enter month
         // Print calendar for the month of the year
         for(int i = 1; i <= 12; i++)
         printMonth(year, i);
         }else if(choice.equals("month")){
         System.out.print("Enter month in number between 1 and 12: ");
         int month = scanner.nextInt();
         System.out.print("Enter full year (e.g., 2016): ");
         int year = scanner.nextInt();
         printMonth(year, month);
         }
         runTimes--;
         }      
   }
   //this method prints the body of the calender for the given month by calling the  the methods printMonthTable and printMonthBody
   public static void printMonth(int year, int month)
   {
      printMonthTitle(year, month);
      
      printMonthBody(year, month);
     
   }
   //this method prints the title of the days in each week(sunday Mon Tues Wed Thur Fir Sat)
   /*This method prints the following     
           month name year
----------------------------
 Sun Mon Tue Wed Thu Fri Sat
 
  this method gets the month as an integer and you need to call the appropriate method to get the name of the month*/
   public static void printMonthTitle(int year, int month)
   {
   System.out.println(" " + getMonthName(month) + " " + year);
   System.out.println("-----------------------------");
   System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
   }
   /*this method calls the method getStartday to find out the first day of the month(sunday, monday, tuesday, wednesday, thursday or friday
   then it calls the method print by passing the startday, year and month*/
   public static void printMonthBody(int year, int month)
   {
   // Get start day of the week for the first date in the month
   int startDay = getStartDay(year, month);
   // Get number of days in the month
   int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
   // Pad space before the first day of the month
   int i = 0;
   for (i = 0; i < startDay; i++)
   System.out.print(" ");
   for (i = 1; i <= numberOfDaysInMonth; i++){
   if (i < 10)
   System.out.print("  " + i);
   else
   System.out.print("  " + i);
   if ((i + startDay) % 7 == 0)
   System.out.println();
   }
   System.out.println();
   }
   public static void print(int startDay, int year, int month)
   {
	   
     for (int i =1; i <= startDay; i++)
        System.out.print("    ");
     for (int i = 1; i<= getNumberOfDaysInMonth(year, month); i++)
     {
          
          System.out.printf("%-4d", i);
          if ((i + startDay) % 7 ==0)
            System.out.println();
     }
	  if (month ==11)
	  {
	     int thanksGiving = thanks(startDay);
		  System.out.println("\n**Thanks giving day is on the " + thanksGiving + "th");
	  }	                
   }
   /*This method finds out the day of thanksgiving
   you must use switch statements . Thanksgiving is the last Thursday of November therefore we need to add a value to 21. 
   for example if the first of the month is sunday then from sunday to thurday is 5 days, then we add 5 to 21 and that would be the thanksgiving day.
   The first day of November 2018 is Thursday and so thanksgiving will be on 1 + 3 * 7 = 22*/
	public static int thanks(int startDay)
	{ 
       return 0; 
	}					 								  		  		  			  		  
	/*This method gets the number of the month and returns the name of the month for example the month is 2 then this method must return Febuary*/    	    
   public static String getMonthName(int month)
   {
      String monthName = null;
      switch (month)
      {
      case 1: monthName = "January"; break;
      case 2: monthName = "February"; break;
      case 3: monthName = "March"; break;
      case 4: monthName = "April"; break;
      case 5: monthName = "May"; break;
      case 6: monthName = "June"; break;
      case 7: monthName = "July"; break;
      case 8: monthName = "August"; break;
      case 9: monthName = "September"; break;
      case 10: monthName = "October"; break;
      case 11: monthName = "November"; break;
      case 12: monthName = "December";
      }
      return monthName;
            
   }
   /*this method returns the first day of the month which is a number, Sunday - saturday is assosiated with 0 - 6. 
   you need to know how many days has passed since 1800, therefore we call the method getTotalNumberOfDays. 
   Remember that Jan 1st 1800 was on a wednesday. 
   after you have the total number of the days you can find out the number of the weeks has passed since Jan 1800
   the leftover will be the start day for the given month and the number of the days left
    
     */
   public static int getStartDay(int year, int month)
   {
   // Get total number of days since 1/1/1800
   int startDay1800 = 3;
   int totalNumberOfDays = getTotalNumberOfDays(year, month);
   // Return the start day
   return (totalNumberOfDays + startDay1800) % 7;
     
   }
   /*use a for loop to calculate the total number of the days has passed since 1800 to the (year -1) 
    use anothe for loop to add the number of the days in each month up to the month entered by the user.
    also remember to check if the year is a leap year or not.
    this method needs to call getNumberOfDaysInMonth method*/
    
   public static int getTotalNumberOfDays(int year, int month)
   { 
   int total = 0;
   // Get the total days from 1800 to year - 1
   for (int i = 1800; i < year; i++)
   if (isLeapYear(i))
   total = total + 366;
   else
   total = total + 365;
   // Add days from Jan to the month prior to the calendar month
   for (int i = 1; i < month; i++)
   total = total + getNumberOfDaysInMonth(year, i);
   return total;
   
      }
   /** Get the number of days in a month */
   static int getNumberOfDaysInMonth(int year, int month)
   {
   if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
   return 31;
   else if (month == 4 || month == 6 || month == 9 || month == 11)
   return 30;
   else if (month == 2)
   return isLeapYear(year) ? 29 : 28;
   return 0; // If month is incorrect
   }
     
   /** Determine if it is a leap year */
   static boolean isLeapYear(int year){
   return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
   }
}