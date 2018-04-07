package codewars;

public class TimeFormatter {

    public static String toHumanTime(String time) {
    	int hour = Integer.parseInt(time.substring(0, 2));
    	int minutes = Integer.parseInt(time.substring(3, 5));
    	
    	if ( hour > 12) { hour =  hour-12;}
    	if ( hour == 0) { hour =  12;}
    	
    	if (minutes == 0) {return numberToWord(hour)+" o'clock";}
    	else if (minutes == 45) {return "quarter to "+numberToWord(hour+1);}
    	else if (minutes == 15) {return "quarter past "+numberToWord(hour);}
    	else if (minutes == 30) {return "half past "+numberToWord(hour);}
    	else if (minutes > 50) {return numberToWord(60-minutes)+" "+minuteStr(minutes)+" to "+numberToWord(hour+1);}
    	else {return numberToWord(minutes)+" "+minuteStr(minutes)+" past "+numberToWord(hour);}
        
    }
    
    
    public static String numberToWord(int num) {
        String ones[] = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String tens[] = {"", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
       if (num > 19) {
            return tens[num / 10] + ((num % 10 > 0)?"-" + ones[num % 10]:"");
        } else {
            return ones[num];
        }

    }
    
    public static String minuteStr(int minutes) {
        if (minutes % 10 == 1 || minutes==11) {
            return "minute";
        } else {
            return "minutes";
        }

    }
    
    public static void main(String[] args) {
//    	 System.out.println("05:28 pm:"+TimeFormatter.toHumanTime("05:28 pm"));
//    	 System.out.println("12:00:"+TimeFormatter.toHumanTime("12:00"));
//    	 System.out.println("05:28 pm:"+TimeFormatter.toHumanTime("03:45am"));
//    	 System.out.println("05:28 pm:"+TimeFormatter.toHumanTime("07:15"));
//    	 System.out.println("05:28 pm:"+TimeFormatter.toHumanTime("23:30"));
//    	 System.out.println("05:28 pm:"+TimeFormatter.toHumanTime("00:01"));
    	 System.out.println("05:28 pm:"+TimeFormatter.toHumanTime("01:20"));
    }
    
}