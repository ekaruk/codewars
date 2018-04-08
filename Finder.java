package codewars;

import java.util.List;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.Point;

public class Finder {

/*	
	int optimumLenth;
	Point start;
	Point finish;
	int[][][] weigth;
	
	int countDoStep;

	public int value(Point p) {
		return weigth[p.x][p.y][0];
	}
	
	public int getLenth(Point p) {
		return weigth[p.x][p.y][1];
	}
	
	public void setLenth(Point p, int len) {
		weigth[p.x][p.y][1] = len;
	}

	
	//Point to - current point
	//Point from - best point to start	
	public void setPoint(Point to, Point from) {
		weigth[to.x][to.y][4] = from.x;
		weigth[to.x][to.y][5] = from.y;
	}
	
	//Point to - current point
	//Point from - best point to finish
	public void setPointToFinish(Point to, Point from) {
		weigth[to.x][to.y][6] = from.x;
		weigth[to.x][to.y][7] = from.y;
	}

	public Point getPointToFinish(Point p) {
		return new Point(weigth[p.x][p.y][6],weigth[p.x][p.y][7]);
	}
	
//	public boolean hasPointToFinish(Point p) {
//		return weigth[p.x][p.y][5] < Integer.MAX_VALUE;
//	}
	
	public Point getPoint(Point to) {
		Point from = new Point(weigth[to.x][to.y][4],weigth[to.x][to.y][5]);
		return from;
	}
	
	public int getMaxDistanceToFinish(Point p) {
		return weigth[p.x][p.y][2];
	}

	public int getMinDistanceToFinish(Point p) {
		return weigth[p.x][p.y][3];
	}
	
	public void setMinDistanceToFinish(Point p, int dist) {
		weigth[p.x][p.y][3] = dist;
	}
	
	
	public void updateDistanceToFinish() {
		Point currPoint = finish;
		int currDistance = 0;
		while (!(currPoint.equals(start))) {
			Point prevPoint = getPoint(currPoint);
			weigth[prevPoint.x][prevPoint.y][2] = currDistance;
			currDistance = currDistance + value(prevPoint);
			currPoint = prevPoint;
		}
	}

	// Point is out of field
	public boolean isOut(Point p) {
		return (p.x < 0) || (p.y < 0) || (p.x > (this.weigth.length-1)) || (p.y > (this.weigth[0].length-1));
	}

	public List<String> getWay() {
		return getWay(this.finish);	
	}
	
	public String calcWay(String way) {
		Point curr = new Point(start);
		String[] steps = way.split(",");
		int len = 0;
		StringBuilder fullWay = new StringBuilder("");
		for (String step : steps) {
			step = step.trim();
			if (step.equals("up")) {
				curr.translate(-1, 0);
			} else if (step.equals("down")) {
				curr.translate(1, 0);
			} else if (step.equals("left")) {
				curr.translate(0, -1);
			} else if (step.equals("right")) {
				curr.translate(0, 1);}
			
			int currLen;
			if (isOut(curr)) {currLen = 999999;} else {currLen = value(curr);}
			
			len = len+currLen;
			fullWay.append(step).append(":"+currLen+";");
		}
		fullWay.append(len);
		return fullWay.toString();
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
	
	// distance in steps from any Point to (x,y)
	public int distanse(Point p, int x, int y) {
		return Math.abs(p.x-x) + Math.abs(p.y - y);
	}


	
	
	public Finder(int[][] t, Point start, Point finish) {
		
		this.weigth = new int[t.length][t[0].length][8];
		for (int x = 0; x<t.length; x++) {
			for (int y = 0; y<t[0].length; y++) {
				this.weigth[x][y][0] = t[x][y];
				this.weigth[x][y][1] = 100*distanse(start,x,y);
				this.weigth[x][y][2] = 100*distanse(finish,x,y);
//				this.weigth[x][y][5] = Integer.MAX_VALUE;
			}
		}
			
		this.start = start;
		this.finish = finish;
		// Set distanse as maximum possible
		this.optimumLenth = 100*distanse(start, finish.x, finish.y);

/*		int currLenth = 1000;
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

	private int doStep(Point currPoint, Point nextPoint, int currLenth) {
		countDoStep++;
		
		
		if (this.isOut(nextPoint)) {
			return Integer.MAX_VALUE;
		}

		
		int minDistanceToFinish = getMinDistanceToFinish(nextPoint);
		if ((currLenth + minDistanceToFinish) >= this.optimumLenth) {
			return minDistanceToFinish + value(nextPoint);
		} 
//		if (hasPointToFinish(nextPoint)) {
//			return getDistanceToFinish(nextPoint) + value(nextPoint);
//		}
		
		if (!(currPoint == null)) {

//			if (currLenth >= this.optimumLenth) {
//				return minDistanceToFinish + value(nextPoint);
//			}

			currLenth = currLenth + value(nextPoint);
//			if (currLenth >= this.optimumLenth) {
//				return Integer.MAX_VALUE;
//			}

			if (nextPoint.equals(finish)) {
				if (this.optimumLenth > currLenth) {
					this.optimumLenth = currLenth;
					setLenth(nextPoint, currLenth);
					setPoint(nextPoint, currPoint);
					updateDistanceToFinish();
					setPointToFinish(finish, currPoint);
//					System.out.println(this);
				}
				return value(nextPoint);
			} else if (currLenth >= getLenth(nextPoint) ) {return minDistanceToFinish + value(nextPoint);}
			else {setLenth(nextPoint, currLenth);
			      setPoint(nextPoint, currPoint);} 

		}

//		System.out.println("nextPoint:"+nextPoint+":"+currLenth);
		
		ArrayList<Point> variants = new ArrayList<Point>();
		Point newPoint;
		newPoint = new Point(nextPoint.x,nextPoint.y+1);
		if (!(isOut(newPoint)) && !(newPoint.equals(currPoint))) {variants.add(newPoint);}
//		if (!(isOut(newPoint))) {variants.add(newPoint);}

		newPoint = new Point(nextPoint.x,nextPoint.y-1);
		if (!(isOut(newPoint)) && !(newPoint.equals(currPoint))) {variants.add(newPoint);}

		newPoint = new Point(nextPoint.x+1,nextPoint.y);
		if (!(isOut(newPoint)) && !(newPoint.equals(currPoint))) {variants.add(newPoint);}

		newPoint = new Point(nextPoint.x-1,nextPoint.y);
		if (!(isOut(newPoint)) && !(newPoint.equals(currPoint))) {variants.add(newPoint);}

		
		Collections.sort(variants, getByDistanseToFinish());
		
		minDistanceToFinish = Integer.MAX_VALUE;
		Point minPointToFinish = new Point();
		for (Point newPoint0 : variants) {
			int distanceToFinish = doStep(nextPoint, newPoint0, currLenth);
			if (distanceToFinish < minDistanceToFinish) {
				minDistanceToFinish = distanceToFinish;
				minPointToFinish = newPoint0;
			}
			setMinDistanceToFinish(newPoint0, distanceToFinish);
		}
		
//		if (minDistanceToFinish < Integer.MAX_VALUE) {
//			setPointToFinish(nextPoint, minPointToFinish);
//			setMinDistanceToFinish(nextPoint, minDistanceToFinish + value(nextPoint));
		
			return minDistanceToFinish + value(nextPoint);
//		} else {return Integer.MAX_VALUE;}
			
		
/*		
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
	
	public Comparator<Point> getByDistanseToFinish()
	{   
	 Comparator<Point> comp = new Comparator<Point>(){
	     @Override
	     public int compare(Point p1, Point p2)
	     {
	         return getMaxDistanceToFinish(p1) - getMaxDistanceToFinish(p2);
	     }

	    }; 
	    return comp;
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
*/	
	
/*	
	public static void way(int[][] t, Point start, Point finish,String steps) {
		Finder field = new Finder(t, start, finish);
		System.out.println(field.calcWay(steps));
	}
*/

