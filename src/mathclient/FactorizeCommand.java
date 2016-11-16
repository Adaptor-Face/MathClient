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
public class FactorizeCommand implements Command {

    public static List<Integer> factorize(int a) {
        List<Integer> integerList = new ArrayList<>();
        long number = a;
        for (int i = 2; i <= a; i++) {
            if (number % i == 0) {
                number /= i;
                integerList.add(i);
                i--;
            }
        }
        return integerList;
    }

    @Override
    public String getName() {
        return "Factorize";
    }

    @Override
    public String process(String[] arguments) {
        String printString = "";
        int i = 0;
        try {
            for (i=0; i<arguments.length; i++) {
                int a = Integer.parseInt(arguments[i]);
                printString += "Factors for " + a + ": ";
                List<Integer> factorizedInteger = factorize(a);
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
        return "Factorizes a number";
    }

    @Override
    public String getLongDesc() {
        return "Factorizes a number. If multiple numbers are entered each one is factorized";
    }

}
