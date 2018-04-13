//https://www.codewars.com/kata/molecule-to-atoms/train/java
package codewars;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseMolecule {

	public ParseMolecule() {
		// TODO Auto-generated constructor stub
	}

	public static void addAtom(Map<String,Integer> formulas, String str, int count) {
		if (formulas.containsKey(str)) {
			formulas.put(str, formulas.get(str)+count);
		} else {formulas.put(str, count);}		
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("\\d+");  //match a number 
	}
	
	  public static int strCount(String str, char letter) {
	 		 int n = 0;
			 for (int i = 0; i < str.length(); i++) { 
			     if (str.charAt(i) == letter) { n++;};
			 }
			 return n;
	  }
	
	
    public static Map<String,Integer> getAtoms(String formula) {

    	if (formula.equals(formula.toLowerCase())) {throw new IllegalArgumentException();}
    	if ((!(strCount(formula, '[')==strCount(formula, ']'))) 
    	 || (!(strCount(formula, '(')==strCount(formula, ')')))
    	 || (!(strCount(formula, '{')==strCount(formula, '}')))) {throw new IllegalArgumentException();}
		System.out.println("1:formula:"+formula);
		
		Map<String,Integer> formulas = new HashMap<String,Integer>();
		Map<String,Integer> innerFormulas = new HashMap<String,Integer>();
		
		Matcher matcher = Pattern.compile("[A-Z][a-z]*|[\\d.]+|[()\\[\\]{}]").matcher(formula);
        String lastExp = new String("");
        String prevExp = new String("");
        int numCounts = 0;
		while (matcher.find()) {
			String currExp = matcher.group();
			if (currExp.equals(")") || currExp.equals("]") || currExp.equals("}")) {
				numCounts--;
				if (numCounts==0) {
					innerFormulas = getAtoms(lastExp);
					lastExp = "";
				}
			}
			if (numCounts>0) {lastExp = lastExp.concat(currExp);}
			if (currExp.equals("(") || currExp.equals("[") || currExp.equals("{")) {
				numCounts++;
				if (!(prevExp.isEmpty())) {
					addAtom(formulas,prevExp,1);
					prevExp = "";
				} else if (!(innerFormulas.isEmpty()) )  {
					//addAtom(formulas,prevExp,(isNumeric(currExp)) ? Integer.parseInt(currExp):1);
					int mult = 1;
					if (isNumeric(currExp)) {
						 mult = Integer.parseInt(currExp);
					} else if (!(currExp.equals("(") || currExp.equals("[") || currExp.equals("{"))) {prevExp = currExp;} 
					for (String s : innerFormulas.keySet()) {
						addAtom(formulas,s,innerFormulas.get(s)*mult);
					}
					innerFormulas.clear();
				}
			}

			if (numCounts==0  && !(currExp.equals(")") || currExp.equals("]")  || currExp.equals("}"))) {
				if (!(prevExp.isEmpty())  && !(isNumeric(prevExp)) && isNumeric(currExp)) {
					addAtom(formulas,prevExp,Integer.parseInt(currExp));
					prevExp = "";
				} else if (!(prevExp.isEmpty())  && !(isNumeric(prevExp) && (!isNumeric(currExp)) )) {
					addAtom(formulas,prevExp,1);
				    prevExp = currExp;
				} else if (!(innerFormulas.isEmpty()) )  {
					//addAtom(formulas,prevExp,(isNumeric(currExp)) ? Integer.parseInt(currExp):1);
					int mult = 1;
					if (isNumeric(currExp)) {
						 mult = Integer.parseInt(currExp);
					} else {prevExp = currExp;} 
					for (String s : innerFormulas.keySet()) {
						addAtom(formulas,s,innerFormulas.get(s)*mult);
					}
					innerFormulas.clear();
				} else {prevExp = currExp;}}
//			lastExp = currExp;
        } 
		if (!(prevExp.isEmpty())) {
			addAtom(formulas,prevExp,1);
		} else if (!(innerFormulas.isEmpty()))  {
			for (String s : innerFormulas.keySet()) {
				addAtom(formulas,s,innerFormulas.get(s));
			}
		}
   	
        return formulas;
    }

	
	public static void main(String[] args) throws IllegalAccessException {
//		System.out.println(getAtoms("H2O"));
		System.out.println(getAtoms("((H)2)[O]"));
//		System.out.println(getAtoms("K4[ON(SO3)2]2"));
//		System.out.println(getAtoms("As2{Be4C5[BCo3(CO2)3]2}4Cu5"));
		
		
		
		
		

	}

}
