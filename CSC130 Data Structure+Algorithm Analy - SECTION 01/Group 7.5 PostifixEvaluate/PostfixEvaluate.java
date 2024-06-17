//Xavier Howell
//CSC130
//Group7.5
//PostfixEvaluate

import java.util.*;
import java.io.*;

class PostfixEvaluate
{
	public static void main(String args[]) throws Exception
	{
		File file=new File("/Users/xhowell/Desktop/CSC130/input.txt");
		Scanner scan=new Scanner(file);
		String hold=scan.nextLine();
		scan.close();
		int value=EvaluatePostfix(hold);
		System.out.println("Expression value: "+value);
	}
	
	static int EvaluatePostfix(String exp)
	{
		Stack<Integer> stack=new Stack<>();
		int length=exp.length();
		for(int i=0;i<length;++i)
		{
			char c=exp.charAt(i);
			if (Character.isLetterOrDigit(c)) 
			{
				stack.push((int)(c-'0'));
			}
			else
			{
				int w=stack.pop();
				int x=stack.pop();
				int y=0;
				switch(c)
				{
					case '+': y=x+w; break;
					case '-': y=x-w; break;
					case '*': y=x*w; break;
					case '/': y=x/w; break;
				}
			stack.push(y);
			}
		}
		
	return stack.pop();
	}
}