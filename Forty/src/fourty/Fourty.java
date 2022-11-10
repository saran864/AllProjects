package fourty;

import java.util.Scanner;

public class Fourty {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean b = true;
		while (b) {
			System.out.println("Welcome to FOURTE");
			System.out.println("1.Start\n2.Exit");
			
			int userint = sc.nextInt();
			switch (userint) {
			case 1: {
				System.out.println("Enter The Resultant Number : ");
				int res = sc.nextInt();
				int arr[] = new int[5];
				for (int i = 1; i < 5; i++) {
					System.out.println("Enter The " + i + "th Number");
					arr[i] = 
							
							sc.nextInt();
				}

				fourteGame(arr, res);
				break;

			}
			case 2: {
				b = false;
				System.out.println("Ended....!");
				break;

			       }
			}

		}
	}

	static  void fourteGame(int arr[],int res )
	   {
	       int result=res;
	       
	       if((arr[1]+arr[2]+arr[3]+arr[4])==result)
	        	System.out.println(arr[1]+"+"+arr[2]+"+"+arr[3]+"+"+arr[4]+"="+result);
	        
	             if((arr[1]-arr[2]+arrWe have newly introduced DIV-4 for users with Rating <= 1399

[3]+arr[4])==result)
	           System.out.println(arr[1]+"-"+arr[2]+"+"+arr[3]+"+"+arr[4]+"="+result);
	            
	       if((arr[1]+arr[2]-arr[3]+arr[4])==result)
	   	        System.out.println(arr[1]+"+"+arr[2]+"-"+arr[3]+"+"+arr[4]+"="+result);
	       
	       if((arr[1]+arr[2]+arr[3]-arr[4])==result)
	   	         System.out.println(arr[1]+"+"+arr[2]+"+"+arr[3]+"-"+arr[4]+"="+result);
	     
	       if((arr[1]-arr[2]-arr[3]-arr[4])==result) 
	   	         System.out.println(arr[1]+"-"+arr[2]+"-"+arr[3]+"-"+arr[4]+"="+result);
	      
	      if((arr[1]+arr[2]+arr[3]-arr[4])==result)
	   	          System.out.println(arr[1]+"-"+arr[2]+"+"+arr[3]+"-"+arr[4]+"="+result);  
	      
	        if((arr[1]+arr[2]+arr[3]-arr[4])==result)
	   	          System.out.println(arr[1]+"-"+arr[2]+"+"+arr[3]+"-"+arr[4]+"="+result);
	        
	        if((arr[1]*arr[2]*arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"*"+arr[3]+"*"+arr[4]+"="+result);      
	      
	        if((arr[1]*arr[2]-arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"-"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	        	
	        
	        if((arr[1]*arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	   		
	   	    
	        if((arr[1]/arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"/"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	   	
	        if((arr[1]*arr[2]/arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"/"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]/arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"/"+arr[4]+"="+result); 
	        
	        if((arr[1]/arr[2]/arr[3]/arr[4])==result)
	   	          System.out.println(arr[1]+"/"+arr[2]+"/"+arr[3]+"/"+arr[4]+"="+result); 
	        
	        if((arr[1]/arr[2]+arr[3]-arr[4])==result)
	   	          System.out.println(arr[1]+"/"+arr[2]+"+"+arr[3]+"-"+arr[4]+"="+result); 
	        
	        if((arr[1]+arr[2]+arr[3]/arr[4])==result)
	   	          System.out.println(arr[1]+"+"+arr[2]+"*"+arr[3]+"/"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        if((arr[1]/arr[2]+arr[3]+arr[4])==result)
	   	          System.out.println(arr[1]+"/"+arr[2]+"+"+arr[3]+"+"+arr[4]+"="+result); 
	        
	        if((arr[1]-arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"-"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]-arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"-"+arr[4]+"="+result); 
	        
	        if((arr[1]+arr[2]-arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"+"+arr[2]+"*"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	        
	        
	        if((arr[1]+arr[2]-arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"+"+arr[2]+"-"+arr[3]+"-"+arr[4]+"="+result); 
	        
	        if((arr[1]*arr[2]+arr[3]*arr[4])==result)
	   	          System.out.println(arr[1]+"*"+arr[2]+"+"+arr[3]+"*"+arr[4]+"="+result); 
	        
	       
	       
	       
       
	      
        

}

}
