package codewars;


import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EvaluateMathematicalExpression {

	public EvaluateMathematicalExpression() {
		// TODO Auto-generated constructor stub
	}

	public static String doOperation(String numStr1, String numStr2, String operation) {
		System.out.println("0:numStr1"+numStr1+":numStr2:"+numStr1+";operation:"+operation);
		
		double num1 = Double.parseDouble(numStr1);
		double num2 = Double.parseDouble(numStr2);
		
		switch (operation) {
		case "*": return String.valueOf((num1 * num2));
		case "/": return String.valueOf((num1 / num2));
		case "+": return String.valueOf((num1 + num2));
		case "-": return String.valueOf((num1 - num2));
		default:
			return null;
		}
	} 
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("[ ]*[-]?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public static double SolveExpr(String expression) {
		
		System.out.println("1:expression:"+expression);
		
		expression = expression.replaceAll("--", "");
		
		LinkedList<String> expressions = new LinkedList<String>();
		
		Matcher matcher = Pattern.compile("[\\d.]+| -[\\d.]+|^-[\\d.]+|[()/+*-]").matcher(expression);
        String lastExp = new String("");
		while (matcher.find()) {
			String currExp = matcher.group();
			if (isNumeric(currExp) && isNumeric(lastExp) && currExp.contains("-")) {
				expressions.add("-");
				expressions.add(currExp.replaceAll("-", ""));
			} else {expressions.add(currExp);}
			lastExp = currExp;
        }
        System.out.println("2:"+expressions+":"+expressions.contains("("));
        
        while (expressions.contains("(")) 
        {
        	System.out.println("3:"+expressions);
        	int StartQ = -1;
        	for (int i=0 ; i < expressions.size(); i++) {
            	String currElem = expressions.get(i);
        		if (currElem.equals("(")) {
        			StartQ = i;
        		}
        		if (currElem.equals(")")) {
        			StringBuilder subExpr = new StringBuilder("");
        			for (int j=StartQ+1; j < i; j++) {
        				subExpr.append(expressions.get(j));
        			}
        			for (int j = i; j >= StartQ; j--) {
        				expressions.remove(j);
        			}
    				double subResult = SolveExpr(subExpr.toString());
    				expressions.add(StartQ, String.valueOf(subResult));
    				break;
        		}
            }
        }
        
        while (expressions.size() > 1) 
        {
        	String currOperations = (expressions.contains("*") || expressions.contains("/")) ? "*/" : "+-"; 
        	for (int i=1 ; i < expressions.size()-1; i++) {
        		String currOperation = expressions.get(i);
        			if (currOperations.indexOf(currOperation) >= 0)
        			{
        				String num1 = expressions.get(i-1);
        				String num2 = expressions.get(i+1);
        				if (num2.equals("-")) {
        					expressions.set(i+2, String.valueOf((-1)*Double.parseDouble(expressions.get(i+2))));
        					expressions.remove(i+1);
        					break;
        				}
        				
        				String resOperation = doOperation(num1,num2, currOperation);
        				expressions.remove(i+1);
        				expressions.remove(i);
        				expressions.remove(i-1);
        				expressions.add(i-1, resOperation);
        				break;       				 
        			}
           }
        }
        
        
        
        return Double.parseDouble(expressions.get(0));
	}
	
	public static void calkWithTime(String expression)
	{
		long StartBasTime = System.nanoTime(); 
		double res = SolveExpr(expression);
		long fullBasTime = System.nanoTime() - StartBasTime;
		System.out.println(expression+":"+fullBasTime+":"+res);
		
	}
	
	public static void main(String[] args) {

//		calkWithTime("12*-1");
//		calkWithTime("12* 123/-(-5 + 2)");
//		calkWithTime("-123");
//		calkWithTime("(1 - 2) + -(-(-(-4)))");
//		calkWithTime("123.45*(678.90 / (-2.5+ 11.5)-(80 -19) *33.25) / 20 + 11");
		calkWithTime("80 -19");
		 
		System.out.println("Finish!");
		
		
		
		
	}

}


