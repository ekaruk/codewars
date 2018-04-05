package codewars;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kata {

	public static String remove(String str) {
		  
        return str.substring(1, str.length()-1);
    }
	
	 public static String Encrypt(String text, int n)
	  {
		if ((text == null) || (text.length() == 0) || (n <=0))
		{
			return text;
		}
		 
		String textEncrypted = new String("");
			
	    //System.out.println(textEncrypted);
	    for (int j=1; j < text.length(); j=j+2) {
	    	textEncrypted = textEncrypted.concat(text.substring(j, j+1));
	    }
	    for (int j=0; j < text.length(); j=j+2) {
	    	textEncrypted = textEncrypted.concat(text.substring(j, j+1));
	    }
	    
		return Encrypt(textEncrypted,n-1);
	  }
	  
	  public static String Decrypt(String encryptedText, int n)
	  {
		  if ((encryptedText == null) || (encryptedText.length() == 0) || (n <=0))
			{
				return encryptedText;
			}
		  
		  int half = encryptedText.length()/2;
		  String first = encryptedText.substring(0, half);
		  String second = encryptedText.substring(half);
		  
		  System.out.println("first:"+first);
		  System.out.println("second:"+second);
		  
		  String text = new String("");
		  
		  for (int i=0; i < half; i++) {
			  text = text.concat(second.substring(i,i+1));
			  text = text.concat(first.substring(i,i+1));
		  }
		  
		  if (second.length() > first.length()) {text = text.concat(second.substring(half,half+1));}
		  
		  return Decrypt(text,n-1);
	  }	
	
	public Kata() {
		// TODO Auto-generated constructor stub
	}

	public static long findNextSquare(long sq) {
	      
	     long prev = (long) Math.sqrt(sq);

	     if (! (prev*prev == sq)) {return -1;}
	     
	     return (prev+1)*(prev+1); 
	}
	
    public static String fatFingers(String str) {
        
    	if (str == null || str.isEmpty()) {
    		return str;
    	}
    	
    	boolean reverseCase = false;
    	StringBuilder aString = new StringBuilder();
    	for (int i =0; i< str.length(); i++) {
    		char aChar = str.charAt(i);
    		if (aChar == 'a' || aChar == 'A') {
    			reverseCase = !reverseCase;
    		} else if (reverseCase) {
    			if (Character.isUpperCase(aChar)) {
    				aString.append(Character.toLowerCase(aChar));
    			} else aString.append(Character.toUpperCase(aChar));
    		} else {
    			aString.append(aChar);
    		}
    	}
    	
		return aString.toString(); 
    	
    }
	
    public static int findSmallestInt(int[] args) {
        Arrays.sort(args);
		return args[0];
    }  
    
    static String noSpace(final String x) {
        return x.replace(" ", "");
    }

    public static long numberOfDivisors(int n) {
        long numbers = 1;
        for (int i = 1; i < n; i++) {
        	if (n % i == 0) {numbers++;}
        }
        return numbers;

      }
    
    public static String solution(String str) {
        // Your code here...
        StringBuilder reverse = new StringBuilder();
        
        for (char curr : str.toCharArray()) {
        	reverse.insert(0, curr);
        }
        
    	return reverse.toString();
      }
    
    public static boolean isAnagram(String test, String original) {
        char[] charTest = test.toLowerCase().toCharArray();
        char[] charOriginal = original.toLowerCase().toCharArray(); // your code goes here

        Arrays.sort(charTest);
        Arrays.sort(charOriginal);
        
        return Arrays.equals(charTest,charOriginal);
      }
    
    public static int countPassengers(ArrayList<int[]> stops) {
		
    	int passengers = 0;
    	for (int[] stop: stops) {
    		passengers=passengers+stop[0];
    		passengers=passengers-stop[1];
    	}
    	
    	return passengers;
    	  //Code here!
    	  }
    
    public static int[] digitize(long n) {
        String number = String.valueOf(n);
        char[] numb = number.toCharArray();
        int[] reversed = new int[numb.length];
		
        for (int i = 1; i<=numb.length; i++) {
        	reversed[numb.length-i] = Integer.parseInt(String.valueOf(numb[i-1]));       	
        }
        
        return reversed;
        
      }
    
    public static double[] averages(int[] numbers)
    {
        if (numbers == null || numbers.length < 2) {return new double[0];}
    	
        double[] output = new double[numbers.length-1];
     	
        for (int i=0; i < numbers.length-1; i++ ) {
        	output[i] = ((double)(numbers[i]+numbers[i+1]))/2; 
        }
        
    	return output;
    }
    
    public static boolean isDivisible(int...numbers){
    	
    	for (int i=1; i < numbers.length; i++) {
    		
    		if (!(numbers[0] % numbers[i] == 0) ) {return false;}
    		
    	}
    	return true;
    	
    }
    
    public static int solution(int number) {
		
    	int multy = 0;
    	
    	for (int i = 0; i<number; i++) {
    		if (i % 3== 0 || i % 5 == 0) {
    			multy +=i;
    		}
    	}
    	return multy;
        //TODO: Code stuff here
      }
    
    public static long int123(final int a) {
        if (a < 123 && a > (Integer.MIN_VALUE+123)) {return 123-a;} 
        else {
           	return (long)Integer.MAX_VALUE*2+(125-a); }
      }

    public static int howOld(final String herOld) {

    	  return Integer.parseInt(herOld.substring(0,1));
    	  
    	  }    
    
    public static int sequence(int[] arr) {
        
    	if (arr.length == 0) {return 0;} 
    	int maxRes = 0;
    	
    	for (int i = 0; i < arr.length; i++) {
    		for (int j = 0; j < arr.length; j++) {
        		int currSum = 0;
        		for (int k = i; k < j; k++) {
        			currSum =currSum + arr[k];
            	}
        		if (currSum > maxRes) {maxRes = currSum;}
        		
        	}	
    	}
    	
    	return maxRes;
      }
    
    public static int sortDesc(final int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);
        StringBuilder digitStr = new StringBuilder();
        for (int i = 1; i <= digits.length; i++) {
        	digitStr.append(digits[digits.length-i]);
        }
        System.out.println(digitStr.toString());
        return Integer.parseInt(digitStr.toString());
      }   
    
    
    
    public static String antiString(String str){
        
    	String input = "";
    	String output = "";
     	
    	ArrayList<Character> letters = new ArrayList<Character>(); 
    	for (char c = 'a'; c <= 'z'; c++) {
    		letters.add(c);
    	}
    	input = input.concat(letters.toString());
    	output = output.concat(new StringBuilder(letters.toString().toUpperCase()).reverse().toString());
    	
    	letters.clear();
    	for (char c = 'A'; c <= 'Z'; c++) {
    		letters.add(c);
    	}
    	input = input.concat(letters.toString());
    	output = output.concat(new StringBuilder(letters.toString().toLowerCase()).reverse().toString());
    	
    	letters.clear();
    	for (char c = '0'; c <= '9'; c++) {
    		letters.add(c);
    	}
    	input = input.concat(letters.toString());
    	output = output.concat(new StringBuilder(letters.toString()).reverse().toString());
    	
    	StringBuilder out = new StringBuilder("");
    	    	
    	for (int i = 0; i < str.length(); i++)
    	{
    		char curr = str.charAt(i);
    		
    		int index = input.indexOf(curr);
    		
    		out.append(output.charAt(index));
   		
    	}
    	
    	return out.reverse().toString();
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	int count = 3;
    	String input = "8 7 6";
//        int count = Integer.parseInt(scan.nextLine().trim());
//        String input = scan.nextLine();
    	
    	
		StringBuilder currFormat = new StringBuilder("0");
		for (int i=1; i<count-1; i++) {currFormat.append("0");}
		DecimalFormat decimal = new DecimalFormat(currFormat.toString());
    	
		StringBuilder buildInput = new StringBuilder(input);
		
		for (int currNum=0; currNum < Math.pow(10, count-1); currNum++) {
			
			char[] currNumbers = decimal.format(currNum).toCharArray();
					
			for (int i = 1; i < count; i++) 
			{
				buildInput.replace(i*2-1, i*2, String.valueOf(currNumbers[i-1]));
			}

			double num = Double.parseDouble(buildInput.toString());

			double numSqrt = Math.sqrt(num);
			//System.out.println(currNum+":"+num+":"+numSqrt+":"+Arrays.toString(currNumbers));
			if (numSqrt == Math.round(numSqrt)) 
			{
				System.out.println((int)numSqrt);
				return;
			}

		}
		
		System.out.println("Not found");

    	
    	
    	
    }
    
    public static Double evaluate(String expression) {
        // your code here
        
    	String[] symbols = expression.split(" ");
    	
    	LinkedList<String> parts = new LinkedList<String>();
    	
    	for (String symbol : symbols) {parts.add(symbol);}

		boolean devision = true;
   	
    	while (parts.size() > 2) {
        	
    		String currOperation;
    		if (devision == true) {currOperation = "*/";} 
    		else {currOperation = "+-";}
    		
    		devision = false;
    		System.out.println("parts: "+parts+"; currOperation:"+currOperation);
    		outerloop:
    		for (int i = 1; i < parts.size()-1; i++) {
        		
        		String operation = parts.get(i); 
        		System.out.println("operation: "+operation);
        		if (currOperation.contains(operation)) {
        			
        			Double num1 = Double.valueOf(parts.get(i-1));
        			Double num2 = Double.valueOf(parts.get(i+1));
        			
        			Double result=0.0;
        			
        			switch (operation) {
        			case "*": result = num1 * num2; break;
        			case "/": result = num1 / num2; break;
        			case "+": result = num1 + num2; break;
        			case "-": result = num1 - num2; break;
        			default: System.out.println("Error case: "+operation);
        			} 
        			
        			devision = "*/".contains(operation);
        			
        			parts.remove(i+1);
        			parts.remove(i);
        			parts.remove(i-1);
        			
        			parts.add(i-1, result.toString());
        			
        			break outerloop;
        		}
        		
        	}
    	}
    	
    	
    	return Double.parseDouble(parts.get(0));
      }
    
    public static String MorseCodeget(String morzeKod)
    {
        String[] english = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                  "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
                  "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                  ",", ".", "?" };

        String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--.." };


        for (int index = 0; index < morse.length; index++)           
        {
            
            if (morzeKod.equals(morse[index]))
            {    
                return english[index];       
            }
        }  
        return null;
    }
    
    public static String morzeDecode(String morseCode) {
    	morseCode = morseCode.replace("   ", " word ").trim();
    	String[] letters = morseCode.split(" ");
    	
    	StringBuilder out = new StringBuilder("");
    	
    	for (int i =0; i<letters.length; i++ ) {
    		if (letters[i].equals("word")) {out.append(" ");}
    		else {out.append(MorseCodeget(letters[i]));}
    		
    	}
    	
    	return out.toString();
    }
    
    public static String decodeBits(String bits) {
    	bits = bits.replaceAll("(^[0]+)|([0]+$)", "");
    	
    	int unitLength = Integer.MAX_VALUE;
        Matcher matcher = Pattern.compile("1+|0+").matcher(bits);
        while (matcher.find()) {
        	unitLength = Math.min(unitLength, matcher.group().length());
        }
    	    	
    	String spaceWord = "";
    	String spaceLetter = "";
    	String spaceDot = "";
    	String spaceDash = "";
    	for (int i=1; i <= unitLength; i++) {
    		spaceWord = spaceWord + "0000000";
        	spaceLetter = spaceLetter + "000";
        	spaceDot = spaceDot+"1";
        	spaceDash = spaceDash+"111";
    	}
    	System.out.println("unitLength:"+unitLength+";spaceWord:"+spaceWord+";bits:"+bits);
    	
    	bits = bits.replaceAll(repeat("0", unitLength * 7), "   ");
    	System.out.println("unitLength:"+unitLength+";bits:"+bits);
    	bits = bits.replaceAll(repeat("0", unitLength * 3), " ");
    	System.out.println("unitLength:"+unitLength+";bits:"+bits);
    	bits = bits.replaceAll(repeat("1", unitLength * 3), "-");
    	System.out.println("unitLength:"+unitLength+";bits:"+bits);
    	bits = bits.replaceAll(repeat("1", unitLength), ".");
    	System.out.println("unitLength:"+unitLength+";bits:"+bits);
    	bits = bits.replaceAll("0", "");
    	System.out.println("unitLength:"+unitLength+";bits:"+bits);
    	
    	return bits;
      }
    

    public static String decodeMorse(String morseCode) {
        morseCode = morseCode.trim().replace("   ", " word ");
      	String[] letters = morseCode.split(" ");
      	
      	StringBuilder out = new StringBuilder("");
      	
      	for (int i =0; i<letters.length; i++ ) {
      		if (letters[i].equals("word")) {out.append(" ");}
      		else {
             String letter = MorseCodeget(letters[i]);
             if(!(letter==null)) {out.append(letter);}
             }
      		
      	}
      	
      	return out.toString(); 
      	
      }
      
      /**
       * Given a string of bits, which may or may not begin or end with '0's,
       * and which may have some variation in the length of the time unit used,
       * returns the Morse Code translation of this message.
       *
       * Accepts 0s and 1s, return dots, dashes and spaces
       *
       */
      public static String decodeBitsAdvanced(String bitsInput) {
    	  
    	  
    	  String bits = bitsInput.replaceAll("(^[0]+)|([0]+$)", "");
    	  
    	  //bits = bits.replaceAll("010", "00");
    	  //bits = bits.replaceAll("101", "11");
    	  
       	  int minZero = 100;
          Matcher matcher0 = Pattern.compile("0+").matcher(bits);
          while (matcher0.find()) {
        	  minZero = Math.min(minZero,matcher0.group().length());
          } 
          
        ArrayList<String> allMatches = new ArrayList<String>();
    	
        Matcher matcher = Pattern.compile("1+|0+").matcher(bits);
        while (matcher.find()) {
          allMatches.add(matcher.group());
        }
    	
    	String[] bit1 = bits.split("[0]+");
    	double numZ = bits.replaceAll("0", "").length();
      if (numZ == 0 ) {return " ";}
    	Arrays.sort(bit1);
    	double unitLength = 0.0;
      if (bit1[0].length() == bit1[bit1.length-1].length()) 
    	  {
    		unitLength = bit1[0].length();
        if (unitLength < (minZero*2)) {unitLength = unitLength*1.2;}
    	  }
    	else  
    	  {
        	double num1 = bits.replaceAll("0", "").length();
        	unitLength = (1.1)*num1/bit1.length;
    	  }
    	
    	String curr = "";
    	StringBuilder result = new StringBuilder();
    	for (String word: allMatches) 
    	{  if (word.contains("0")) 
    	    {
    		if (word.length() > (Math.max(Math.min(minZero,unitLength*4),unitLength*2.33))) {curr = "   ";}
    		else if (word.length() >= (Math.max(minZero,unitLength))) {curr = " ";}
    		else {curr = "";}
    	    }
    	else 
    	    {
    		if (word.length() >= (unitLength*1.05)) {curr = "-";}
    		else {curr = ".";}
    	    }
    	result.append(curr);  
    	}
    	System.out.println("result:"+unitLength+":"+bitsInput+":"+result.toString()+":"+decodeMorse(result.toString()));
    	return result.toString();
      }
       

    
    private static String repeat(String symbol, int times) {
        StringBuilder ret = new StringBuilder(symbol);
        for (int i = 1; i < times; i++)
            ret.append(symbol);
        return ret.toString();
    }
    
	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("morzeDecode:"+decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011"));
		System.out.println("morzeDecode:"+decodeBitsAdvanced("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011"));
		System.out.println("decodeBitsAdvanced:"+decodeBitsAdvanced("0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000"));
		System.out.println("decodeBitsAdvanced:"+morzeDecode(decodeBitsAdvanced("0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000")));
//		System.out.println("morzeDecode_morzeDecode:"+morzeDecode(decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011")));
//		System.out.println("morzeDecode:"+morzeDecode("  .... . -.--   .--- ..- -.. ."));
		System.out.println("morzeDecode_morzeDecode:"+morzeDecode(decodeBits("01110")));
		System.out.println("decodeBitsAdvanced:"+decodeBitsAdvanced("00000000000000011111111000000011111111111100000000000111111111000001111111110100000000111111111111011000011111111011111111111000000000000000000011111111110000110001111111111111000111000000000001111111111110000111111111100001100111111111110000000000111111111111011100001110000000000000000001111111111010111111110110000000000000001111111111100001111111111110000100001111111111111100000000000111111111000000011000000111000000000000000000000000000011110001111100000111100000000111111111100111111111100111111111111100000000011110011111011111110000000000000000000000111111111110000000011111000000011111000000001111111111110000000001111100011111111000000000111111111110000011000000000111110000000111000000000011111111111111000111001111111111001111110000000000000000000001111000111111111100001111111111111100100000000001111111100111111110111111110000000011101111111000111000000001001111111000000001111111111000000000111100001111111000000000000011111111100111111110111111111100000000000111111110000001100000000000000000000111111101010000010000001111111100000000011111000111111111000000111111111110011111111001111111110000000011000111111110000111011111111111100001111100001111111100000000000011110011101110001000111111110000000001111000011111110010110001111111111000000000000000000111111111110000000100000000000000000011110111110000001000011101110000000000011111111100000011111111111100111111111111000111111111000001111111100000000000001110111111111111000000110011111111111101110001111111111100000000111100000111100000111111111100000111111111111000000011111111000000000001000000111100000001000001111100111111111110000000000000000000010001111111100000011111111100000000000000100001111111111110111001111111111100000111111100001111111111000000000000000000000000011100000111111111111011110000000010000000011111111100011111111111100001110000111111111111100000000000000111110000011111001111111100000000000011100011100000000000011111000001111111111101000000001110000000000000000000000000000111110010000000000111111111000011111111110000000000111111111111101111111111100000000010000000000000011111111100100001100000000000000111100111100000000001100000001111111111110000000011111111111000000000111100000000000000000000111101111111111111000000000001111000011111000011110000000001100111111100111000000000100111000000000000111110000010000011111000000000000001111111111100000000110111111111100000000000000111111111111100000111000000000111111110001111000000111111110111111000000001111000000000010000111111111000011110001111111110111110000111111111111000000000000000000000000111111111110000000111011111111100011111110000000001111111110000011111111100111111110000000001111111111100111111111110000000000110000000000000000001000011111111110000000001111111110000000000000000000000011111111111111000000111111111000001111111110000000000111111110000010000000011111111000011111001111111100000001110000000011110000000001011111111000011111011111111110011011111111111000000000000000000100011111111111101111111100000000000000001100000000000000000011110010111110000000011111111100000000001111100011111111111101100000000111110000011110000111111111111000000001111111111100001110111111111110111000000000011111111101111100011111111110000000000000000000000000010000111111111100000000001111111110111110000000000000000000000110000011110000000000001111111111100110001111111100000011100000000000111110000000011111111110000011111000001111000110000000011100000000000000111100001111111111100000111000000001111111111000000111111111100110000000001111000001111111100011100001111111110000010011111111110000000000000000000111100000011111000001111000000000111111001110000000011111111000100000000000011111111000011001111111100000000000110111000000000000111111111111000100000000111111111110000001111111111011100000000000000000000000000"));
/*
		System.out.println("isDivisible:"+isDivisible(100,5,4,10,25,20));

		
		System.out.println("averages:"+Arrays.toString(averages(new int[]{ 1, 3, 5, 1, -10})));
		
		System.out.println("digitize:"+Arrays.toString(digitize(12345)));
		ArrayList<int[]> list = new ArrayList<int[]>();
	    list.add(new int[] {10,0});
	    list.add(new int[] {3,5});
	    list.add(new int[] {2,5});		
		System.out.println("countPassengers:"+countPassengers(list));
		System.out.println("isAnagram:"+isAnagram("asdfghjkl","asdfghkjl"));
		System.out.println("numberOfDivisors:"+numberOfDivisors(5));
		System.out.println("remove:"+solution("asdfghjkl"));
		System.out.println("findSmallestInt:"+findSmallestInt(new int[]{78,56,232,12,11,43}));
		System.out.println("findNextSquare(long sq):"+findNextSquare(-1));
		
		System.out.println("solution:"+solution(10));
		
		long a = 32767;
		System.out.println(a+":"+(a+1000)+":"+(int)(a+1000)+":"+Integer.MAX_VALUE+":"+(int)(Integer.MAX_VALUE*2+125));
		System.out.println("int123:"+int123(-2147483648)+":"+((int) (int123(-2147483648) -2147483648.00)));
		//Kata n = new Kata();
		System.out.println("sortDesc:"+sortDesc(45131953));
		
		System.out.println("sequence:"+sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
		
		System.out.println("antiString:"+antiString("abcWXY123"));
		System.out.println("evaluate:"+evaluate("2 / 2 + 3 * 4 - 6"));
	*/	
		
	}

	private static void assertEquals(String string, String decrypt) {
		String result;
		
		if (string.equals(decrypt)) {result = "true";} else {result = "false";}
		
		System.out.println(result+":"+string+":"+decrypt);
		
	}

}


