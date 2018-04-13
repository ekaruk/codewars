package codewars;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Dinglemouse {

	char grid[][];
	String[][] visitedWays;
	boolean visited[][];
	Point currPos;
	Point prevPos;
	Point start;
	String currDirection;

	public boolean isOut(int x, int y) {
		return (x < 0) || (y < 0) || (x > (this.grid.length - 1)) || (y > (this.grid[0].length - 1));
	}

	public Dinglemouse(char[][] grid) {
		this.grid = grid;// TODO Auto-generated constructor stub
		this.start = getStart();
		this.currPos = new Point(this.start);
		this.currDirection = "";
		this.visited = new boolean[grid.length][grid[0].length];
		this.visitedWays = new String[grid.length][grid[0].length];
		for (int x=0;x<grid.length;x++) {
			for (int y=0;y<grid[0].length;y++) {
				if (!(isOut(x, y))) this.visitedWays[x][y] = new String();}}

	}

	public Point getStart() {
		Point start = new Point(-1,-1);
		int countPoint = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				if (grid[x][y] == 'X')
				{
					if (start.x == -1) {start = new Point(x, y);}
					countPoint++;
				}
					
			}
		}
		return (countPoint == 2) ? start: new Point(-1,-1);
	}

	public char getChar(int x, int y) {
		if (isOut(currPos.x + x, currPos.y + y))
			return ' ';

		return grid[currPos.x + x][currPos.y + y];
	}

	public char getCharXY(int x, int y) {
		if (isOut(x,y))
			return ' ';

		return grid[x][y];
	}

	public ArrayList<String> getWays(){
		 ArrayList<String> ways = new ArrayList<String>();
		 
		 char up = getChar(0, 1);
		 char down = getChar(0, -1);
		 char left = getChar(-1, 0);
		 char right = getChar(1, 0);
		 
		 if (currDirection.equals("up")) {
		     currPos.translate(0, 1);
		  } else if (currDirection.equals("down")) {
		     currPos.translate(0, -1);
		  } else if (currDirection.equals("right")) {
			 currPos.translate(1, 0);
		  } else if (currDirection.equals("left")) {
			 currPos.translate(-1, 0);}
		 
		 char left2  = getChar(-1, 0);
		 char right2 = getChar(1, 0);
		 char down2  = getChar(0, -1);
		 char up2    = getChar(0, 1);
		 
		 
		 if ((!(up2==' ' || up2=='-')) && (
			((currDirection.isEmpty() || currDirection.equals("up")) && (up=='|' || up=='+' || up=='X'))
		 || (((currDirection.equals("right") && (right=='+' || right=='X')) || (currDirection.equals("left") && (left=='+' || left=='X')) ))		 
		    )) {ways.add("up");}

		 if ((!(down2==' ' || down2=='-')) && (
			((currDirection.isEmpty() || currDirection.equals("down")) && (down=='|' || down=='+' || down=='X'))
		 || (((currDirection.equals("right") && (right=='+' || right=='X')) || (currDirection.equals("left") && (left=='+' || left=='X')) ))		 
		    )) {ways.add("down");}

		 if ((!(right2==' ' || right2=='|')) && (
			((currDirection.isEmpty() || currDirection.equals("right")) && (right=='-' || right=='+' || right=='X'))
		 || (((currDirection.equals("up") && (up=='+' || up=='X')) || (currDirection.equals("down") && (down=='+' || down=='X')) ))		 
		    )) {ways.add("right");}


		 if ((!(left2==' ' || left2=='|')) && (
			((currDirection.isEmpty() || currDirection.equals("left")) && (left=='-' || left=='+' || left=='X'))
		 || (((currDirection.equals("up") && (up=='+' || up=='X')) || (currDirection.equals("down") && (down=='+' || down=='X')) ))		 
		    )) {ways.add("left");}


		 return ways;
	}
	
	public String reverseDirection() {
		switch (this.currDirection) {
		case "up": return "down";
		case "down": return "up";
		case "left": return "right";
		case "right": return "left";
		default: return "";
		}
	}
	
	public boolean makeStep() {

		visitedWays[this.currPos.x][this.currPos.y] = visitedWays[this.currPos.x][this.currPos.y]+reverseDirection(); 
		if (!(this.prevPos==null)) {
			visitedWays[this.prevPos.x][this.prevPos.y] = visitedWays[this.prevPos.x][this.prevPos.y]+this.currDirection; 
		}
		
		this.prevPos = new Point(this.currPos);
		
//		if (this.grid[currPos.x][currPos.y]=='*') {return false;} //loop
//		this.grid[currPos.x][currPos.y]='*';
		
//		if (!isOut(currPos.x+1,currPos.y)) this.visited[currPos.x][currPos.y] = true;
		ArrayList<String> ways = getWays();

        char currChar = getChar(0,0);
		
        if (currChar=='X' && (!currPos.equals(start))) return true; 
		
		if (currDirection.isEmpty() && (!(ways.size()==1))) {return false;} // from the start point only 1 way

		if ((ways.size()==0)) {return false;} // no ways
        
		if (currChar=='+') {
			if (ways.size()==3) return false;
			boolean corner = false;
			for (String way: ways) {
				corner = corner 
				|| ((currDirection.equals("up") || currDirection.equals("down")) && (way.equals("right") || way.equals("left"))) 
				|| ((currDirection.equals("right") || currDirection.equals("left")) && (way.equals("up") || way.equals("down"))); 
			}
			if (!corner ) return false;
		}  // in + must be corner

		if (!(currChar=='+') && (!(ways.size()==1))) {return false;} // must be 1 ways
		
		
		ArrayList<String> validWays = new ArrayList<String>();
		
		String visitedWay = visitedWays[this.currPos.x][this.currPos.y];
		
		
		for (String way: ways) {
			if (!visitedWay.contains(currDirection));
			validWays.add(way);
			if (way.equals(currDirection)) {return true;}
		}

		if (validWays.size() == 1) {
			currDirection = validWays.get(0);
			return true;
		}
		
		if (validWays.size() == 0) {
			return false;
		}
		
		
		int maxLen = 0;
		String maxWay = "";
		for (String way: validWays) {
			  int  len=0;
			  char next2 = getChar(0,0);
			  while (!(next2 == ' ')) {
				  len++;
				  if (way.equals("up")) {
					  next2 = getChar(0,len);
				  } else if (way.equals("down")) {
					  next2 = getChar(0,-1*len);
				  } else if (way.equals("right")) {
					  next2 = getChar(len,0);
				  } else if (way.equals("left")) {
					  next2 = getChar(-1*len,0);}
				  if (next2=='X') {
						currDirection = way;
						return true;
					}
			  }
			 if (len > maxLen) 
			 {
				 maxLen=len;
				 maxWay = way;
			 }
		}
		
		currDirection = maxWay;
		return true;
	}

	public static boolean line(final char [][] grid) {
	  	for (int y = grid[0].length-1; y >= 0 ; y--) {
	      for (int x = 0; x < grid.length; x++) {
					System.out.print(grid[x][y]);
				}
	            System.out.println();
			}

		Dinglemouse dm = new Dinglemouse(grid);
	  	
		dm.visited[dm.start.x][dm.start.y] = true;
	  	boolean changes = true;
	  	while (changes) {
	  		changes = false;
		  	for (int y = 0; y < grid[0].length ; y++) {
			      for (int x = 0; x < grid.length; x++) {
			    	char currChar  = dm.getCharXY(x, y);
			    	char currCharR = dm.getCharXY(x+1, y);
			    	char currCharL = dm.getCharXY(x-1, y);
			    	char currCharU = dm.getCharXY(x, y+1);
			    	char currCharD = dm.getCharXY(x, y-1);
			    	if (currChar ==' ' && currCharR =='-') return false;
			    	if (currChar =='+' && currCharR =='+' && currCharL =='+' && currCharU =='+' && currCharD =='+') return false;
			    	if (!(dm.isOut(x, y))  &&  !(grid[x][y]==' ') && !(dm.visited[x][y]) && (dm.visited[Math.min(x+1,grid.length-1)][y] || dm.visited[Math.max(x-1,0)][y] || dm.visited[x][Math.min(y+1,grid[0].length-1)] || dm.visited[x][Math.max(y-1,0)])) 
						{
						dm.visited[x][y] = true;
						changes = true;
						}}}
	  	}

	  	
		
		if (dm.start.x == -1) {return false;}
		
		while (!(grid[dm.currPos.x][dm.currPos.y]=='X') || (dm.currPos.equals(dm.start))) {
			
			System.out.println("("+dm.currPos.x+","+dm.currPos.y+")"+dm.currDirection+";");
			if (!dm.makeStep()) return false;
		}
		
	  	for (int y = 0; y < grid[0].length-1; y++) {
	      for (int x = 0; x < grid.length; x++) {
					if (!((grid[x][y] == ' ') || dm.visited[x][y])) return false;
				}
			}

		
	return true;

	}

	public static void main(String[] args) {
		char[][] grid = makeGrid(new String[] {
		       "        X     ",
		       "  X+++  +-+   ",
		       "   +++--+ |   ",
		       "        +-+   "  });

/*
		char[][] grid = makeGrid(new String[] {
				"   +-----+  ",
				"   |+---+|  ", 
				"   ||+-+||  ",
				"   |||X+||  ",
				"   X|+--+|  ",
				"    +----+  "    });


		char[][] grid = makeGrid(new String[] {
				"   X-----+   ",
			    "         |   ",
			    "   X-----+   ",
			    "         |   ",
			    "   ------+   ",		
				"             "});
		   
*/		
		
//		System.out.println(line(grid));		
				// TODO Auto-generated method stub
		
	    char grid3[][] = makeGrid(new String[] {
	    	    "           ",
	    	    "   X+++    ",  
	    	    "    +++X   ",
	    	    "           ",
	    	    "           "});

	    char grid2[][] = makeGrid(new String[] {
	    	    "           ",
	    	    "    +++X   ",  
	    	    "    +++    ",
	    	    "   X+++    ",
	    	    "           ",
	    	    "           "});
	    
	    
	    System.out.println(line(grid2));		

		

	}

	private static char[][] makeGrid(String[] strings) {
		char[][] grig = new char[strings[0].length()][strings.length];
		for (int y=0; y< strings.length;y++ ) {
			char[] yy = strings[y].toCharArray();
			for (int x=0; x < strings[0].length();x++ ) {
				grig[x][strings.length-1 - y] = yy[x]; 
			}
		}
		return grig;
	}
	
	private static char[][] transpond(char[][] input) {
		char[][] grig = new char[input[0].length][input.length];
		for (int y = 0; y < input.length;y++ ) {
			for (int x=0; x < input[0].length;x++ ) {
				grig[x][y] = input[x][0]; 
			}
		}
		return grig;
	}
	
	  public static int strCount(String str, char letter) {
		    
		  str.
		  int n = 0;
		    for (int i = 0; i < str.length(); i++) { 
		        if (str.charAt(i) == letter) { n++;};
		    }
		    return n;
		  }


	  public static char randomChar() {
		    char[] chars =  "qwertyuiopa".toCharArray();
		    int randomInt = (int)(10*Math.random());
		    return chars[randomInt];
		  }
	  
	  
	  public static String randomWords() {
		  for (int j=0; j<10; j++ ) {
			  int count = 0;
			  StringBuilder str = new StringBuilder();  
			  char check = randomChar();
			  for (int i = 0; i < 30; i++) { 
				  char randChar = randomChar();
				  str.append(randChar);
				  if (check == randChar) {count++;}
	      	  }
			  return str.toString();
    	  }
		  }

}
