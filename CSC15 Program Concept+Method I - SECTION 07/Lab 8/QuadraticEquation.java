public class QuadraticEquation {
private double a;
private double b;
private double c;

public QuadraticEquation(double a, double b, double c)
{
this.a = a;
this.b = b;
this.c = c;
}

public double getA() {
return a;
}

public void setA(double a) {
this.a = a;
}

public double getB() {
return b;
}

public void setB(double b) {
this.b = b;
}

public double getC() {
return c;
}

public void setC(double c) {
this.c = c;
}

public double getDiscriminant()
{
double d = b * b - 4 * a * c;
if(d < 0)
return 0;
else
return d;
}

public double getRoot1()
{
double d = b * b - 4 * a * c;
if(d < 0)
return 0;
else
return (-b + Math.sqrt(d)) / (2 * a);
}

public double getRoot2()
{
double d = b * b - 4 * a * c;
if(d < 0)
return 0;
else
return (-b - Math.sqrt(d)) / (2 * a);
}

public boolean equals(QuadraticEquation e)
{
if(a == e.a && b == e.b && c == e.c)
return true;
else
return false;
}
public String getEquation()
{
String s= "";
if(a != 0)
{
s += a +"x^2 ";
}

if(b != 0)
{
if(b == 1)
s += "+ x ";
else if(b == -1)
s += "- x ";
else
{
if(b < 0)
s += "- " + (-b) + "x ";
else
s += "+ " + b + "x ";
}
}

if(c != 0)
{
if(c < 0)
s += "- " + (-c);
else
s += "+ " + c;

}

s += " = 0";
return s;
}

public String toString()
{
return getEquation();
}

}