package codewars;

import java.text.DecimalFormat;
import java.util.Arrays;

public class ExampleTest {

	
  public static void change(char[] arCh) {
	  
	  char[]  zzz = Arrays.copyOf(arCh,arCh.length);
	  
	  arCh[0] = 'c';
	  
	  zzz[2] = 'b';
	  System.out.println(arCh);
	  System.out.println(zzz);
	  
  }
  
   public static void changeInt(int In) {
	  
	  System.out.println(In);
	  In++;
	  System.out.println(In);
	  
  }
	
   public static String generateColor() {
       StringBuilder currFormat = new StringBuilder("000000");
	   DecimalFormat decimal = new DecimalFormat(currFormat.toString());

       int part = (int)(Math.random()*100000);
       String cR = "#"+String.format("%06d", part);
       
       System.out.println(cR);
       return cR;
   }
   
  public static void main(String[] args) {
  
	  System.out.println(generateColor());
	  
/*	  char[] arr = {'q','w','e'};
	  System.out.println(arr);
	  change(arr);
	  System.out.println(arr);

	  int it = 5;
	  System.out.println(it);
	  changeInt(it);
	  System.out.println(it);
*/	  
	  
  }
	
	
}