	HashMap<Point, LinkedList<Way>> points;
	Point[][] idPoints;
	int[][] roads;
	Way[][] waysFromStart;
	Point start;
	Point finish;

	// Point is out of field
	public boolean isOut(int x,int y) {
		return (x < 0) || (y < 0) || (x > (this.idPoints.length-1)) || (y > (this.idPoints[0].length-1));
	}
	
	public Way getWayToFinish() {
		return waysFromStart[finish.x][finish.y];
	}
	
	public Point getPoint(int x, int y) {
		
		if (isOut(x, y)) {return null;}
		
		Point result = idPoints[x][y];
		if (result==null) {
			result = new Point(x,y);
			idPoints[x][y] = result;
		}
		return result;
	}
	
	public Way getWay(Point p1, Point p2) {
		Point pMin = minPoint(p1, p2);
		Point pMax = maxPoint(p1, p2);
		
		Way way = null;
		if (points.containsKey(pMin))
		{
			LinkedList<Way> ways = points.get(pMin);
			for (Way w: ways) {
				if (w.point2.equals(pMax)) {
					way = w;
					break;
				}
			}
		}
		return way;
	}
	
	public Point maxPoint(Point p1, Point p2) {
		Point pMax;
		if (p1.x < p2.x || (p1.x == p2.x && p1.y < p2.y)) {
			pMax = p2; 
		} else {pMax = p1;}
		return pMax;
	}

	public Point minPoint(Point p1, Point p2) {
		Point pMin;
		if (p1.x < p2.x || (p1.x == p2.x && p1.y < p2.y)) {
			pMin = p1; 
		} else {pMin = p2;}
		return pMin;
	}
	
	
	public LinkedList<Way> initListRoad(Point point) {
		LinkedList<Way> ways = new LinkedList<Way>();
		
		Point point1 = getPoint(point.x+1,point.y);
		if (!(point1==null)) {
			Way way1 = new Way(point,point1,roads[point1.x][point1.y]);
			ways.add(way1);
		}
		Point point2 = getPoint(point.x,point.y+1);
		if (!(point2==null)) {
			Way way2 = new Way(point,point2,roads[point2.x][point2.y]);
			ways.add(way2);
		}

		Point point3 = getPoint(point.x-1,point.y);
		if (!(point3==null)) {
			Way way3 = getWay(point3, point);
			ways.add(way3);
		}
		Point point4 = getPoint(point.x,point.y-1);
		if (!(point4==null)) {
			Way way4 = getWay(point4, point);
			ways.add(way4);
		}		
		
		return ways;
	}
	
