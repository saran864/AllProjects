package flames;

public class MagiSquare {
public static void main(String[] args) {
   int check=3;
    int arr[]= {1,2,3,4,5,6,7,8,9},i,j,k;
      for(i=0;i<arr.length;i++)
      {
    	  for(j=i+1;j<arr.length;j++)
          {
    		  for(k=j+1;k<arr.length;k++)
    	      {
    	    	  if(arr[i]+arr[j]+arr[k]==15);
    	    	  System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
    	      }
          } 
      }
    	  
    
}
}
