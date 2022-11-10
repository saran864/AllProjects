package calculator;

import java.util.Scanner;

public class calc {
	public static void main(String[] args) {
     Scientific s=new Scientific();
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		  while(b)
		  {       
		      System.out.println("----------------------------------");
			    System.out.println("1.On\n2.Off");
			  System.out.println("----------------------------------");
			  int userint=sc.nextInt();
			  switch(userint)
			  {
				  case 1:
				  {
					  s.operation();
					  break;
				  }
				   case 2:
				  {
					  b=false;
					  break;
				  }
				 					   
					  
		
		
	
			  }
		  }
	}

}
