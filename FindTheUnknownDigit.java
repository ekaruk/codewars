package codewars;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindTheUnknownDigit {

	public FindTheUnknownDigit() {
		// TODO Auto-generated constructor stub
	}

	public static int solveExpression( final String expression ) {

		LinkedList<String> expressions = new LinkedList<String>();
		
		Matcher matcher = Pattern.compile("[\\d?]+|^-[\\d?]+|[+*-=]").matcher(expression);
	    String lastExp = new String("");
		while (matcher.find()) {
			String currExp = matcher.group();
			if ((currExp.equals("-")) && ("+-*=".contains(lastExp))) {
				matcher.find();
				expressions.add(currExp+matcher.group());
			} else {expressions.add(currExp);}
			lastExp = currExp;
	    }
		String operation = expressions.get(1);
		
		for (int i=0; i<10;i++) 
		{
			if (i==0 && expression.contains("??")) {continue;}
			if (expression.contains(String.valueOf(i))) {continue;}
			char currNum = String.valueOf(i).charAt(0); 
			int num1 = Integer.parseInt(expressions.get(0).replace('?', currNum));
			int num2 = Integer.parseInt(expressions.get(2).replace('?', currNum));
			int result = Integer.parseInt(expressions.get(4).replace('?', currNum));
			int currRes = 0; 
			
			switch (operation) {
			case "+": currRes = num1+num2; break;
			case "-": currRes = num1-num2; break;
			case "*": currRes = num1*num2; break;
			default: break;}
			
			if (currRes == result) return i;
		}
		
		return -1;
	}
	

	
	public static void main(String[] args) {
		System.out.println("?*11=??:"+solveExpression("?*11=??"));
		System.out.println("-5?*-1=5?:"+solveExpression("-5?*-1=5?"));

	}

}
