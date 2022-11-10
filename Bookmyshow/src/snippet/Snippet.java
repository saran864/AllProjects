package snippet;

public class Snippet {
	static void showSudoku( int sudoku [] [])
		{
		    System.out.println("Solve the Puzzle");
		     System.out.println("-----------------");
		   for(row=0;row<sudoku.length;row++)
		   {  
		       for(col=0;col<sudoku.length;col++)
		       {
		           if(sudoku[row][col]==0)
		            System.out.print("* ");
		            else 
		          System.out.print(sudoku[row][col]+" ");
		          
		          
		       }
		        System.out.println();
		   }
		 System.out.println("----------------");
		 System.out.println("Start Solve ");
		}
}

