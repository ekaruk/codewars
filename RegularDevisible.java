package codewars;

public class RegularDevisible {

	public RegularDevisible() {
		// TODO Auto-generated constructor stub
	}

	public static int FrogJumping(int[] a) {
	    if (a.length==0) return -1;
		int currPosition = 0; 
	    int oldPosition = 0;
		int jumps = 0;
	    while (currPosition>=0 && currPosition<a.length ) {
	    	if (a[currPosition] == Integer.MAX_VALUE) return -1;
	    	oldPosition = currPosition;
	    	currPosition = currPosition + a[currPosition];	
	    	a[oldPosition] = Integer.MAX_VALUE;
	    	jumps++;   
	       }
	    return jumps;
	    }
	
	public static double findUniq(double arr[]) {
	    double notUniq = (arr[0]==arr[1]) ? arr[0] : arr[2];
	    for(double d: arr) {if (!(d==notUniq)) return d;}
        return arr[0];
	    }
	
	 public static int nearestSq(int n){
	     return (int) Math.pow((double)Math.round(Math.sqrt(n)),2.0);
	  }
	 
	public static void main(String[] args) {
		System.out.println(nearestSq(111));
	}

}
