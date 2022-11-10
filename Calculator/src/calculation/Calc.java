package calculation;
import java.util.*;
import java.io.*;

 class Calculator
{

	public void Addition(int a, int b) 
	{
	  System.out.println(a+b);
		
	}
	public void Division(int a, int b) 
	{	  System.out.println(a/b);
		
	}
	public void Subraction(int a, int b) 
	{
		  System.out.println(a-b);
	}
	public void Multiplication(int a, int b) 
	{
		  System.out.println(a*b);
	}
	
	 
	 
}

public class Calc {
	public static void main(String[] args) {
		Scanner in=new Scanner (System.in);
		Calculator cr=new Calculator();	
		boolean b=true;
		while (b)
		{
			System.out.println("1.Addition\n2.Subraction\n3.Multiplication\n4.Division\n5.Exit");
			int useript=in.nextInt();
				switch(useript)
			{
			
				case 1 :
				{
					System.out.println("Enter the input 1");
				int input1=in.nextInt();
				System.out.println("Enter the input 2");
				int input2=in.nextInt();
				 cr.Addition(input1,input2);
				 break;
				}
				 
				case 2 :{
					System.out.println("Enter the input 1");
					int input1=in.nextInt();
					System.out.println("Enter the input 2");
					int input2=in.nextInt();
					 cr.Subraction(input1,input2);
					 break;
				}
				case 3 :
				{
					System.out.println("Enter the input 1");
				int input1=in.nextInt();
				System.out.println("Enter the input 2");
				int input2=in.nextInt();
					 cr.Multiplication(input1,input2);
					 break;
				}
				case 4 :
				{
					System.out.println("Enter the input 1");
					int input1=in.nextInt();
					System.out.println("Enter the input 2");
					int input2=in.nextInt();
				
					 cr.Division(input1,input2);
					 break;
				}
				case 5 :
				{
				 b = false ;
					break;
				}
			default:
					System.out.println("Wrong Choice!!");
					break;					
			}				
			
		}	
	
  }

}
