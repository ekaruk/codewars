package codewars;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Anagrams {

	public static BigInteger listPosition(String word) {
		if (word.length() == 1) {
			return BigInteger.ONE;
		}

		BigInteger result = BigInteger.ZERO;

		String lastPart = null;
		lastPart = word.substring(1);
		BigInteger lastCount = listPosition(lastPart);
		result = result.add(lastCount);

		Character first = word.charAt(0);
		ArrayList<Character> letters = new ArrayList<Character>();

		for (char ch : word.toCharArray()) {
			if (!(letters.contains(ch))) {
				letters.add(ch);
			}
		}

		for (Character ch : letters) {
			if (ch < first) {
				String currWord = word.replaceFirst(ch.toString(), "");
				BigInteger currResult = list(currWord);
				result = result.add(currResult);
			}
		}

		return result;
	}

	public static BigInteger factorial(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Value must be positive");
		}

		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= value; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}

		return result;
	}

	public static BigInteger list(String word) {

		BigInteger factorial = factorial(word.length());

		HashMap<Character, Integer> letters = new HashMap<Character, Integer>();

		for (char ch : word.toCharArray()) {
			if (letters.containsKey(ch)) {
				letters.put(ch, letters.get(ch) + 1);
			} else {
				letters.put(ch, 1);
			}
		}

		for (Integer count : letters.values()) {
			factorial = factorial.divide(factorial(count));
		}

		return factorial;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(String.valueOf("IMMUNOELE:" + listPosition("IMMUNOELE") + ":" + list("IMMUNOELE")));
		System.out.println(String.valueOf(listPosition("IMMUNOELECTROPHO")));
		System.out.println(String.valueOf(listPosition("IMMUNOELECTROPHORETICALLY")));
		System.out.println(String.valueOf("BAAA:" + listPosition("BAAA") + ":" + list("BAAA")));
		System.out.println(String.valueOf("QUESTION:" + listPosition("QUESTION") + ":" + list("QUESTION")));
		System.out.println(String.valueOf(listPosition("BOOKKEEPER")));

	}

}
