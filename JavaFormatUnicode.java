package codewars;

import java.util.Arrays;

public class JavaFormatUnicode {

    public static String decode(final String input) {
        String[] symbols = input.split("\\\\u");
    	
        StringBuilder result = new StringBuilder("");
        
        for (int i=0; i < symbols.length; i++) {
        	if (!(symbols[i].isEmpty())) {
            	char ch = (char) Integer.parseInt(symbols[i], 16);
            	result.append(ch);        		
        	}
        }
        
    	return result.toString();
    }

    public static String encode(String input) {
        
    	StringBuilder resStr = new StringBuilder(""); 
    	for (char ch : input.toCharArray()) {
    		String returnStr;
    	    
    	    String charEsc = "\\\\u";

    	    
    	    if (ch < 0x10) {
    	      returnStr = "000" + Integer.toHexString(ch);
    	    }
    	    else if (ch < 0x100) {
    	      returnStr = "00" + Integer.toHexString(ch);
    	    }
    	    else if (ch < 0x1000) {
    	      returnStr = "0" + Integer.toHexString(ch);
    	    }
    	    else
    	      returnStr = "" + Integer.toHexString(ch);	
    	    resStr.append(charEsc+returnStr);
    	}
    	
    	return resStr.toString();
    }

	public static void main(String[] args) {
		System.out.println("decode:"+decode("\\u0068\\u006f\\u006c\\u0061"));
		System.out.println("encode:"+encode("hola"));

	}

}
