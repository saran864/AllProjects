package hrProblem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;;

public class PulusMinus {


    public static void plusMinus(List<Integer> arr) {
       int i;
       double c=0,c1=0,c2=0;
       for(i=0;i<arr.size();i++)
          {  if(arr.get(i)>0)
              c++;
              else if(arr.get(i)<0)
              c1++;
              else if(arr.get(i)==0)
              c2++;
          }
          
          double a,b,d;
           a=c/arr.size();
           b=c1/arr.size();
           d=c2/arr.size();
          System.out.printf("%.6f\n",a);
           System.out.printf("%.6f\n",b);
            System.out.printf("%.6f\n",d);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}



