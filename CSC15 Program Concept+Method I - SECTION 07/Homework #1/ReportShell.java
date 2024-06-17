import java.util.*;
public class ReportShell
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      process(kb);
//System.out.println(s);
   }
/*This method asks the user for the number of the clients and generate the report too*/

   public static void process(Scanner kb)
   {
      description();//call the method description
      System.out.println("How many clients do you have :");
      int clients = kb.nextInt();
      int totalHours=0;
      double income=0.0;
      Formatter formatter = new Formatter();
      formatter.format("%20s %20s %20s %20s %20s %20s %20s %20s","Client", "Lawyer", "Hours", "Fee", "%Discount", "Reg Hours", "Discounted Hours", "Total Fee");
      StringBuilder rep = new StringBuilder();
      rep.append(formatter);
      rep.append(System.getProperty("line.separator"));
      for(int i = 1; i <= clients; i++)
      
      {   
         System.out.println("Enter the name of the client :"+ kb.nextLine());
         String clientName= kb.nextLine();
         System.out.println("Enter the name of the lawyer :");
         String lawyerName = kb.nextLine();
         System.out.println("Enter the hours :");
         int hours = kb.nextInt();
         System.out.println("Enter the base hours :");
         int baseHours = kb.nextInt();
         System.out.println("Enter the fee for the first "+baseHours+" hours :");
         float feePerHour = kb.nextFloat();
         System.out.println("Enter the discount percentage:{5,10,20,25,....}: ");
         int discountedPercentage = kb.nextInt();
         System.out.println("==============================================================================");
//call the method getFee to get the total cost for the client and
//store it in a variable called fee
         float TotalFee = getFee(discountedPercentage,baseHours,hours,feePerHour);   
//call the method getDiscountedHours  
         int discountedHrs = getDiscountedHour(hours, baseHours);   
//calculate the regular hours
         int regularHrs = baseHours;
//add hours to total hours
         totalHours = totalHours+hours;
//add the fee to the total income
         income = income+TotalFee;
//use the format method from the string class to cretae a formated
         Formatter formatter1 = new Formatter();
         formatter1.format("%20s %20s %20s %20f %20s %20s %20s %20f",clientName, lawyerName, hours, feePerHour, discountedPercentage, regularHrs, discountedHrs, TotalFee);
//string called s containing all the info about the client   
         rep.append(System.getProperty("line.separator"));
         rep.append(formatter1);      
      }
         System.out.println("The report is ready,Hit Enter to view it "+kb.nextLine());
         System.out.println(rep);
         System.out.println("Total Hours Total Income \n");
         System.out.println(totalHours+" "+income);
}

/*this method creates the description of what this program does*/
   public static void description()
   {
      System.out.println("*************************************************************************************************************");
      System.out.println("This program generates a summary report for the Best Law Firm\nClients are charged based on the number of hours.\n"
      + "Depending on the assigned lawyer, clients get an hourly discount after certain number of hours.\n"
      + "The provided discount is offered by each individual lawyer and it could vary from one lawyer to another");
      System.out.println("*************************************************************************************************************");
   }

/*This method calculate the fee */
   public static float getFee(int dp, int bh,int th,float fph)
   { 
      float totalCost;
      float cost4bh = bh*fph;
      int dh = getDiscountedHour(th,bh);
      float cost4dhBeforeDiscount=dh*fph;
      float discount = dh*fph*dp/100;
      float cost4dhAfterDiscount= cost4dhBeforeDiscount-discount;
      if(th>bh) { 
      totalCost = cost4bh+cost4dhAfterDiscount;
   }
   else
   {
      totalCost = cost4bh;
   }
   return totalCost;   
   }  
   public static int getDiscountedHour(int th,int bh) {
   return (th-bh);
   }
   
}