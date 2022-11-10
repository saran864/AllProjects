package hrProblem;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class Triplets {
	

	
	    public static void main(String args []){
	      Scanner sc=new Scanner(System.in);
	         int alice=0,Bob=0,i,j;
	         int a[]=new int[3];
	         int b[]=new int[3]; 
	    for( i=0;i<3;i++)
	        {
	            a[i]=sc.nextInt();   
	        }  
	    for( i=0;i<3;i++)
	      { 
	          b[i]=sc.nextInt(); 
	       }
	         for( i=0;i<3;i++)
	         {
	            if(a[i]>b[i])
	            alice++;
	           else if(a[i]<b[i])
	            Bob++;
	       }
	        System.out.print(alice +" "+Bob);
	    
	      
	       
	    
	    }
	

}
