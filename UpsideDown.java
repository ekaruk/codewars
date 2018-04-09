package codewars;

import java.util.LinkedList;
import java.util.List;

public class UpsideDown {

	public UpsideDown() {
		// TODO Auto-generated constructor stub
	}

    public static int solve(int x, int y) {
        int countUpdown = 0;
    	for (int i=x; i < y; i++) {
        	String currNum = String.valueOf(i);
        	String currNumUp = currNum.replaceAll("[23457]", "");
        	if (!currNum.equals(currNumUp)) {continue;}
        	String reverse = new StringBuilder().append(currNum).reverse().toString();
        	reverse = reverse.replaceAll("9", "d");
        	reverse = reverse.replaceAll("6", "9");
        	reverse = reverse.replaceAll("d", "6");
    		if (currNum.equals(reverse)) {
    			System.out.println(currNum);
    			countUpdown++;}
        }
    	
    	return countUpdown;
    }
	
    
    
    
    public static List<Integer> primeNumbersBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        if (n >= 2) {
            primeNumbers.add(2);
        }
        for (int i = 3; i <= n; i += 2) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    
    private static boolean isPrimeBruteForce(int number) {
        for (int i = 2; i*i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public withoutPrime
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solve(100,1000));
	}

}
