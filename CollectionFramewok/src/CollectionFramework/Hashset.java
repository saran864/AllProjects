package CollectionFramework;

import java.util.*;

public class Hashset {
public static void main(String[] args) {
	Set<String> hash_Set = new HashSet<String>();
	 hash_Set.add("Hi");
     hash_Set.add("i'm");
     hash_Set.add("Saran");
     hash_Set.add("From");
     hash_Set.add("Thanjavur");
     for (String value : hash_Set)
         System.out.print(value + ", ");
     System.out.println();
     System.out.println(hash_Set);
     String check = "Thanjavur";
     String check1 = "*";
     
 System.out.println(hash_Set.contains(check1));
     System.out.println( hash_Set.contains(check));
     
     Set<Integer> a = new HashSet<Integer>();
     a.addAll(Arrays.asList(
             new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));
     
     Set<Integer> b = new HashSet<Integer>();
     
     b.addAll(Arrays.asList(
           new Integer[] { 1, 3, 7, 5, 4, 0, 7, 5 }));
     
     
     Set<Integer> union = new HashSet<Integer>(a);
     union.addAll(b);
     System.out.print("Union of the two Set");
     System.out.println(union);

     Set<Integer> intersection = new HashSet<Integer>(a);
     intersection.retainAll(b);
     System.out.print("Intersection of the two Set");
     System.out.println(intersection);


     Set<Integer> difference = new HashSet<Integer>(a);
     difference.removeAll(b);
     System.out.print("Difference of the two Set");
     System.out.println(difference);
}
}
