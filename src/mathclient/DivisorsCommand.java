/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Face
 */
public class DivisorsCommand implements Command{
    public static List<Integer> getDivisors(int a) {
        List<Integer> integerList = new ArrayList<>();
        long number = a;
        for (int i = 2; i <= a; i++) {
            if (number % i == 0) {
                integerList.add(i);
            }
        }
        return integerList;
    }

    @Override
    public String getName() {
        return "Divisors";
    }

    @Override
    public String process(String[] arguments) {
        String printString = "";
        int i = 0;
        try {
            for (i=0; i<arguments.length; i++) {
                int a = Integer.parseInt(arguments[i]);
                printString += "Divisors for " + a + ": ";
                List<Integer> factorizedInteger = getDivisors(a);
                for (int factor : factorizedInteger) {
                    printString += factor + ", ";
                }
                printString = printString.substring(0, printString.length() - 2) + "\n";
            }
        } catch (NumberFormatException e){
            printString += "\"" + arguments[i] + "\"" + " is not an integer.\n";
            i++;
            String[] resumeArgs = new String[arguments.length - i];
            for(int o=0; o<resumeArgs.length; o++)
            {
                resumeArgs[o] = arguments[i];
                i++;
            }
            printString += process(resumeArgs);
        }
        return printString.trim();
    }

    @Override
    public String getShortDesc() {
        return "Displays all numbers an integer is devisable by";
    }

    @Override
    public String getLongDesc() {
        return "Displays all numbers an integer is devisable by. Supports multiple entries";
    }
    
}
