/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author Face
 */
public class NumberConverter {

    private static final HashMap<Integer, String> hex;
    private static final HashMap<String, Integer> decimal;

    static {
        hex = new HashMap<>();
        hex.put(10, "a");
        hex.put(11, "b");
        hex.put(12, "c");
        hex.put(13, "d");
        hex.put(14, "e");
        hex.put(15, "f");
        hex.put(16, "g");
        hex.put(17, "h");
        hex.put(18, "i");
        hex.put(19, "j");
    }
    static {
        decimal = new HashMap<>();
        decimal.put("a", 10);
        decimal.put("b", 11);
        decimal.put("c", 12);
        decimal.put("d", 13);
        decimal.put("e", 14);
        decimal.put("f", 15);
        decimal.put("g", 16);
        decimal.put("h", 17);
        decimal.put("i", 18);
        decimal.put("j", 19);
    }

    public static String convertTo(String numberToConvert, int toNumberSystem) {
        String returnString = "";
        Pattern intPattern = Pattern.compile("-?\\d+");
        double actualNumber = 0;
        if (intPattern.matcher(numberToConvert).matches()) {
            actualNumber = Integer.parseInt(numberToConvert);
            while (actualNumber > 0) {
                Double remainder = actualNumber % toNumberSystem;
                actualNumber = (actualNumber - remainder) / toNumberSystem;
                if (remainder > 9) {
                    returnString = hex.get(remainder.intValue()) + returnString;
                } else {
                    returnString = remainder.intValue() + returnString;
                }
            }
        }
        return returnString.toUpperCase();
    }
    
    public static String convertFrom(String numberToConvert, int fromNumberSystem){
        Pattern intPattern = Pattern.compile("-?\\d+");
        char[] numbers = numberToConvert.toLowerCase().toCharArray();
        int resultNumber = 0;
        int i = numbers.length -1;
        for(char number : numbers){
            int num = 0;
            if(intPattern.matcher("" + number).matches()){
                num = Integer.parseInt("" + number);
            } else{
                num = decimal.get("" + number);
            }
                resultNumber += num * (Math.pow((double)fromNumberSystem, (double)i));
                i--;
        }
        
        return "" + resultNumber;
    }

}
