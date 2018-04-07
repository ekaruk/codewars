package codewars;

import java.util.Arrays;

public class RectanglesUnion {

	int[][] input;
	
	public RectanglesUnion() {
		// TODO Auto-generated constructor stub
	}

	public static int calculateSpace(int[][] rectangles) {
        
		int[] range = {Integer.MAX_VALUE,Integer.MAX_VALUE,0,0};
		
		for(int i=0; i< rectangles.length; i++) {
			range[0] = Math.min(range[0], rectangles[i][0]); //minX
			range[1] = Math.min(range[1], rectangles[i][1]); //minY
			range[2] = Math.max(range[2], rectangles[i][2]); //maxX
			range[3] = Math.max(range[3], rectangles[i][3]); //maxY
		}
		
		int[][] usedX = new int[range[2]][2];
		int[][] usedY = new int[range[3]][2];
		for(int i=0; i< rectangles.length; i++) {
			for (int x = rectangles[i][0]; x < rectangles[i][2]; x++) {
				usedX[x][0] = 1;
			}
			for (int y = rectangles[i][1]; y < rectangles[i][3]; y++) {
				usedY[y][0] = 1;
			}
		}
		int rangeX = 0;
		int rangeY = 0;
		
		for (int x = 0; x < usedX.length; x++) {
			if (usedX[x][0] == 1) {
				usedX[x][1] = rangeX;
				rangeX++;
			}
		}
		for (int y = 0; y < usedY.length; y++) {
			if (usedY[y][0] == 1) {
				usedY[y][1] = rangeY;
				rangeY++;
			}
		}
		
		strDebug(Arrays.toString(range)+":"+rectangles.length);
		
		boolean[][] field = new boolean[rangeX][rangeY];
		
		int res = 0;
		for(int i=0; i< rectangles.length; i++) {
			for (int x = rectangles[i][0]; x < rectangles[i][2]; x++) {
				for (int y = rectangles[i][1]; y < rectangles[i][3]; y++) {
					if (!(field[usedX[x][1]][usedY[y][1]])) {
						field[usedX[x][1]][usedY[y][1]] = true;
						res++;
					} 
				}
			}
		}
		
		
//		for (int x = 0; x < rangeX; x++) {
//			for (int y = 0; y < rangeY; y++) {
//				if (field[x][y]) {res++;}
//			}
//		}
		
		for (boolean[] str :field) {
			strDebug(Arrays.toString(str));	
		}
		
		System.out.println("RaxgeX:"+rangeX+";rangeY:"+rangeY);
		for (int[] str :usedX) {
			strDebug("X:"+Arrays.toString(str));	
		}
		for (int[] str :usedY) {
			strDebug("Y:"+Arrays.toString(str));	
		}
		
		System.out.println(Arrays.toString(range)+":"+res);
		
		return res; 
    }	
	
	public static void strDebug(String text) {
		int a = 0;
		if (a==1) {System.out.println(text);} 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] recs = {{3,3,8,5},{6,3,8,9},{11,6,14,12}};
		
        RectanglesUnion.calculateSpace(recs);
	}
	
	

}
