package codewars;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

	int[][] sudoku; 
	
	public SudokuValidator(int[][] sudoku) {
	    this.sudoku = sudoku;
	}

	public boolean isDuplicates(int x1,int y1,int x2,int y2) {
		Set<Integer> numbers = new HashSet<Integer>();
		for (int x =x1; x<=x2; x++) {
			for (int y =y1; y<=y2; y++) {
				int num = sudoku[x][y];
				if (numbers.contains(num)) return true;
				numbers.add(num);
			}
		}
		return numbers.contains(0);
	}
	
    public static boolean check(int[][] sudoku) {
        SudokuValidator sv = new SudokuValidator(sudoku); 
        for (int i = 0; i<9; i++) {
        	if (sv.isDuplicates(i, 0, i, 8)) return false;
        	if (sv.isDuplicates(0, i, 8, i)) return false;
        }
		for (int x=1; x<8; x+=3) {
			for (int y =1; y<8; y+=3) {
	        	if (sv.isDuplicates(x-1, y-1, x+1, y+1)) return false;
			}
		}
    	return true;
    }
	
	public static void main(String[] args) {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 4},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        
        System.out.println(SudokuValidator.check(sudoku));
	}

}
