package codewars;

import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class Finder {

	int optimumLenth;
	Point start;
	Point finish;
	int[][][] weigth;

	public int value(Point p) {
		return weigth[p.x][p.y][0];
	}
	
	public int getLenth(Point p) {
		return weigth[p.x][p.y][1];
	}
	
	public void setLenth(Point p, int len) {
		weigth[p.x][p.y][1] = len;
	}
	
	public void setPoint(Point to, Point from) {
		weigth[to.x][to.y][2] = from.x;
		weigth[to.x][to.y][3] = from.y;
	}
	
	public Point getPoint(Point to) {
		Point from = new Point(weigth[to.x][to.y][2],weigth[to.x][to.y][3]);
		return from;
	}

	// Point is out of field
	public boolean isOut(Point p) {
		return (p.x < 0) || (p.y < 0) || (p.x > (this.weigth.length-1)) || (p.y > (this.weigth[0].length-1));
	}

	public List<String> getWay() {
		return getWay(this.finish);	
	}
	
	public List<String> getWay(Point last) {

		List<String> nameWay = new ArrayList<String>();
		Point curr = new Point(last);

		while (!(curr.equals(this.start))) {
			Point prev = getPoint(curr);
			if (curr.x < prev.x) {
				nameWay.add(0,"up");
			} else if (curr.x > prev.x) {
				nameWay.add(0,"down");
			} else if (curr.y < prev.y) {
				nameWay.add(0,"left");
			} else if (curr.y > prev.y) {
				nameWay.add(0,"right");
			}
			curr = prev;
			
		} 
		
		return nameWay;
	}

	public int getLenth() {
		return getLenth(this.finish);
	}
	
	public Finder(int[][] t, Point start, Point finish) {
		
		this.weigth = new int[t.length][t[0].length][4];
		for (int x = 0; x<t.length; x++) {
			for (int y = 0; y<t[0].length; y++) {
				this.weigth[x][y][0] = t[x][y];
				this.weigth[x][y][1] = Integer.MAX_VALUE;
			}
		}
		
		
		this.start = start;
		this.finish = finish;
		this.optimumLenth = 0;

		int currLenth = 1000;
		Point currPoint = new Point(start);
		while (!(currPoint.equals(finish))) {
			Point nextPoint = new Point(currPoint);
			if (currPoint.x > finish.x) {
				nextPoint.translate(-1, 0);
			} else if (currPoint.x < finish.x) {
				nextPoint.translate(1, 0);
			} else if (currPoint.y > finish.y) {
				nextPoint.translate(0, -1);
			} else if (currPoint.y < finish.y) {
				nextPoint.translate(0, 1);
			} else {
				System.out.println(
						"Error! We must be on finish" + ":;currLenth:" + currLenth);
				}

			currLenth = currLenth + value(nextPoint);
			
			setPoint(nextPoint, currPoint);
			setLenth(nextPoint, currLenth);
			
			currPoint = nextPoint;
		}

		optimumLenth = currLenth;
//		System.out.println(this);
		
//		this.PrintTable();
	}

	private void doStep(Point currPoint, Point nextPoint, int currLenth) {

		if (this.isOut(nextPoint)) {
			return;
		}

		if (!(currPoint == null)) {

			if (currLenth >= this.optimumLenth) {
				return;
			}

			currLenth = currLenth + value(nextPoint);
			if (currLenth >= this.optimumLenth) {
				return;
			}

			if (nextPoint.equals(finish)) {
				if (this.optimumLenth > currLenth) {
					this.optimumLenth = currLenth;
					setLenth(nextPoint, currLenth);
					setPoint(nextPoint, currPoint);
//					System.out.println(this);
				}
				return;
			} else if (currLenth >= getLenth(nextPoint) ) {return;}
			else {setLenth(nextPoint, currLenth);
			      setPoint(nextPoint, currPoint);} 

		}

//		System.out.println("nextPoint:"+nextPoint+":"+currLenth);
		
		Point newPoint0 = new Point(nextPoint);
		newPoint0.translate(0, 1);
		doStep(nextPoint, newPoint0, currLenth);

		Point newPoint1 = new Point(nextPoint);
		newPoint1.translate(0, -1);
		doStep(nextPoint, newPoint1, currLenth);

		Point newPoint2 = new Point(nextPoint);
		newPoint2.translate(1, 0);
		
		doStep(nextPoint, newPoint2, currLenth);

		Point newPoint3 = new Point(nextPoint);
		newPoint3.translate(-1, 0);
		doStep(nextPoint, newPoint3, currLenth);

	}
	
	
	public String toString()
	{
		return this.getLenth()+":"+this.getWay()+":"+this.start+":"+this.finish;		
	}

	public void PrintTable()
	{
		for (int[][] arr : this.weigth)
			
		{   System.out.println(); 
			for (int[] arr2 : arr) {
			System.out.print(arr2[0]+":"+arr2[1]+":"+arr2[2]+","+arr2[3]+";\t");
		}
			
		}
		
		
	}
	
	static List<String> cheapestPath(int[][] t, Point start, Point finish) {
		Finder field = new Finder(t, start, finish);

		field.doStep(null, start, 0);
		
		StringBuilder out = new StringBuilder();
		for (int[] arr : t) 
		{for (int arr2 : arr) 
		  {out.append(arr2);
		   out.append(",");}
		   out.append(";");}
		out.append(";start"+start.x+","+start.y+","+finish.x+","+finish.y+";");
		System.out.print(out.toString());
		//{System.out.println(Arrays.toString(arr));}
//		field.PrintTable();
		System.out.println(field);
		
		return field.getWay();
	}

	public static void main(String[] args) {
//		int[][] tollMap = { { 1, 4, 1 }, { 1, 9, 1 }, { 1, 1, 1 } };
//		Point start = new Point(0, 0), finish = new Point(0, 2);
//
//		System.out.println(cheapestPath(tollMap, start, finish));

		int[][] tollMap2 = { { 1, 9, 1 }, { 2, 9, 1 }, { 2, 1, 1 } };
		Point start2 = new Point(0, 0), finish2 = new Point(0, 2);
		System.out.println(cheapestPath(tollMap2, start2, finish2));
		
		int[][] tollMap3 = { 
		{50,58,98,44,88,31,33,14,30,58,79,40,28,80,35,9,54,30,78,74,42,37,32,16,93,71,86,56,71,78,61,51,2,86,43,},
		{19,87,95,18,59,17,25,14,86,98,4 ,93,20,64,8, 34,29,13,57,81,14,32,36,87,31,57,6,21,91,55,95,99,67,84,31,},
		{68,46,93,17,69,75,89,33,0 ,33,57,72,23,15,22,11,85,67,63,57,29,38,81,57,41,83,40,40,22,9,80,88,27,7,99,},
		{23,64,77,63,48,54,49,52,62,0 ,93,95,20,92,25,24,80,89,74,50,24,57,88,46,89,75,33,48,54,68,18,46,22,30,83,},
		{37,4 ,41,76,85,63,2 ,47,69,44,19,23,25,11,58,66,98,8,49,86,24,34,7,94,46,37,53,59,58,58,88,23,3,7,15,}};
		Point start3 = new Point(1, 17), finish3 = new Point(4, 13);
		System.out.println(cheapestPath(tollMap3, start3, finish3));

//		start 1;17
//		finish 4;13
		

/*		int[][] tollMap2 = { { 1, 19, 1 ,1 ,1}, { 1, 19, 1 ,19,1},{ 1, 19, 1 ,19,1},{ 1, 19, 1 ,19,1}, { 1, 1, 1,19,1 } };
		Point start2 = new Point(0, 0), finish2 = new Point(4, 4);
		System.out.println(cheapestPath(tollMap2, start2, finish2));
*/		
		
	}

}