	public Finder(int[][] t, Point start0, Point finish0) {

		idPoints = new Point[t.length][t[0].length];
		waysFromStart = new Way[t.length][t[0].length];
		points = new HashMap<Point, LinkedList<Way>>();
		start = start0;
		finish = finish0;
		
		idPoints[start.x][start.y] = start;
		idPoints[finish.x][finish.y] = finish;
		roads = t;
		
		for (int x = 0; x<t.length; x++) {
			for (int y = 0; y<t[0].length; y++) {
					Point point = getPoint(x, y);
					LinkedList<Way> ways = initListRoad(point);
					points.put(point, ways);
			}
		}
		
		initFirstWay();
		
		getBestWay();
		
	}
	
	
	
	
	public Point getNextPoint(Point p1,Point p2) {
		if (p1.x > p2.x) {return getPoint(p1.x-1, p1.y);}
		else if (p1.x < p2.x) {return getPoint(p1.x+1, p1.y);}
		else if (p1.y > p2.y) {return getPoint(p1.x, p1.y-1);}
		else {return getPoint(p1.x, p1.y+1);}
	}
	
	public void getBestWay() {

		LinkedList<Way> startWays = points.get(start);
		LinkedList<Way> newWays = new LinkedList<Way>();
		Way wayToFinis = getWayToFinish();
		
		boolean modificated = true;
		while (modificated) {
			modificated = false;
			for (Way way : startWays) {
				//take all ways of end point of this way
				Point currEndPoint = way.getEndPoint(start);
				LinkedList<Way> currWays = points.get(currEndPoint);
				for (Way pointsWay : currWays) {
					if ((way.len + pointsWay.len) < wayToFinis.len) {
						Point endPoint = pointsWay.getEndPoint(currEndPoint);
						Way oldWay = waysFromStart[endPoint.x][endPoint.y];
						if (oldWay == null) {
							Way newWay = new Way(way,pointsWay); 
							waysFromStart[endPoint.x][endPoint.y] = newWay;
							newWays.add(newWay);
							modificated = true;
						} else if ((way.len+pointsWay.len) < oldWay.len){
							oldWay.len = way.len+pointsWay.len;
							oldWay.way1 = way;
							oldWay.way2 = pointsWay;
							modificated = true;
						}
					}
				}
			}
			for (Way w: newWays) {
				startWays.add(w);
			}
			newWays.clear();
		}
		
		points.put(start,startWays);
	}
	
	public void initFirstWay() {
		
		LinkedList<Way> startWays = points.get(start);

		Point currPoint = getNextPoint(start, finish);
		Way currWay = getWay(start, currPoint);
		while (!(currPoint.equals(finish))) {
			Point nextPoint = getNextPoint(currPoint, finish);
			Way nextWay = getWay(currPoint,nextPoint);
			Way newWay = new Way(currWay,nextWay);
			startWays.add(newWay);	
			
			currPoint = nextPoint;
			currWay = newWay;
		}
		
		points.put(start,startWays);
		
		for (Way way : startWays) {
			Point endPoint = (start.equals(way.point1)) ? way.point2 : way.point1;
			waysFromStart[endPoint.x][endPoint.y] = way;
		}

	}
	
	
	
	static List<String> cheapestPath(int[][] t, Point start, Point finish) {
		Finder field = new Finder(t, start, finish);

		Way finishWay = field.waysFromStart[finish.x][finish.y];
		
		ArrayList<String> fin = new ArrayList<String>();
		
		fin.add(String.valueOf(finishWay.len));
		fin.add(finishWay.toString());
		
		return finishWay.getWay(start);
	}
	
	public static String getNameOfWay(Point start, Point finish) {
		String ret = ""; 
		if (start.x < finish.x) {
			ret = "down";
		} else if (start.x > finish.x) {
			ret = "up";
		} else if (start.y < finish.y) {
			ret = "right";
		} else if (start.y > finish.y) {
			ret = "left";
		}
		
		ret = ret +"("+finish.x+","+finish.y+")";
		return ret;
	}
	
