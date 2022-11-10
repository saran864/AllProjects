package hackerrank;

import java.util.Scanner;

public class NumberAlbhabets {
public static void main(String[] args) {
   Scanner sc=new Scanner(System.in);
   System.out.println("Enter value");
     int a=sc.nextInt();
     if(a>0&&a<=999)
     {
    	 alphabets(a);
     }
     
   sc.close();  
   
}
private static void alphabets(int a) {
	
	String [] ones= {"","one","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
	String [] teens= {"" ,"Eleven","Twelwe","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};	
	String [] tens= {"" ,"ten","Twenty","Thrirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"," Hundred  "};
	
	int mod,count=0,s=1,teen=0;
	String d="";//for store ones
	String f="";//for  store tens
	String g="";//for store humdredss
	
	int temp=a; //
	while(temp!=0)//123 12 %10 2 1%1 1
	{    
		temp/=10;//12 1 1 3
		count++; //3
	}
	int printer=count;
	
	
	while(count!=0)
	{
		 mod=a%10;///123%10 3 2
		 switch (s)//1
		 {
		 case 1://find ones	 
		 {
			 d=ones[mod];//three
			teen=mod;
			 s++;
			 a/=10;//12
			 break; 
		 } 	
		  
		 
		 case 2://find 10s
		 {    if(mod==1) {
			 d=teens[teen];
			 f=teens[0]+d;
			 s++;
			 a/=10;
		 }
		 else if(mod==0)
		 {
			 f=teens[0]+d;
			 s++;
			 a/=10;
		 }
		 else
		 {
			 f=tens[mod]+teens[0]+d;
			 s++;
			 a/=10;
			 
		 }			 
			 break;		 
		 }
		 case 3://find 100s
		 {    
			 
			 g=ones[mod]+tens[10]+f;
		 }	
		 		
		 }
		 count--;
	}	
if(printer==3)
	System.out.println(g);// 123
if(printer==2)
	System.out.println(f);//23
if(printer==1)
	System.out.println(d);//1
	
}
}
