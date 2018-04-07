package codewars;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double meal_cost = in.nextDouble();
        int tip_percent = in.nextInt();
        int tax_percent = in.nextInt();
         
        double tip = meal_cost*tip_percent/100;
        double tax = meal_cost*tax_percent/100;
        
        double totalCost = meal_cost + tip + tax;
          
        System.out.println("The total meal cost is "+Integer.toString((int)Math.round(totalCost))+" dollars.");
        

        in.close();
    }
}