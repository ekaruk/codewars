package codewars;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//Time: 2407ms Passed: 8 Failed: 0
public class SkyScrapers7 {

	Pot[][] puzzle;
    Block[] blocks;
    ArrayList<Combination> combinations;
	
	public SkyScrapers7(int[] clues) {
		
		combinations = new ArrayList<Combination>();
		blocks = new Block[14]; 
		puzzle = new Pot[7][7];
		
		for (int x=0; x<7;x++) { // vertical
			Block currBlock = new Block(x, 0, clues[x], clues[20-x], true);
			blocks[x] = currBlock;
			for (int y=0; y<7;y++) {
				puzzle[x][y] = new Pot(x, y);
				blocks[x].points[y] = puzzle[x][y];
				puzzle[x][y].blockV = currBlock;
				} 
		}
		for (int y=0; y<7;y++) { // horizontal
			Block currBlock = new Block(0, y, clues[27-y], clues[7+y], false);
			blocks[y+7] = currBlock;
			for (int x=0; x<7;x++) {
				currBlock.points[x] = puzzle[x][y];
				puzzle[x][y].blockH = currBlock;	
			}
		}
		
//		for (int x=0;x<4;x++) {
//			for (int y=0;y<4;y++) {
//				puzzle[x][y] = new Pot(x,y);
//			}
//		}
		
		for (int i1=1; i1<8; i1++) {
			for (int i2=1; i2<8; i2++) {
				if (i1==i2) continue;
				for (int i3=1; i3<8; i3++) {
					if (i1==i3 || i2==i3) continue;
					for (int i4=1; i4<8; i4++) {
						if (i1==i4 || i2==i4 || i3==i4) continue;
						for (int i5=1; i5<8; i5++) {
							if (i1==i5 || i2==i5 || i3==i5 || i4==i5) continue;
							for (int i6=1; i6<8; i6++) {
								if (i1==i6 || i2==i6 || i3==i6 || i4==i6 || i5==i6) continue;
								for (int i7=1; i7<8; i7++) {
									if (i1==i7 || i2==i7 || i3==i7 || i4==i7 || i5==i7  || i6==i7) continue;
									Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7});
									combinations.add(currComb);
								}
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
				for (int i=1; i<=7;i++) {
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
		
		long starttime = System.nanoTime();
		
		SkyScrapers7 ss = new SkyScrapers7(clues);
		ss.init();
	//	ss.print();
		while (ss.step()) {
	//		ss.print();
			};
		
		int[][] result = new int[7][7];
		for (int x=0; x<7;x++) { // vertical
			for (int y=0; y<7;y++) {
				result[x][y] = ss.puzzle[x][y].number;
				} 
		}
		
		long endtime = System.nanoTime() - starttime;
		long durationInMs = TimeUnit.NANOSECONDS.toMillis(endtime);
//		System.out.println("nano:"+endtime+";durationInMs:"+durationInMs);
		
		return result;
	}
	
	public static int[] getClues(int[][] solution) {
		int[] clues = new int[28];
		for(int x=0;x<7;x++) {
			int clue1=1;
			int clue2=1;
			int maxFloor = solution[x][0];
			for (int i = 1; i <= 6; i++) {
				if (solution[x][i] > maxFloor) {
					maxFloor = solution[x][i];
					clue1++;
				}
			}
			maxFloor = solution[x][6];
			for (int i = 5; i >= 0; i--) {
				if (solution[x][i] > maxFloor) {
					maxFloor = solution[x][i];
					clue2++;
				}
			}		
			clues[x] = clue1;
			clues[20-x] = clue2;
		} 
		for(int y=0;y<7;y++) {
			int clue1=1;
			int clue2=1;
			int maxFloor = solution[0][y];
			for (int i = 1; i <= 6; i++) {
				if (solution[i][y] > maxFloor) {
					maxFloor = solution[i][y];
					clue1++;
				}
			}
			maxFloor = solution[6][y];
			for (int i = 5; i >= 0; i--) {
				if (solution[i][y] > maxFloor) {
					maxFloor = solution[i][y];
					clue2++;
				}
			}		
			clues[27-y] = clue1;
			clues[7+y] = clue2;
		} 
		
		return clues;
	}
	
	public static int[][] mixSolution(int[][] solution){
	    int[][] mixSolution = new int[7][7];
	    List<Integer> mix1 = new ArrayList<Integer>();
	    for (int i = 0; i < 7; i++) {mix1.add(i);}
	    Collections.shuffle(mix1);
	    List<Integer> mix2 = new ArrayList<Integer>();
	    for (int i = 0; i < 7; i++) {mix2.add(i);}
	    Collections.shuffle(mix2);
	    
	    for (int y=0; y<7;y++) { 
			for (int x=0; x<7;x++) { 
				mixSolution[x][y] = solution[mix1.get(x)][mix2.get(y)];	
				} 
			}
	    return mixSolution;
	}
	
	public static boolean checkSolution(int[][] solution1,int[][] solution2) {
	    for (int y=0; y<7;y++) { 
			for (int x=0; x<7;x++) { 
			if (!(solution1[x][y] == solution2[x][y])) return false;	
			} 
		}
		return true;
	}
	
	
	public static void main(String[] args) {
	    
		
	    int[][] solution = new int[7][7];

	    for (int y=0; y<7;y++) { 
			for (int x=0; x<7;x++) { 
			solution[x][y] = 1+(x+y) % 7;	
			} 
		}

		for (int x=0; x<7;x++) { 
			for (int y=0; y<7;y++) { 
				System.out.print(solution[x][y]+",");
				} 
			System.out.println();
		}
		
		int right = 0;
		
		long StartTime = System.nanoTime(); 
		long endtime=0;
		long durationInMs=0;
		
		for (int i=1;i<1000;i++) {
			solution = mixSolution(solution);
			int[] clues = getClues(solution);
		    int[][] newSolution = solvePuzzle(clues);
			
		    boolean check = checkSolution(solution,newSolution);
		    if (check) {
			    System.out.print("solutions["+right+"] = {\n");
		    	
				for (int x=0; x<7;x++) { 
					System.out.print("{");
					for (int y=0; y<7;y++) { 
						System.out.print(solution[x][y]+((y==6)?"":","));
						} 
					if (x==6) {System.out.print("}");} else {System.out.println("}");}
				}
				System.out.println("}");

				System.out.print("clues["+right+"] = {");
				for (int j = 0;j<28;j++)
					System.out.print(clues[j]+((j==27)?"":","));
				System.out.println("}");

				endtime = System.nanoTime() - StartTime;
				durationInMs = TimeUnit.NANOSECONDS.toMillis(endtime);
				System.out.println("GenerateTime["+right+"]:"+durationInMs+"Ms");
				StartTime = System.nanoTime(); 
				
				right++;
		    }
		}
		endtime = System.nanoTime() - StartTime;
		durationInMs = TimeUnit.NANOSECONDS.toMillis(endtime);
		System.out.println("GenerateTime:"+durationInMs+"Ms");
		
/*	    for (int y=0; y<7;y++) { 
			for (int x=0; x<7;x++) { 
				mixSolution[x][y] = solution[mix1.get(x)][mix2.get(y)];	
			} 
		}
	    solution = mixSolution;
*/
		
	    
	    
/*	    
	    //for all places
		for (int y=0; y<7;y++) { 
			for (int x=0; x<7;x++) { 
			    ArrayList<Integer> numbers = new ArrayList<Integer>();
				for (int i=1; i<8;i++) {numbers.add(i);} 
				for (int j=0;j<x;j++) {
					if (numbers.contains(solution[j][y])) numbers.remove(numbers.indexOf(solution[j][y]));
				}
				for (int j=0;j<y;j++) {
					if (numbers.contains(solution[x][j])) numbers.remove(numbers.indexOf(solution[x][j]));
				}
				int rand = rng.nextInt(numbers.size());
				solution[x][y] = numbers.get(rand);
				} 
		}
*/		
		
		
		
		

		for (int x=0; x<7;x++) { 
			for (int y=0; y<7;y++) { 
				System.out.print(solution[x][y]+",");
				} 
			System.out.println();
		}
		for (int i =0;i<28;i++)
		System.out.print(clues[i]+",");
		
		
	    int[][] newSolution = solvePuzzle(clues);
	    
		
		System.out.println(checkSolution(solution,newSolution)+":");
		for (int i =0;i<28;i++)
			System.out.print(clues[i]+",");
		
		
		

	}
	
	private static int[] clues = new int[]{ 7,0,0,0,2,2,3, 0,0,3,0,0,0,0, 3,0,3,0,0,5,0, 0,0,0,0,5,0,4 };	

	class Block{
		Pot[] points;
		int clue1;
		int clue2;
		
		ArrayList<Combination> combinations;
		
		public Block(int x, int y, int clue1, int clue2, boolean vertical) {
			this.clue1 = clue1;
			this.clue2 = clue2;
			this.points = new Pot[7];
			this.combinations = new ArrayList<Combination>();
			
			for (int i=0; i<=6;i++) this.points[i] = puzzle[vertical?x:x+i][vertical?y+i:y];
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
		for (int y=0;y<7;y++) {
			for (int x=0;x<7;x++) {
				System.out.print((puzzle[x][y].numbers.size()==1)?"("+puzzle[x][y].number+")":puzzle[x][y].numbers);
			}
			System.out.println();
		}
		System.out.println("------------------------");
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
			for (int i = 1; i <= 6; i++) {
				if (pos[i] > maxFloor) {
					maxFloor = pos[i];
					this.clue1++;
				}
			}
			maxFloor = pos[6];
			for (int i = 5; i >= 0; i--) {
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
