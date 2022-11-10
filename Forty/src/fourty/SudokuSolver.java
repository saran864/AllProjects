package fourty;

public class SudokuSolver {
	 private static final int GRID_SIZE = 9;

	    public static void main(String[] args) {
	        int[][] question = new int[][]{
	                {8, 4, 9, 0, 0, 3, 5, 7, 0},
	                {0, 1, 0, 0, 0, 0, 0, 0, 0},
	                {7, 0, 0, 0, 9, 0, 0, 8, 3},
	                {0, 0, 0, 9, 4, 6, 7, 0, 0},
	                {0, 8, 0, 0, 5, 0, 0, 4, 0},
	                {0, 0, 6, 8, 7, 2, 0, 0, 0},
	                {5, 7, 0, 0, 1, 0, 0, 0, 4},
	                {0, 0, 0, 0, 0, 0, 0, 1, 0},
	                {0, 2, 1, 7, 0, 0, 8, 6, 5}
	        };

	        display(question);
	        if (solver(question)) {
	            System.out.println("\nSolved Successfully\n");
	            display(question);
	        } else System.out.println("\nImpossible Question!\n");


	    }

	    private static void display(int[][] question) {
	        for (int row = 0; row < GRID_SIZE; row++) {
	            if (row % 3 == 0 && row != 0) System.out.println("-----------");
	            for (int col = 0; col < GRID_SIZE; col++) {
	                if (col % 3 == 0 && col != 0) System.out.print("|");
	                System.out.print(question[row][col]);
	            }
	            System.out.println();
	        }
	    }

	    public static boolean isValidInRow(int[][] question, int numberToFind, int row) {
	        for (int i = 0; i < GRID_SIZE; i++) {
	            if (question[row][i] == numberToFind)
	                return true;
	        }
	        return false;
	    }

	    public static boolean isValidInColumn(int[][] question, int numberToFind, int col) {
	        for (int i = 0; i < GRID_SIZE; i++) {
	            if (question[i][col] == numberToFind)
	                return true;
	        }
	        return false;
	    }

	    public static boolean isValidInBox(int[][] question, int numberToFind, int row, int col) {
	        int currRow = row - row % 3;
	        int currCol = col - col % 3;


	        for (int i = currRow; i < currRow + 3; i++) {
	            for (int j = currCol; j < currCol + 3; j++) {
	                if (question[i][j] == numberToFind)
	                    return true;
	            }
	        }
	        return false;
	    }

	    public static boolean isValid(int[][] question, int numberToFind, int row, int col) {
	        return !isValidInRow(question, numberToFind, row) &&
	                !isValidInColumn(question, numberToFind, col) &&
	                !isValidInBox(question, numberToFind, row, col);
	    }

	    public static boolean solver(int[][] question) {
	        for (int row = 0; row < GRID_SIZE; row++) {
	            for (int col = 0; col < GRID_SIZE; col++) {
	                if (question[row][col] == 0) {
	                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
	                        if (isValid(question, numberToTry, row, col)) {
	                            question[row][col] = numberToTry;

	                            if (solver(question)) return true;
	                            else question[row][col] = 0;
	                        }
	                    }
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
}
