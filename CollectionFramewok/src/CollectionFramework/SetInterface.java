package CollectionFramework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
public class SetInterface {

	public static void main(String[] args) {
	 
		  
 HashSet<String> set=new HashSet();  
		           set.add("One");    
		           set.add("Two");    
		           set.add("Three"); 
		           System.out.println(set);
		           Iterator <String>  i= set.iterator();  
		           while(i.hasNext())  
		           {  
		           System.out.println(i.next());  
		           }  
		          
		  

	}
	

}
/*import java.util.*;
public class LinkedHashset {
	 public static void main(String args[]){  
		 
		        LinkedHashSet<String> set=new LinkedHashSet();  
		               set.add("One");    
		               set.add("Two");    
		               set.add("Three");   
		               set.add("Four");  
		               set.add("Five");  
		               Iterator<String> i=set.iterator();  
		               while(i.hasNext())  
		               {  
		               System.out.println(i.next());  
		               }  
	 }

}
*/
