package hrProblem;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class DiagonalDifference {




	    public static void main(String[] args)  {
	        Scanner sc=new Scanner(System.in);  
	         int n=sc.nextInt();
	         if(n>=-100&&n<=100)
	         {
	          int a[][] = new int[n][n],i,j,sum=0,dif=0;
	        for ( i = 0; i < n; i++) {
	            for ( j = 0; j < n; j++)
	            {  
	                a[i][j] = sc.nextInt();
	            }
	        }
	        for ( i = 0; i < n; i++) {
	            for ( j = 0; j < n; j++)
	            {  if(i==j)
	                sum+=a[i][j];
	                if((i+j)==n-1)
	                dif+=a[i][j];
	                
	            }    
	        }
	        int min=sum-dif;
	        System.out.println(Math.abs(min));
	         }
	           
	            
	        
	      
	    
	
	}


}
