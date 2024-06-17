//Name:Xavier Howell
//Desc:Spell Mississippi
//Date:2.20.18

public class Conversion
{
   public static void main(String[] args)
   {
      print();
   }
   //inches conversion
   public static double feet_inch(double feet)
   {
      double inches = feet * 12;
      return inches;
   }
   //meter conversion
   public static double feet_meter(double feet)
   {
      double meter = feet * .305;
      return meter;
   }
   // centi conversion
   public static double meter_centi(double meter)
   {
      double centi = meter * 100;
      return centi;
   }
   //mili conversion
   public static double centi_mili(double centi)
   {
      double mili = centi * 10;
      return mili;
   }
   public static void print()
   {
   // Create section headers
   // set for statement_create chart
      System.out.println("Feet    Inch    Meter    Centi      Mili");
      for (int i=1; i<=20; i++)
      {
         double inch = feet_inch(i);
         double meter = feet_meter(i);
         double centi = meter_centi(meter);
         double mili = centi_mili(centi);
         System.out.printf("%2s%10.2f%8.2f%10.2f%11.2f\n",i,inch,meter,centi,mili); 
      }
   }
}