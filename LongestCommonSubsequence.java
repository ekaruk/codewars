package codewars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongestCommonSubsequence {

	int countlcsRec;
	int countisMatch;
	
	public LongestCommonSubsequence() {

		// TODO Auto-generated constructor stub
	}

	public String lcs(String x, String y) {
		
		x = x.replace('?', 'Ô');
		y = y.replace('?', 'Ô');
		long startTime = System.nanoTime();    
		
		this.countlcsRec = 0;
		this.countisMatch = 0;		
		
		String res = lcsRec(x,y,"");
		
		long estimatedTime = System.nanoTime() - startTime;		

		
		System.out.println("countlcsRec:"+this.countlcsRec+";countisMatch"+this.countisMatch+";estimatedTime"+estimatedTime);
		
		return res.replace('Ô','?');
	}
	
	
	public String lcsRec(String x, String y, String add) {

		this.countlcsRec++;
		
		if (x.length() == 0 || y.length() == 0 || x == null || y == null)
			return "";

		//System.out.println("x:" + x + ";y:" + y);

		String MaxResult = "";
		String result = "";
		for (int i = 0; i < x.length(); i++) {

				String currRes2 = x.substring(i, i+1);
				if (isMatch(currRes2, y, add)) {
					result = currRes2+lcsRec(x.substring(i+1),y,add+currRes2+".*");
//					System.out.println(result);
				}
				if (result.length() > MaxResult.length()) {MaxResult = result;}
			}

		return MaxResult;

	}

	public boolean isMatch(String currRes, String y, String add) {
		this.countisMatch++;
		
		currRes = add+changePattern(currRes);
		
		Matcher matcher = Pattern.compile(currRes).matcher(y);
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
//		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		System.out.println(lcs.lcs("acaf", "abcdef"));
//		System.out.println(lcs("anothertest","notatest"));
//		System.out.println(lcs.lcs("O0IF=;N4K6L6H1?J?Q?1SHOS<LIE2CRGK0QR<RL:;","Q:86F8GAK?KENR5GHNS9RMK<L5>3QS2AI0M;CNFA"));
//		System.out.println(lcs.lcs("L45@7;7H6QR33HJ<II7JG1M3D69IG9GFKGDJCRC?","A:CA25D0RD?3EHJK9R=MPKR=?8N4=28EA120??P?"));
		System.out.println(lcs.lcs("L8;FJG=P?;2PQ<L>LQ2D:4R@B644N5O99HRH86MNL>GJF?HEL@H9LGSS?SP<;","BQQSE05Q6=<I6B<HHMIR2B<G?O91KDC16SEEI5>>D7FK9@D:JH9NM3P?QP46"));
//		x:y:
		// System.out.println(lcs("abcdef","fffffffcdfffffffff"));
		// System.out.println(lcs("acaaaaaaaaaaaaaaaaaa","acbbbbabbbbbbbbbbbbbb"));
		// System.out.println(lcs("aaaaaaaaaaaaaaaaaac","bbbbbbcbbbbbbbbbbbbbac"));
		// System.out.println(lcs("fhjdshabcdjshfa","fhjdsabkdjshfa"));

	}

}