	public static void main(String[] args) {
//		int[][] tollMap = { { 1, 4, 1 }, { 1, 9, 1 }, { 1, 1, 1 } };
//		Point start = new Point(0, 0), finish = new Point(0, 2);
//
//		System.out.println(cheapestPath(tollMap, start, finish));

		int[][] tollMap2 = { { 1, 9, 1 }, { 2, 9, 1 }, { 2, 1, 1 } };
		Point start2 = new Point(0, 0), finish2 = new Point(0, 2);
		System.out.println(cheapestPath(tollMap2, start2, finish2));
/*
		int[][] tollMap3 = { { 1, 9, 1 }, { 2, 9, 1 }, { 2, 1, 1 } };
		Point start3 = new Point(0, 2), finish3 = new Point(0, 0);
		System.out.println(cheapestPath(tollMap3, start3, finish3));
*/		
		//way(tollMap2, start2, finish2,"down, down, right, right, up, up");
		
		int[][] tollMap3 = { 
		{50,58,98,44,88,31,33,14,30,58,79,40,28,80,35,9,54,30,78,74,42,37,32,16,93,71,86,56,71,78,61,51,2,86,43,},
		{19,87,95,18,59,17,25,14,86,98,4 ,93,20,64,8, 34, 29 ,13,57,81,14,32,36,87,31,57,6,21,91,55,95,99,67,84,31,},
		{68,46,93,17,69,75,89,33,0 ,33,57,72,23,15,22,11,85,67,63,57,29,38,81,57,41,83,40,40,22,9,80,88,27,7,99,},
		{23,64,77,63,48,54,49,52,62,0 ,93,95,20,92,25,24,80,89,74,50,24,57,88,46,89,75,33,48,54,68,18,46,22,30,83,},
		{37,4 ,41,76,85,63,2 ,47,69,44,19,23,25,11,58,66,98,8,49,86,24,34,7,94,46,37,53,59,58,58,88,23,3,7,15,}};
		Point start3 = new Point(1, 17), finish3 = new Point(4, 13);
		System.out.println(cheapestPath(tollMap3, start3, finish3));

		int[][] tollMap4 = {
		{14,38,6,4,21,1,4,63,83,37,94,88,18,92,3,70,90,53,74,85,52,47,56,11,33,95,13,10,30,89,83,0,41,84,90,47,20,8,91,85,61,94,68,51,73,15,78,30,27,45,78,35,70,19,6,62,3,12,},
		{19,62,45,52,68,84,5,71,26,85,74,23,0,13,83,65,31,42,32,99,3,6,40,53,56,2,12,26,35,33,2,62,85,1,5,66,98,36,28,48,28,46,30,62,55,11,16,74,65,69,62,80,43,81,14,52,46,31,},
		{11,58,72,23,82,12,16,86,38,27,89,42,21,47,59,55,60,57,48,32,16,14,5,18,80,78,21,99,39,70,28,82,42,85,5,94,39,98,19,30,5,11,13,21,84,38,68,88,14,16,58,6,55,4,12,51,78,37,},
		{31,56,48,8,80,10,46,5,86,49,21,45,2,73,74,38,61,22,65,43,86,21,41,74,6,35,97,65,37,60,43,37,27,80,42,42,52,40,23,87,51,73,84,25,5,78,28,80,42,63,32,42,57,1,36,33,64,18,},
		{18,40,48,30,27,34,50,72,71,50,32,31,4,35,35,28,68,76,37,71,72,49,28,14,9,38,33,1,88,44,16,87,70,89,9,65,19,75,28,87,21,83,90,36,21,41,71,64,73,8,41,21,49,84,23,61,24,83,},
		{39,4,12,54,26,78,46,41,12,85,7,38,69,84,2,70,20,7,79,17,35,79,51,17,5,79,88,63,22,66,29,94,18,68,85,64,81,67,32,95,58,17,94,49,72,25,84,85,93,92,82,59,11,55,47,30,17,15,},
		{24,68,59,72,28,86,86,66,58,71,26,22,13,48,98,70,72,30,34,57,29,87,25,14,25,94,19,23,51,71,6,57,78,80,88,79,13,52,27,77,89,13,5,32,21,27,21,51,23,11,71,25,47,1,45,70,69,12,},
		{2,71,16,53,19,15,46,11,42,44,36,55,94,27,70,3,78,60,76,72,2,14,84,73,39,71,29,6,88,43,1,27,29,84,70,87,57,23,99,30,40,73,17,3,38,85,14,9,88,62,27,48,89,41,13,36,70,85,},
		{90,51,22,26,66,17,12,12,98,18,82,21,5,31,36,8,88,17,7,30,4,83,69,74,84,98,74,20,94,32,3,69,24,71,75,35,83,4,31,29,22,81,20,39,41,66,64,8,46,8,26,51,69,32,51,84,0,19,},
		{25,64,90,49,91,41,46,82,68,58,45,13,21,70,51,84,18,75,87,9,69,93,57,92,34,76,73,26,86,60,48,94,15,93,10,63,78,0,91,89,64,66,39,60,35,59,78,8,73,77,14,11,55,75,46,90,55,29,},
		{11,16,52,7,34,88,59,26,53,77,36,95,77,63,27,84,6,26,97,4,93,92,59,31,2,80,37,30,85,98,85,29,52,81,11,0,23,95,54,57,72,61,96,80,39,21,78,47,17,60,87,57,42,16,54,83,96,75,},
		{89,51,99,35,61,53,55,34,81,71,0,10,22,51,63,37,51,1,5,50,88,64,5,69,81,71,59,41,94,73,52,62,77,48,46,33,30,44,52,68,91,40,15,42,53,7,81,27,49,8,39,66,93,95,41,92,6,68,},
		{54,43,21,15,57,83,94,47,91,96,21,43,4,29,4,95,6,9,10,19,26,33,54,31,53,18,5,59,22,37,51,78,93,29,85,22,48,87,39,59,71,34,8,98,59,18,19,16,32,27,22,64,51,40,18,9,71,85,},
		{1,97,24,79,4,68,2,35,13,41,16,22,86,52,92,84,17,16,96,63,39,4,16,67,4,97,99,52,80,74,41,56,4,73,78,24,85,46,79,85,98,22,38,56,93,66,76,64,99,34,72,84,89,77,0,69,96,95,},
		{64,41,21,27,43,66,38,58,44,46,57,60,54,80,90,98,97,85,69,6,85,41,66,76,95,25,69,13,66,18,98,42,15,91,73,78,65,11,32,81,3,86,89,74,62,4,75,17,54,81,22,86,53,48,15,85,11,49,},
		{83,31,68,29,71,37,54,90,76,33,11,60,33,50,31,53,89,81,78,4,9,58,24,88,52,56,79,56,13,98,63,14,38,71,13,3,10,9,97,86,61,82,25,13,93,68,99,13,41,40,4,52,2,14,24,82,46,79,},
		{86,45,52,35,1,59,71,29,81,62,11,46,27,75,18,99,63,32,0,68,12,35,69,66,15,17,48,64,78,2,61,75,12,8,93,31,85,59,54,18,39,45,66,31,65,4,33,27,61,47,19,54,25,35,92,33,71,14,},
		{98,98,33,42,53,88,71,0,55,3,84,63,11,4,5,10,64,49,22,79,31,87,67,66,61,69,27,24,23,81,19,83,61,81,33,44,94,70,89,14,50,23,58,36,41,5,62,37,48,48,27,2,23,17,14,4,34,74,},
		{29,80,42,80,29,33,59,48,11,30,43,6,93,41,78,54,90,76,74,24,98,66,83,67,63,34,3,87,88,41,27,1,4,6,13,11,56,59,62,1,87,22,6,38,67,50,8,89,48,56,84,33,69,74,55,67,51,91,},
		{69,43,82,22,38,29,27,65,3,3,96,86,30,97,76,89,33,80,74,7,47,95,21,84,68,4,35,65,62,92,34,5,74,31,4,10,77,59,87,7,62,23,13,29,21,11,66,7,24,7,48,11,31,62,1,20,49,38,},
		{52,25,59,13,49,24,73,38,23,13,12,82,87,62,8,75,11,92,13,64,90,80,73,59,32,28,88,8,47,84,49,33,60,53,55,27,15,16,20,72,45,60,81,44,52,39,5,23,30,64,9,84,36,16,34,52,54,22,},
		{67,41,68,15,98,11,15,2,64,14,57,33,85,66,68,82,70,95,98,78,55,51,22,13,25,35,83,39,81,23,50,13,3,78,67,67,61,19,53,2,20,53,83,11,73,6,46,60,95,43,0,11,56,96,79,85,78,72,},
		{32,88,0,14,4,58,29,63,1,2,46,9,34,81,75,73,52,37,40,94,48,33,38,37,22,86,78,88,69,38,19,27,26,1,31,99,12,68,91,48,69,30,20,29,90,81,11,33,12,1,82,77,47,90,5,65,93,91,},
		{79,41,24,96,36,10,42,0,20,49,94,44,11,28,52,41,85,91,97,17,74,39,56,13,10,71,5,6,83,59,41,38,23,66,49,62,26,91,35,39,21,80,42,5,36,24,85,88,35,11,83,66,63,5,56,11,57,36,},
		{28,69,12,42,89,99,16,27,74,80,83,98,10,76,99,64,4,92,47,87,37,52,31,18,81,86,83,99,23,25,67,64,63,52,13,19,42,13,92,76,28,95,1,57,75,0,74,50,16,84,7,46,11,59,12,79,77,89,},
		{88,18,82,92,56,59,41,62,85,97,39,90,40,17,11,50,32,78,97,19,58,44,14,25,8,61,64,6,68,54,12,18,0,62,28,68,20,70,84,60,88,29,64,64,52,19,32,54,82,98,24,73,82,80,87,96,22,36,},
		{92,0,5,79,10,45,51,87,10,97,58,88,3,85,26,15,99,25,72,40,37,18,57,76,61,98,27,69,77,96,30,90,78,71,64,42,80,71,95,48,23,23,43,77,61,53,40,71,37,39,87,62,79,66,72,65,16,58,},
		{93,43,57,78,74,28,18,62,93,67,32,95,82,70,36,66,11,25,68,77,5,80,57,31,42,36,7,75,40,21,96,49,4,2,59,29,85,21,53,28,52,35,67,81,67,76,96,29,90,36,95,36,57,98,40,37,49,22,},
		{95,76,12,19,25,98,72,22,84,77,68,42,31,15,52,57,94,2,82,35,44,30,99,86,38,35,97,83,13,37,36,13,5,86,0,60,72,99,48,39,46,71,85,3,19,37,6,89,91,25,60,95,63,59,47,16,69,9,},
		{36,89,14,79,83,39,75,15,44,87,81,82,13,94,26,42,23,51,50,64,10,99,36,71,64,15,46,18,50,36,7,62,30,81,91,22,37,10,11,90,13,42,15,85,71,30,45,3,30,79,27,82,56,75,99,83,62,85,},
		{97,69,24,92,38,55,35,74,97,28,83,84,75,53,13,79,79,46,78,79,77,57,31,19,99,8,51,29,10,45,71,62,83,85,33,63,13,79,12,73,88,68,33,83,97,28,67,66,68,50,17,41,24,60,88,18,49,54,},
		{93,50,48,26,78,17,88,50,36,25,40,27,52,54,15,77,19,22,39,27,96,60,23,1,82,92,40,97,84,45,6,76,36,56,40,74,99,56,21,53,46,75,87,98,51,40,50,81,52,46,67,60,42,93,91,91,15,52,},
		{95,35,64,32,25,82,5,85,64,67,10,35,58,99,55,31,10,52,6,64,38,6,5,25,24,24,32,49,61,12,0,14,75,63,66,30,67,11,27,88,63,92,48,48,46,51,83,67,33,50,52,42,19,77,86,64,77,69,},
		{18,59,0,36,35,1,18,12,5,72,79,85,48,17,52,10,60,77,93,48,81,74,13,91,85,9,96,90,47,16,56,86,81,12,49,54,98,20,54,46,49,42,58,87,98,4,73,55,58,47,77,38,95,88,20,40,13,38,},
		{70,25,44,44,60,60,7,63,92,0,22,1,61,90,50,35,51,84,18,50,37,74,78,97,27,8,19,17,75,84,53,9,68,30,0,68,17,58,75,34,68,9,43,32,38,32,57,56,71,57,27,41,80,44,37,20,29,20,},
		{29,25,18,4,0,9,31,53,8,44,6,28,46,70,61,4,73,67,32,54,1,77,78,67,99,83,85,84,46,52,7,32,11,48,5,70,34,51,6,37,72,64,85,83,98,42,48,46,68,59,1,2,95,53,67,8,28,3,},
		{86,83,29,76,20,41,35,3,72,85,68,95,50,72,20,21,73,94,2,56,97,0,95,45,64,68,9,41,50,81,51,85,78,52,95,49,97,67,55,49,96,10,28,64,70,93,23,18,94,70,22,49,31,52,55,88,85,11,},
		{77,77,24,26,72,17,13,7,11,31,86,87,5,93,63,8,60,47,91,55,47,42,36,94,82,44,22,7,16,68,67,15,10,4,81,83,35,51,55,49,69,17,70,59,1,33,70,40,28,51,12,99,38,11,3,7,51,2,},
		{12,33,77,93,52,9,89,59,68,24,43,16,8,65,85,60,73,83,57,89,6,40,50,42,26,34,45,48,79,31,36,75,70,81,4,71,95,76,37,74,30,96,30,51,68,24,16,67,32,69,43,11,5,55,12,81,88,18,},
		{39,98,20,53,42,55,10,65,63,99,0,40,94,56,2,89,86,24,43,9,57,48,86,88,20,28,85,94,70,7,47,8,62,79,18,46,15,17,52,4,7,63,19,90,88,62,89,29,31,2,45,80,20,36,0,4,56,45,},
		{0,4,82,50,23,4,60,9,57,93,17,33,59,25,44,21,60,58,8,91,46,82,35,60,46,70,46,62,98,72,27,5,28,2,30,59,47,62,66,74,57,3,11,9,44,38,57,16,71,54,61,12,90,26,92,3,19,74,},
		{82,92,43,22,44,88,87,35,81,98,44,5,11,76,31,38,10,87,51,17,16,96,28,73,43,73,60,6,91,4,82,73,92,31,68,12,68,74,23,95,15,6,45,99,77,22,49,21,45,96,94,96,79,61,39,16,90,38,},
		{21,46,96,32,20,30,60,87,84,11,7,59,70,67,9,88,70,31,82,76,24,57,8,91,80,77,99,59,24,61,47,62,5,87,67,74,93,89,95,35,27,59,29,20,54,14,34,55,0,40,63,79,76,89,27,5,35,9,},
		{11,59,36,43,61,82,99,90,28,54,26,51,36,80,19,96,78,67,2,68,66,8,23,24,88,65,34,14,15,98,75,72,25,87,21,87,84,57,75,66,46,56,69,83,31,69,37,22,57,42,97,54,41,3,10,73,57,48,},
		{28,83,15,45,53,30,99,21,24,92,25,76,10,18,62,32,79,14,33,87,39,33,47,8,49,0,88,75,86,0,45,55,51,12,27,35,85,35,26,56,73,23,91,99,52,54,4,66,13,23,37,50,85,33,64,5,93,49,},
		{58,53,94,33,77,26,12,38,51,64,34,22,42,40,8,3,82,77,20,29,15,79,13,51,43,18,95,78,80,53,61,61,71,5,43,56,93,25,21,8,24,79,4,85,46,62,6,12,27,20,6,93,85,18,28,12,91,62,},
		{19,71,42,0,52,0,72,91,25,77,9,0,55,99,5,21,56,39,3,8,29,95,24,57,71,68,16,30,47,21,82,95,8,26,19,39,44,15,80,94,19,24,93,15,63,38,2,65,54,83,54,48,82,55,71,27,23,96,},
		{5,85,60,31,19,80,72,9,23,9,12,74,31,22,29,33,8,27,39,73,76,17,65,90,93,60,38,33,44,4,85,87,63,75,37,77,90,22,87,49,74,18,4,64,41,65,84,4,71,27,1,97,54,51,34,41,76,50,},
		{19,0,33,84,82,26,52,2,16,84,12,49,18,43,28,46,64,13,67,73,47,4,58,99,84,1,12,47,40,42,41,61,11,1,4,89,29,9,94,59,39,42,11,80,65,53,4,77,56,86,36,71,23,47,49,75,63,50,},
		{78,42,8,38,42,10,68,2,32,5,7,68,62,11,77,70,93,80,79,28,91,51,84,14,24,10,20,47,47,99,40,31,41,56,33,9,64,39,4,94,13,0,83,23,0,73,41,17,18,13,68,96,25,60,84,45,16,71,},
		{17,65,61,15,74,80,28,89,8,59,39,82,17,94,84,72,70,29,48,29,29,2,68,50,49,32,98,53,40,4,43,2,51,93,0,96,68,18,7,8,24,83,58,37,85,60,80,31,95,76,73,55,4,30,57,4,2,70,},
		{17,73,76,10,65,6,81,91,61,89,36,72,47,82,26,91,75,74,43,12,49,53,71,26,44,75,81,4,76,27,78,1,75,88,36,88,74,76,83,51,23,69,97,71,39,23,82,50,96,28,66,29,60,32,66,41,57,38,},
		{10,12,18,95,38,44,64,70,20,69,51,52,79,1,56,6,59,98,43,18,51,31,22,39,32,95,0,38,11,27,16,6,98,11,42,91,17,17,56,83,17,21,35,1,38,69,77,32,73,33,51,72,37,84,41,84,20,67,},
		{81,31,90,86,57,25,81,80,40,67,64,26,87,27,49,89,50,16,27,59,94,21,97,9,4,32,33,59,96,81,13,48,39,89,44,4,27,18,39,79,68,93,38,4,52,15,9,64,45,20,7,87,35,30,54,97,37,35,},
		{79,5,64,16,21,82,75,66,97,2,15,77,8,77,59,98,56,68,44,26,9,55,82,15,93,64,76,0,64,58,34,33,42,22,21,72,59,42,48,46,36,4,77,14,88,22,68,89,41,76,71,0,18,85,59,59,75,39,},
		{80,98,23,63,56,77,46,86,99,80,47,67,66,14,77,70,9,49,43,41,43,72,6,9,53,85,6,47,56,56,25,17,60,52,43,61,61,57,69,77,46,39,33,18,73,58,34,71,38,35,1,42,16,36,6,83,46,81,},
		{47,92,31,27,80,92,44,10,12,82,35,74,96,35,17,86,96,18,82,10,8,5,89,7,98,69,0,53,14,65,13,64,19,8,34,28,11,45,59,36,30,97,38,35,41,60,73,50,18,20,56,9,1,99,22,88,62,83,},
		{18,90,78,5,55,11,3,0,25,59,15,96,27,86,45,27,49,70,28,40,62,64,10,51,80,52,89,84,5,88,9,48,58,72,89,38,48,40,84,95,50,15,48,97,62,73,9,47,19,20,68,42,56,31,52,47,44,70,},
		{12,26,46,55,11,7,77,31,83,79,32,93,8,57,87,89,13,64,61,64,30,30,35,68,74,64,11,70,12,49,10,89,44,35,20,99,76,8,42,78,17,26,10,25,89,27,43,4,70,33,12,59,32,58,50,71,18,84,},
		{50,23,10,36,33,63,12,4,56,5,20,51,49,90,55,1,11,44,17,73,2,58,77,52,6,10,80,14,94,77,50,11,18,22,14,4,78,4,94,85,10,94,87,77,18,98,44,61,91,10,70,57,61,84,47,48,88,13,},
		{64,75,18,17,89,61,87,25,77,34,76,29,34,82,94,25,54,86,78,95,5,90,62,72,15,70,83,33,74,47,86,92,81,8,46,20,65,67,57,18,73,42,91,82,25,67,59,98,87,1,8,61,57,56,7,20,87,41,},
		{12,27,34,43,32,16,83,62,55,58,22,43,99,27,55,47,79,62,94,0,78,53,59,6,28,28,41,11,68,44,48,92,92,23,66,85,66,99,83,4,48,50,97,69,42,58,75,85,33,55,34,45,23,14,83,78,3,39,},
		{0,51,79,79,49,91,96,52,43,18,91,31,15,62,69,57,86,95,14,30,9,40,43,14,21,82,98,33,57,0,39,87,59,12,17,9,2,76,19,23,60,20,5,95,1,53,14,95,40,80,99,91,70,49,40,54,78,2,},
		{63,71,70,56,51,91,85,90,93,86,58,14,29,44,66,20,66,71,87,64,24,19,67,32,21,45,4,16,93,49,29,63,25,35,67,75,40,68,27,76,21,83,67,90,75,63,60,46,6,16,22,40,74,38,44,86,51,49,},
		{76,96,52,36,96,83,17,4,90,88,0,30,20,96,67,10,12,98,4,83,83,58,35,84,55,60,49,0,87,52,70,1,63,89,84,18,32,19,27,16,1,0,82,69,17,32,80,85,25,78,92,24,67,7,11,85,88,4,},
		{67,48,35,84,14,27,43,83,61,92,19,64,27,75,10,58,58,44,50,22,68,91,26,24,83,92,51,31,75,9,83,93,38,67,5,99,23,62,37,87,52,98,29,56,61,27,31,3,60,79,2,80,22,16,95,67,38,13,},
		{31,93,59,54,79,98,13,76,88,73,76,98,22,5,39,15,37,16,76,81,6,57,69,32,65,99,16,56,4,12,88,43,32,68,75,27,75,60,33,81,69,86,30,55,44,56,9,28,96,8,1,6,5,91,40,9,64,27,},
		{92,59,1,1,5,48,82,13,10,84,61,18,44,15,15,2,45,54,69,20,63,29,73,99,80,53,87,39,85,59,63,12,75,46,94,30,74,82,76,21,2,98,3,60,48,85,99,81,1,73,64,70,11,89,53,67,64,42,},
		{35,25,1,17,25,90,69,91,39,10,86,13,66,34,85,59,3,3,93,69,69,39,66,2,32,9,12,77,68,3,27,33,75,13,19,12,79,24,77,12,28,5,77,47,98,37,7,29,4,82,89,56,43,96,7,68,31,25,},
		{70,5,29,54,67,2,42,4,31,0,20,4,28,48,69,72,87,26,45,70,6,95,2,34,54,90,52,47,89,83,23,83,73,36,96,18,21,50,80,89,2,80,76,77,94,11,50,98,77,82,5,37,80,41,76,82,43,66,},
		{54,54,28,91,74,82,70,47,46,17,69,75,16,29,98,57,57,2,35,13,59,63,98,49,54,38,63,27,16,6,40,21,97,27,68,96,43,63,64,14,18,44,46,27,92,35,5,35,8,40,83,27,41,8,74,29,47,92,},
		{56,69,65,87,66,18,66,8,84,95,45,69,80,20,21,24,2,69,35,8,19,49,68,52,89,67,94,84,6,46,33,85,82,48,67,56,84,6,5,19,46,30,74,12,4,50,67,91,3,21,25,43,66,84,70,36,34,99,}};
		
//		real cost 2312
//		your path: [up, up, right, up, up, up, up, left, left, left, up, left, up, up, up, up, up, up, up, up, up, up, up, left, left, left, up, up, up, up, up, up, up, left, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, left, left, up, left, left, up, left, left, left, left, up, left, left, up, up, up, up, up, up, up, up, up, up, right, up, right, up, right], 
//		cost=2288 should be somehting like 
//		           [up, up, right, up, up, up, up, left, left, left, up, left, up, up, up, up, up, up, up, up, up, up, up, left, left, left, up, up, up, up, up, up, up, left, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, left, up, up, left, left, left, left, left, left], 
//		           cost=2263 expected:<2263> but was:<2288>
		
		Point start4 = new Point(67, 52), finish4 = new Point(8, 38);
		System.out.println(cheapestPath(tollMap4, start4, finish4));
//		way(tollMap4, start4, finish4,"up, up, right, up, up, up, up, left, left, left, up, left, up, up, up, up, up, up, up, up, up, up, up, left, left, left, up, up, up, up, up, up, up, left, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, left, left, up, left, left, up, left, left, left, left, up, left, left, up, up, up, up, up, up, up, up, up, up, right, up, right, up, right");
//		way(tollMap4, start4, finish4,"up, up, right, up, up, up, up, left, left, left, up, left, up, up, up, up, up, up, up, up, up, up, up, left, left, left, up, up, up, up, up, up, up, left, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, up, left, up, up, left, left, left, left, left, left");
//		{;st67,52,8,38;2312{
/*		
//		start 1;17
//		finish 4;13
		
*/
/*		int[][] tollMap2 = { { 1, 19, 1 ,1 ,1}, { 1, 19, 1 ,19,1},{ 1, 19, 1 ,19,1},{ 1, 19, 1 ,19,1}, { 1, 1, 1,19,1 } };
		Point start2 = new Point(0, 0), finish2 = new Point(4, 4);
		System.out.println(cheapestPath(tollMap2, start2, finish2));
*/		
		
	}


