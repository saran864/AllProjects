package flames;
//
import java.util.Scanner;
class A{
	A(){
		super(5);
		System.out.print("N");
	}
	A(int x){
		System.out.print("P");
	}
}

public class JavaDemo {
	public static void main(String[] args) {
	A a=new A();
	}
}


//	  public static void main (String[]args)
//	  {
//	    Scanner sc = new Scanner (System.in);
//	     System.out.println("Welcome To FLAMES  Calculator ");
//	    int i, j,n1count=0,n2count=0,tcount;
//	    System.out.print("Enter Your Name: "); 
//	    String Name1 = sc.next ();
//	    System.out.print("Enter Your CrushName: ");
//	    String Name2 = sc.next ();
//	    String s1 =Name1.toLowerCase();
//	    String s2=Name2.toLowerCase();
//	    
//	    char a[] = s1.toCharArray ();
//	    char b[] = s2.toCharArray ();
//	    for (i = 0; i < a.length; i++)
//	      {
//	    for (j = 0; j < b.length; j++) 
//		{
//		  if (a[i] == b[j])
//		    {
//		      a[i] = ' ';
//		      b[j] = ' ';
//	        }
//	      }
//	      if(a[i]!=' ')
//	      n1count++;
//	    }
//	     for (j = 0; j < b.length; j++) 
//	     {if(b[j]!=' ')
//	       n2count++;
//	     }
//	 
//	   System.out.println(n1count);
//	   System.out.println(n2count);
//	   
//	    
//	     
//	      
//	  }
	//}
//
//
//
