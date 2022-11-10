package hrProblem;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;



public class VeryBigSum {
	
	    public static void main(String[] args) {
	        Scanner sc=new Scanner(System.in);
	        int n=sc.nextInt();
	        int a[]=new int[n]; 
	        long sum=0;
	        for(int i=0;i<n;i++)
	        a[i]=sc.nextInt();
	        for(int j=0;j<n;j++)
	        sum+=a[j];
	        System.out.println(sum);
	    
	        
	    
	}
}