	class Way {
		
		Point point1;
		Point point2;
		int len;
		
		Way way1;
		Way way2; 
		
		public Way(Point p1, Point p2, int length) {
			Point pMin = minPoint(p1, p2);
			Point pMax = maxPoint(p1, p2);
			
			this.point1 = pMin;
			this.point2 = pMax;
			this.len = length;
		}
		
		public Way(Way way1, Way way2) {
			if (way1.point2 == way2.point1) {
				this.point1 = minPoint(way1.point1, way2.point2);
				this.point2 = maxPoint(way1.point1, way2.point2);
			} else if (way1.point2 == way2.point2) {
				this.point1 = minPoint(way1.point1, way2.point1);
				this.point2 = maxPoint(way1.point1, way2.point1);
			} else if (way1.point1 == way2.point1) {
				this.point1 = minPoint(way1.point2, way2.point2);
				this.point2 = maxPoint(way1.point2, way2.point2);
			} else if (way1.point1 == way2.point2) {
				this.point1 = minPoint(way1.point2, way2.point1);
				this.point2 = maxPoint(way1.point2, way2.point1);
			} else {
				throw new java.lang.RuntimeException("No common point: way1 and way2)");
				}

			this.len = way1.len + way2.len;
			
			this.way1 = way1;
			this.way2 = way2;
		}
		
