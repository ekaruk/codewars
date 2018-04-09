package codewars;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongestCommonSubsequence {

	public LongestCommonSubsequence() {
		// TODO Auto-generated constructor stub
	}

	public static String lcs(String x, String y, String add) {

		if (x.length() == 0 || y.length() == 0 || x == null || y == null)
			return "";

		System.out.println("x:" + x + ";y:" + y);

		boolean newResult = true;
		String result = "No";
		int currLength = 0;
		while (newResult) {
			newResult = false;
			for (int i = 0; i < x.length() - currLength; i++) {

				String currRes = x.substring(i, i + currLength + 1);
				if (isMatch(currRes, y, add)) {
					lcs(x.substring(beginIndex))
					result = currRes;
					currLength++;
					newResult = true;
					break;
				}
			}
		}

		return result;

	}

	public static boolean isMatch(String currRes, String y, String add) {

		currRes = add+changePattern(currRes);
		
		Matcher matcher = Pattern.compile(add+currRes).matcher(y);
            if (matcher.find()) {
            	return true;
            } else return false;
    }

	public static String changePattern(String currRes) {
		int currLength = currRes.length();
		for (int j = 1; j<currLength; j++) {
			currRes = currRes.substring(0,currLength-j)+".*"+currRes.substring(currLength-j);
   		}
		return currRes;
	} 
	
	public static void main(String[] args) {
		// System.out.println(lcs("bc", "abc")); // TODO Auto-generated method stub
		System.out.println(lcs("acf", "abcdef",""));
		// System.out.println(lcs("abcdef","fffffffcdfffffffff"));
		// System.out.println(lcs("acaaaaaaaaaaaaaaaaaa","acbbbbabbbbbbbbbbbbbb"));
		// System.out.println(lcs("aaaaaaaaaaaaaaaaaac","bbbbbbcbbbbbbbbbbbbbac"));
		// System.out.println(lcs("fhjdshabcdjshfa","fhjdsabkdjshfa"));

	}

}
