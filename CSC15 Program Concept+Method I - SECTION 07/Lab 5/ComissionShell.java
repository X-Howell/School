import java.util.Scanner;
public class ComissionShell {

public static final double MAX_RATE = 25;
  
public static void main(String[] args) {
Scanner kb = new Scanner(System.in);

run(kb);

}

public static void run(Scanner kb)

{

boolean repeat = true;

while(repeat)

{

//call the method saleAmount
saleAmount(kb);
// ask the user if he/she wants to use the app again
System.out.println("Do you want to use the app again (yes/no)?: ");
String ch=kb.next();

//update the loop control variable based on the user's input
if(!"yes".equals(ch))
repeat=false;

}

}

public static void saleAmount(Scanner kb)

{

double saleAmount = 0.01;

// call the method getValidDouble to get the sale goal amount
double goal=getValidDouble(kb,"Enter a positive amount for commission goal that you want to get: ");

//call the method getValidDouble to get the base rate
double baseRate=getValidDouble(kb,"Enter a positive amount base rate for commission: ");

//call the method getValidDouble to get the percent that the commission increases
double increment=getValidDouble(kb,"Enter a positive amount of percent increase for commission: ");

//call the method getValidDouble to getthe sale interval that the commission increases
double interval=getValidDouble(kb,"Enter a positive amount for the sale interval that commission increases: ");

double commission = 0;//comission( saleAmount, baseRate,increment,interval);

while (commission <goal)

{
//System.out.println("comi: "+commission+" sales: "+saleAmount+" base: "+ baseRate);
commission = comission( saleAmount, baseRate,increment,interval);

//call the method comission

//if the commission is less thatn the goal
if(commission <goal)

//increase the saleamount by 0.1
saleAmount+=0.1;

}

//output the result
System.out.println("To get the "+goal+" of commission, you need to have "+saleAmount+" of sale");
  

}

public static double comission(double saleAmount, double baseRate, double increment, double interval)

{
//return (saleAmount*(baseRate/100));

double total = 0;

double com = 0;

double count = 0;
//double rate=baseRate;

double sale = saleAmount;

while(sale> interval)

{

// subtract minSale from sale and store the result in sale
sale-=interval;
  
//add the increment to count
count=count+1;
  

}

//find the maximimu rate by adding the count to baseRate and storing
double rate=(count+increment)+baseRate;

//it in a new variable called rate

//while rate is gretaer than zero and saleAmount is gretaer than interval
while(rate>0 && saleAmount>interval)
{

//set com to interval * rate/100
com=interval * rate/100;

// reduce the saleAmount by interval
saleAmount =saleAmount -interval;
  

//reduce the rate by the increment
rate=rate-increment;

//add com to total
total=total+com;

}
  
if (saleAmount > 0)
total=total+(saleAmount*(rate/100));

//calculate the leftover

return total;
  

}

//use the code form the class to help you with the data type validation

//and the range validation

public static double getValidDouble(Scanner kb, String prompt)
{
System.out.println(prompt);
double value=kb.nextDouble();

if(value<0)
getValidDouble(kb,prompt);
else
return value;
  
return 0;
}
  
}