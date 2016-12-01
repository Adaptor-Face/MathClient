/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 *
 * @author Face
 */
public class NumberConverter {

    private static final HashMap<BigDecimal, String> hex;
    private static final HashMap<String, BigDecimal> decimal;

    static {
        hex = new HashMap<>();
        hex.put(new BigDecimal("10"), "a");
        hex.put(new BigDecimal("11"), "b");
        hex.put(new BigDecimal("12"), "c");
        hex.put(new BigDecimal("13"), "d");
        hex.put(new BigDecimal("14"), "e");
        hex.put(new BigDecimal("15"), "f");
        hex.put(new BigDecimal("16"), "g");
        hex.put(new BigDecimal("17"), "h");
        hex.put(new BigDecimal("18"), "i");
        hex.put(new BigDecimal("19"), "j");
    }

    static {
        decimal = new HashMap<>();
        for (BigDecimal key : hex.keySet()) {
            decimal.put(hex.get(key), key);
        }
    }

    public static String convertTo(String numberToConvert, int toNumberSystem) {
        String returnString = "";
        Pattern intPattern = Pattern.compile("-?\\d+");
        BigDecimal actualNumber = new BigDecimal("0");
        if (intPattern.matcher(numberToConvert).matches()) {
            actualNumber = new BigDecimal(numberToConvert);
            while (actualNumber.compareTo(new BigDecimal("0")) == 1) {
                BigDecimal remainder = actualNumber.remainder(new BigDecimal(toNumberSystem));
                actualNumber = (actualNumber.subtract(remainder)).divide(new BigDecimal(toNumberSystem));
                if (remainder.compareTo(new BigDecimal("9")) == 1) {
                    returnString = hex.get(remainder) + returnString;
                } else {
                    returnString = remainder.toString() + returnString;
                }
            }
        }
        return returnString.toUpperCase();
    }

    public static String convertFrom(String numberToConvert, int fromNumberSystem) {
        Pattern intPattern = Pattern.compile("-?\\d+");
        char[] numbers = numberToConvert.toLowerCase().toCharArray();
        BigDecimal resultNumber = new BigDecimal("0");
        int i = numbers.length - 1;
        for (char number : numbers) {
            BigDecimal num = new BigDecimal("0");
            if (intPattern.matcher("" + number).matches()) {
                num = new BigDecimal("" + number);
            } else {
                num = decimal.get("" + number);
            }
            double power = Math.pow((double) fromNumberSystem, (double) i);
            resultNumber = resultNumber.add(num.multiply(new BigDecimal("" + power)));
            i--;
        }
        String returnString = resultNumber.toString();
        if (returnString.contains(".")) {
            while (returnString.endsWith("0")) {
                returnString = returnString.substring(0, returnString.length() - 1);
            }
            if (returnString.endsWith(".")) {
                returnString = returnString.substring(0, returnString.length() - 1);
            }
        }
        return returnString;
    }

}
