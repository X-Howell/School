import java.util.Scanner;

public class RecursionNumberSum 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number ");
        int n = in.nextInt();
        System.out.println("The sum of the number " + n + " would be " + sum(n) + ".");
    }
    
    public static int sum(int n) 
    {
        if (n > 0)
        {         
            int digit = n % 10;
            int sumRest = sum(n / 10);
            return digit + sumRest;
        } 
        else 
        {
            return 0;
        }
    }
}