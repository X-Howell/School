//Name: Xavier Howell
//Date: 2.11.18

public class lab_2
{
public static final int SIZE = 3
;
   
   public static void main (String[] args)   
   { 
     draw();
   }
   
   public static void draw()
   {
      line();
      top();
      top();
      line();
      bottom();
      bottom();
      line();
   }  
   
   public static void line()
   {   
         System.out.print("+");                          
         for (int dash = 1; dash<= SIZE*2; dash++)           //+-------+
         {   
         System.out.print("-");
         }   
         System.out.println("+");
   } 
   
   public static void top()     
   {   
      for (int line=1; line<=SIZE; line++)
      {        
            System.out.print("|");
         for (int space=1; space<=SIZE-line; space++)        // |  ^^  |
         {                                                   // | ^  ^ |
            System.out.print(" ");                           // |^    ^|
         } 
            System.out.print("^");
         for (int space=1; space<=2*(line-1); space++)
         {
            System.out.print(" ");
         }
            System.out.print("^");  
         for (int space=1; space<=SIZE-line; space++)
         {
            System.out.print(" "); 
         }
            System.out.println("|");
      }
   }
   
   public static void bottom()
   {
      for (int line=1; line<=SIZE; line++)
      {
            System.out.print("|");
         for (int space=1; space<=line-1; space++)                     //|v    v|             
         {                                                             //| v  v |
            System.out.print(" ");                                     //|  vv  | 
         }
            System.out.print("v");
         for (int space=1; space<=(-2*line)+(SIZE*2); space++)         // Calculate equation *y=-2x+6
         {
            System.out.print(" ");
         }
            System.out.print("v");
         for (int space=1;  space<=line-1; space++)
         {
            System.out.print(" ");
         }
            System.out.println("|");                    
      } 
   }                                                  
                 

}          