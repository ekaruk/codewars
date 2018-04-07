package codewars;

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
	
  public static void main(String[] args) {
  
	  char[] arr = {'q','w','e'};
	  System.out.println(arr);
	  change(arr);
	  System.out.println(arr);

	  int it = 5;
	  System.out.println(it);
	  changeInt(it);
	  System.out.println(it);
	  
	  
  }
	
	
}