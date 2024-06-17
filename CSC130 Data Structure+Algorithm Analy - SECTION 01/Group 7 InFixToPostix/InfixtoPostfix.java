//Xavier Howell
//CSC130
//Group7
//InFixToPostFix

import java.util.*;
import java.io.*;

class InfixtoPostfix
{
	public static void main(String args[]) throws Exception
	{
		File file=new File("/Users/xhowell/Desktop/CSC130/input.txt");
		Scanner scan=new Scanner(file);
		String hold=scan.nextLine();
		scan.close();
		Queue<Character> queue=infixtopostfix(hold);
		
		while(!queue.isEmpty())
		{
			System.out.print(queue.poll());
		}
		
	System.out.println();
	}
	
	static int Pre(char ch)
	{
		if(ch=='+' || ch=='-') return 1;
		if(ch=='*' || ch=='/') return 2;
		return -1;
	}

	static Queue<Character> infixtopostfix(String hold)
	{
		Queue<Character> queue=new LinkedList<>();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i<hold.length(); ++i)
		{
			char c = hold.charAt(i); 
			if (Character.isLetterOrDigit(c)) 
			{
				queue.add(c);
			}
			else if (c == '(')
			stack.push(c);
			else if (c == ')')
			{
				while (!stack.isEmpty() && stack.peek() != '(')
				queue.add(stack.pop());
				stack.pop();
			}
			else
			{
				while (!stack.isEmpty() && Pre(c) <= Pre(stack.peek()))
				{ 
					queue.add(stack.pop());
				}
				stack.push(c);
			}
		}
		
		while (!stack.isEmpty())
		{
			queue.add(stack.pop());
		}
			
	return queue;
	}
	
}