package codewars;

import java.awt.Point;
import java.util.ArrayList;

public class SkyScrapers6 {

	Pot[][] puzzle;
    Block[] blocks;
    ArrayList<Combination> combinations;
	
	public SkyScrapers6(int[] clues) {
		
		combinations = new ArrayList<Combination>();
		blocks = new Block[12]; 
		puzzle = new Pot[6][6];
		
		for (int x=0; x<6;x++) { // vertical
			Block currBlock = new Block(x, 0, clues[x], clues[17-x], true);
			blocks[x] = currBlock;
			for (int y=0; y<6;y++) {
				puzzle[x][y] = new Pot(x, y);
				blocks[x].points[y] = puzzle[x][y];
				puzzle[x][y].blockV = currBlock;
				} 
		}
		for (int y=0; y<6;y++) { // horizontal
			Block currBlock = new Block(0, y, clues[23-y], clues[6+y], false);
			blocks[y+6] = currBlock;
			for (int x=0; x<6;x++) {
				currBlock.points[x] = puzzle[x][y];
				puzzle[x][y].blockH = currBlock;	
			}
		}
		
//		for (int x=0;x<4;x++) {
//			for (int y=0;y<4;y++) {
//				puzzle[x][y] = new Pot(x,y);
//			}
//		}
		
		for (int i1=1; i1<7; i1++) {
			for (int i2=1; i2<7; i2++) {
				if (i1==i2) continue;
				for (int i3=1; i3<7; i3++) {
					if (i1==i3 || i2==i3) continue;
					for (int i4=1; i4<7; i4++) {
						if (i1==i4 || i2==i4 || i3==i4) continue;
						for (int i5=1; i5<7; i5++) {
							if (i1==i5 || i2==i5 || i3==i5 || i4==i5) continue;
							for (int i6=1; i6<7; i6++) {
								if (i1==i6 || i2==i6 || i3==i6 || i4==i6 || i5==i6) continue;
								Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6});
								combinations.add(currComb);
							}
						}
					}
				}
			}
		}
	}

	public void init() {
		for (Block bl: blocks) {
			for (Combination comb: combinations) {
				if ((bl.clue1==0 || bl.clue1==comb.clue1) && (bl.clue2==0 || bl.clue2==comb.clue2)) {
					bl.combinations.add(comb);
				}
			} 
		}
		
		for (Pot[] px: puzzle) {
			for (Pot pxy: px) {
				//for all points
				for (int i=1; i<=6;i++) {
					boolean inBlockV = pxy.blockV.isNumber(i, pxy.y);
					boolean inBlockH = pxy.blockH.isNumber(i, pxy.x);
					if (inBlockV && inBlockH) pxy.addNumber(i);
					else if (inBlockV) pxy.blockV.delByNumber(i, pxy.y);
					else if (inBlockH) pxy.blockH.delByNumber(i, pxy.x);
				}
			}
		}
	}
	
	public boolean step() {
		boolean change=false;
		for (Pot[] px: puzzle) {
			for (Pot pxy: px) {
				//for all points
				for (int j=pxy.numbers.size(); j>0; j--) {
					int i=pxy.numbers.get(j-1);
					boolean inBlockV = pxy.blockV.isNumber(i, pxy.y);
					boolean inBlockH = pxy.blockH.isNumber(i, pxy.x);
					if (inBlockV && inBlockH) {/*all ok*/}
					else {pxy.delByNumber(i);change=true;}
				}
			}
		}
		
		return change;
	}
	
	static int[][] solvePuzzle (int[] clues) {
		 
		SkyScrapers6 ss = new SkyScrapers6(clues);
		ss.init();
		ss.print();
		while (ss.step()) {
			ss.print();
			};
		
		int[][] result = new int[6][6];
		for (int x=0; x<6;x++) { // vertical
			for (int y=0; y<6;y++) {
				result[x][y] = ss.puzzle[x][y].number;
				} 
		}
			
		return result;
	}
	
	public static void main(String[] args) {
	    System.out.println(solvePuzzle(clues));

	}
	
	private static int[] clues = new int[]{ 3, 2, 2, 3, 2, 1,
            1, 2, 3, 3, 2, 2,
            5, 1, 2, 2, 4, 3,
            3, 2, 1, 2, 2, 4};	
	

	class Block{
		Pot[] points;
		int clue1;
		int clue2;
		
		ArrayList<Combination> combinations;
		
		public Block(int x, int y, int clue1, int clue2, boolean vertical) {
			this.clue1 = clue1;
			this.clue2 = clue2;
			this.points = new Pot[6];
			this.combinations = new ArrayList<Combination>();
			
			for (int i=0; i<=5;i++) this.points[i] = puzzle[vertical?x:x+i][vertical?y+i:y];
		}
		
		//is exist Number in any combination on position 
		public boolean isNumber(int number, int position) {
			for (Combination comb: this.combinations) {
				if (comb.pos[position] == number) return true;
			}
			return false;
		}

		public void delByNumber(int number, int position) {
			for (int i=this.combinations.size()-1;i>=0;i--) {
				Combination comb = this.combinations.get(i);
				if (comb.pos[position] == number) this.combinations.remove(i);;
			}
		}
	}
	
	public void print() {
		for (int y=0;y<6;y++) {
			for (int x=0;x<6;x++) {
				System.out.print(puzzle[x][y].numbers+"("+puzzle[x][y].number+")");
			}
			System.out.println();
		}
	}
	
	class Combination{
		int[] pos;
		
		int clue1;
		int clue2;
		
		public Combination(int[] pos) {
			
			this.pos = pos;
			
			this.clue1 = 1;
			this.clue2 = 1;
			
			int maxFloor = pos[0];
			for (int i = 1; i <= 5; i++) {
				if (pos[i] > maxFloor) {
					maxFloor = pos[i];
					this.clue1++;
				}
			}
			maxFloor = pos[5];
			for (int i = 4; i >= 0; i--) {
				if (pos[i] > maxFloor) {
					maxFloor = pos[i];
					this.clue2++;
				}
			}
			
		}
	}
	
	class Pot extends Point {
		
		Block blockV;
		Block blockH;
		
		ArrayList<Integer> numbers;
		int number;
		
		public Pot(int x,int y) {
			super(x,y);
			numbers = new ArrayList<Integer>(); 
		}
		
		public void delByNumber(int i) {
			int index = this.numbers.indexOf(i);
			if (index>=0) this.numbers.remove(index);
			
			this.blockV.delByNumber(i, this.y);
			this.blockH.delByNumber(i, this.x);
			
			if (this.numbers.size()==1) this.number = numbers.get(0);
		}
		
		public void addNumber(int i) {
			this.numbers.add(i);
			if (this.numbers.size()==1) this.number = i;
		}
	}

}