		public String toString() {
			
			StringBuilder str = new StringBuilder();
			if (this.way1 == null) {
			    str.append("("+this.point1.x+","+this.point1.y+")");
			    str.append(this.len);
			    str.append("("+this.point2.x+","+this.point2.y+")");
			} else {
				str.append(this.way1);
				str.append(this.way2);
			}
			
			return str.toString();
		}
		
		public Point getEndPoint(Point startPoint) {
			return (startPoint.equals(this.point1)) ? this.point2 : this.point1;
		}

		public Way getFirstWay(Point startPoint) {
			if (this.point1 == startPoint || this.point2 == startPoint) {
				{return this.way1;} 
			} else {return this.way2;}
		}
		
		public Way getLastWay(Point startPoint) {
			if (this.point1 == startPoint || this.point2 == startPoint) {
				{return this.way2;} 
			} else {return this.way1;}
		}


		
		public List<String> getWay(Point startPoint) {

			List<String> ways = new ArrayList<String>();
			
			
			if (this.way1 == null)  {
				ways.add(getNameOfWay(startPoint, this.getEndPoint(startPoint))+":"+this.len);
			} else {
				Way first = getFirstWay(startPoint);
				Way last = getLastWay(startPoint);

				List<String> insideWays = first.getWay(startPoint);
				for (String newWay:insideWays) {ways.add(newWay);}

				Point endPoint = first.getEndPoint(startPoint);
				insideWays = last.getWay(endPoint);
				for (String newWay:insideWays) {ways.add(newWay);}
			};
			
			return ways;
		}
	}


}

