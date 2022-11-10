package flames;

import java.util.Scanner;
//find magic square or not 

public class magicSquare {

	public static void main(String[] args) {
		
	
		int square [][]=new int[3][3];
		System.out.println("Enter 3*3 matrix :");
		int count=0,count1=0;
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
			   square[i][j]=sc.nextInt();
				
			}
		}
			
		for(int i=0;i<3;i++)
		{
			
			   if (square[i][0]+square[i][1]+square[i][2]==15)
			   {
				  count++;
			   }

		}
		
		for(int i=0;i<3;i++)
		{
			
			if (square[0][i]+square[1][i]+square[2][i]==15)
			   {
				  count1++;
			   }

		}
	System.out.println(count==3 && count1==3 ? " Wow.! it is a Magic Square" : " Not a magic Square");
	
}
}
