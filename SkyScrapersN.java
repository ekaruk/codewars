package codewars;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Time: 2407ms Passed: 8 Failed: 0
public class SkyScrapersN {

	Pot[][] puzzle;
    Block[] blocks;
    ArrayList<Combination> combinations;
	static int size;
    static boolean anySolution;
	
	public SkyScrapersN(int[] clues) {
		
		anySolution = true;
		size = clues.length/4;
		combinations = new ArrayList<Combination>();
		blocks = new Block[size*2]; 
		puzzle = new Pot[size][size];
		
		for (int x=0; x<size;x++) { // vertical
			Block currBlock = new Block(x, 0, clues[x], clues[(size*3-1)-x], true);
			blocks[x] = currBlock;
			for (int y=0; y<size;y++) {
				puzzle[x][y] = new Pot(x, y);
				blocks[x].points[y] = puzzle[x][y];
				puzzle[x][y].blockV = currBlock;
				} 
		}
		for (int y=0; y<size;y++) { // horizontal
			Block currBlock = new Block(0, y, clues[(size*4-1)-y], clues[size+y], false);
			blocks[y+size] = currBlock;
			for (int x=0; x<size;x++) {
				currBlock.points[x] = puzzle[x][y];
				puzzle[x][y].blockH = currBlock;	
			}
		}
		
//		for (int x=0;x<4;x++) {
//			for (int y=0;y<4;y++) {
//				puzzle[x][y] = new Pot(x,y);
//			}
//		}
		
		for (int i1=1; i1<=size; i1++) {
			for (int i2=1; i2<=size; i2++) {
				if (i1==i2) continue;
				for (int i3=1; i3<=size; i3++) {
					if (i1==i3 || i2==i3) continue;
					for (int i4=1; i4<=size; i4++) {
						if (i1==i4 || i2==i4 || i3==i4) continue;
						if (size==4) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4}); combinations.add(currComb);} else { 
						for (int i5=1; i5<=size; i5++) {
							if (i1==i5 || i2==i5 || i3==i5 || i4==i5) continue;
							if (size==5) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5}); combinations.add(currComb);} else { 
							for (int i6=1; i6<=size; i6++) {
								if (i1==i6 || i2==i6 || i3==i6 || i4==i6 || i5==i6) continue;
								if (size==6) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6}); combinations.add(currComb);} else { 
								for (int i7=1; i7<=size; i7++) {
									if (i1==i7 || i2==i7 || i3==i7 || i4==i7 || i5==i7  || i6==i7) continue;
									if (size==7) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7}); combinations.add(currComb);} else { 
									for (int i8=1; i8<=size; i8++) {
										if (i1==i8 || i2==i8 || i3==i8 || i4==i8 || i5==i8  || i6==i8  || i7==i8) continue;
										if (size==8) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8}); combinations.add(currComb);} else { 
										for (int i9=1; i9<=size; i9++) {
											if (i1==i9 || i2==i9 || i3==i9 || i4==i9 || i5==i9  || i6==i9  || i7==i9) continue;
											if (size==9) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8,i9}); combinations.add(currComb);} else { 
											for (int i10=1; i10<=size; i10++) {
												if (i1==i10 || i2==i10 || i3==i10 || i4==i10 || i5==i10  || i6==i10  || i7==i10  || i8==i10  || i9==i10) continue;
												if (size==10) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8,i9,i10}); combinations.add(currComb);} else { 
												for (int i11=1; i11<=size; i11++) {
													if (i1==i11 || i2==i11 || i3==i11 || i4==i11 || i5==i11  || i6==i11 || i7==i11 || i8==i11 || i9==i11 || i10==i11) continue;
													if (size==11) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11}); combinations.add(currComb);} else { 
													for (int i12=1; i12<=size; i12++) {
														if (i1==i12 || i2==i12 || i3==i12 || i4==i12 || i5==i12  || i6==i12 || i7==i12 || i8==i12 || i9==i12 || i10==i12 || i11==i12) continue;
														if (size==12) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12}); combinations.add(currComb);} else { 
														for (int i13=1; i13<=size; i13++) {
															if (i1==i13 || i2==i13 || i3==i13 || i4==i13 || i5==i13  || i6==i13 || i7==i13 || i8==i13 || i9==i13 || i10==i13 || i11==i13 || i12==i13) continue;
															if (size==13) {Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13}); combinations.add(currComb);} else { 
															for (int i14=1; i14<=size; i14++) {
																if (i1==i14 || i2==i14 || i3==i14 || i4==i14 || i5==i14  || i6==i14 || i7==i14 || i8==i14 || i9==i14 || i10==i14 || i11==i14 || i12==i14 || i13==i14) continue;
																Combination currComb = new Combination(new int[]{i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14});
																combinations.add(currComb);}}}}}}}}}}}}}}}}}}}}}}}}}

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
				for (int i=1; i<=size;i++) {
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
		
		if (anySolution && !change) {
			//if we need any solution, remove one combination
			for (Pot[] px: puzzle) {
				for (Pot pxy: px) {
					//for all points
					if (pxy.numbers.size()>1) {pxy.delByNumber(pxy.numbers.get(0));return true;}
				}
			}
		}
		
		return change;
	}
	
	static int[][] solvePuzzle (int[] clues) {
		
		long starttime = System.nanoTime();
		
		SkyScrapersN ss = new SkyScrapersN(clues);
		ss.init();
//		ss.print();
		while (ss.step()) {
//			ss.print();
			};
		
		int[][] result = new int[size][size];
		for (int x=0; x<size;x++) { // vertical
			for (int y=0; y<size;y++) {
				result[x][y] = ss.puzzle[x][y].number;
				} 
		}
		
		long endtime = System.nanoTime() - starttime;
		long durationInMs = TimeUnit.NANOSECONDS.toMillis(endtime);
//		System.out.println("nano:"+endtime+";durationInMs:"+durationInMs);
		
		return result;
	}
	
	public static int[] getClues(int[][] solution) {
		int[] clues = new int[size*4];
		for(int x=0;x<size;x++) {
			int clue1=1;
			int clue2=1;
			int maxFloor = solution[x][0];
			for (int i = 1; i < size; i++) {
				if (solution[x][i] > maxFloor) {
					maxFloor = solution[x][i];
					clue1++;
				}
			}
			maxFloor = solution[x][size-1];
			for (int i = (size-2); i >= 0; i--) {
				if (solution[x][i] > maxFloor) {
					maxFloor = solution[x][i];
					clue2++;
				}
			}		
			clues[x] = clue1;
			clues[(size*3-1)-x] = clue2;
		} 
		for(int y=0;y<size;y++) {
			int clue1=1;
			int clue2=1;
			int maxFloor = solution[0][y];
			for (int i = 1; i < size; i++) {
				if (solution[i][y] > maxFloor) {
					maxFloor = solution[i][y];
					clue1++;
				}
			}
			maxFloor = solution[size-1][y];
			for (int i = (size-2); i >= 0; i--) {
				if (solution[i][y] > maxFloor) {
					maxFloor = solution[i][y];
					clue2++;
				}
			}		
			clues[(size*4-1)-y] = clue1;
			clues[size+y] = clue2;
		} 
		
		return clues;
	}
	
	public static int[][] mixSolution(int[][] solution){
	    int[][] mixSolution = new int[size][size];
	    List<Integer> mix1 = new ArrayList<Integer>();
	    for (int i = 0; i < size; i++) {mix1.add(i);}
	    Collections.shuffle(mix1);
	    List<Integer> mix2 = new ArrayList<Integer>();
	    for (int i = 0; i < size; i++) {mix2.add(i);}
	    Collections.shuffle(mix2);
	    
	    for (int y=0; y<size;y++) { 
			for (int x=0; x<size;x++) { 
				mixSolution[x][y] = solution[mix1.get(x)][mix2.get(y)];	
				} 
			}
	    return mixSolution;
	}
	
	public static boolean checkSolution(int[][] solution1,int[][] solution2) {
	    for (int y=0; y<size;y++) { 
			for (int x=0; x<size;x++) { 
			if (!(solution1[x][y] == solution2[x][y])) return false;	
			} 
		}
		return true;
	}
	
	public static boolean checkClues(int[][] solution,int[] clues) {
		int[] newClues = getClues(solution);
		for (int y=0; y<size*4;y++) { 
			if (!(newClues[y] == clues[y])) return false;	
		}
		return true;
	}
	
	public static void printSolution(int[][] solution,int num) {
	    System.out.print("solutions["+num+"] = {\n");
		for (int y=0; y<size;y++) { 
			System.out.print("{");
			for (int x=0; x<size;x++) { 
				System.out.print(solution[x][y]+((x==(size-1))?"":","));
				} 
			if (y==(size-1)) {System.out.print("}");} else {System.out.println("},");}
		}
		System.out.println("}");
	}
	
	public static void printClues(int[] clues,int num) {
		System.out.print("clues["+num+"] = {");
		for (int j = 0;j<(size*4);j++)
			System.out.print(clues[j]+((j==(size*4-1))?"":","));
		System.out.println("}");
	}
	
	public static String durationTime(long duration) {
		String result="";

		if (TimeUnit.NANOSECONDS.toMillis(duration)>1) {
			result = result+" "+TimeUnit.NANOSECONDS.toMillis(duration)+" Millis";
		} else {
			result = result+" "+duration+" NanoSeconds";
		} 
		
		
		if (TimeUnit.NANOSECONDS.toSeconds(duration)>1) {
			result = result+"; "+TimeUnit.NANOSECONDS.toSeconds(duration)+" Seconds";
		}
		if (TimeUnit.NANOSECONDS.toMinutes(duration)>1) {
			result = result+"; "+TimeUnit.NANOSECONDS.toMinutes(duration)+" Minutes";
		}
		if (TimeUnit.NANOSECONDS.toDays(duration)>1) {
			result = result+"; "+TimeUnit.NANOSECONDS.toDays(duration)+" Days";
		}

		return result;
	} 
	
	
	public static void main(String[] args) {
	    
		size=11;
		
	    int[][] solution = new int[size][size];

	    for (int y=0; y<size;y++) { 
			for (int x=0; x<size;x++) { 
			solution[x][y] = 1+(x+y) % size;	
			} 
		}

		for (int x=0; x<size;x++) { 
			for (int y=0; y<size;y++) { 
				System.out.print(solution[x][y]+",");
				} 
			System.out.println();
		}
		
		int right = 0;
		int attempt =0;
		
		long StartTime = System.nanoTime(); 
		long endtime=0;
		long durationInMsZero=0;
		long durationInMs=0;
		long durationInMsSolving=0;
		long durationInMsSolvingZero=0;
		long startSolvingInMs=0;
		
		int att = 10;
		for (int i=1;i<att;i++) {
			solution = mixSolution(solution);
			int[] clues = getClues(solution);
			startSolvingInMs = System.nanoTime();
			int[][] newSolution = solvePuzzle(clues);
			durationInMsSolving = System.nanoTime()-startSolvingInMs;
		    boolean check = checkSolution(solution,newSolution);
		    boolean checkSol;
		    if (anySolution) {checkSol = checkClues(newSolution, clues);} 
//			System.out.println("//SolvingTime["+i+"]:"+TimeUnit.NANOSECONDS.toMillis(durationInMsSolving)+":"+check);

		    		    
		    attempt++;
		    if (check) {
		    	printSolution(solution,right);
		    	
		    	printClues(clues,right);
				
				endtime = System.nanoTime() - StartTime;
				durationInMs = endtime;
//				System.out.println("//GenerateTime["+right+"]:"+durationInMs+"Ms; attempts:"+attempt);

				for (int c1=0; c1<(size*4); c1++) {
					int[] cluetest = new int[size*4];
					for (int c=0; c<(size*4); c++) {cluetest[c] = clues[c];}
					cluetest[c1] = 0;
					if (c1==(size*4-1)) startSolvingInMs = System.nanoTime();
					int[][] newSolution2 = solvePuzzle(cluetest);
					if (c1==(size*4-1)) durationInMsSolvingZero = System.nanoTime()-startSolvingInMs;
					boolean check2 = checkSolution(solution,newSolution2);
					if (check2) clues = cluetest; 
				}
				System.out.print("cluesWithZeroes["+right+"] = {");
				for (int j = 0;j<(size*4);j++)
				System.out.print(clues[j]+((j==(size*4-1))?"":","));
				System.out.println("}");

				
				endtime = System.nanoTime() - StartTime;
				durationInMsZero = endtime;
				System.out.println("//GenerateTime["+right+"]:"+durationTime(durationInMsZero)+"(without Zero "+durationTime(durationInMs)+"); solutionTime:"+durationTime(durationInMsSolvingZero)+"(without Zero "+durationTime(durationInMsSolving)+"); attempts:"+attempt);
				StartTime = System.nanoTime(); 
				
				attempt = 0;
				right++;
		    } else if (anySolution) {System.out.println("//SolvingTime["+i+"]:"+durationTime(durationInMsSolving)+":"+check);} 

		}
		endtime = System.nanoTime() - StartTime;
		durationInMs = endtime;
		System.out.println("GenerateTime:"+durationTime(durationInMs));

	}
	
//	private static int[] clues = new int[]{ 7,0,0,0,2,2,3, 0,0,3,0,0,0,0, 3,0,3,0,0,5,0, 0,0,0,0,5,0,4 };	

	class Block{
		Pot[] points;
		int clue1;
		int clue2;
		
		ArrayList<Combination> combinations;
		
		public Block(int x, int y, int clue1, int clue2, boolean vertical) {
			this.clue1 = clue1;
			this.clue2 = clue2;
			this.points = new Pot[size];
			this.combinations = new ArrayList<Combination>();
			
			for (int i=0; i<size;i++) this.points[i] = puzzle[vertical?x:x+i][vertical?y+i:y];
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
		for (int y=0;y<size;y++) {
			for (int x=0;x<size;x++) {
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
			for (int i = 1; i < size; i++) {
				if (pos[i] > maxFloor) {
					maxFloor = pos[i];
					this.clue1++;
				}
			}
			maxFloor = pos[size-1];
			for (int i = (size-2); i >= 0; i--) {
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
			if (this.numbers.size()==1) {this.number = i;} else {this.number = 0;} ;
		}
	}

}
