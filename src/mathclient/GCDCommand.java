/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Face
 */
public class GCDCommand implements Command {

    @Override
    public String getName() {
        return "Gcd";
    }

    @Override
    public String process(String[] arguments) {
        int returnInt = 0;
        try {
            if (arguments.length == 1) {
                return arguments[0] + " is its own least common multiple";
            } else if (arguments.length > 1) {
                int a = Integer.parseInt(arguments[0]);
                int b = 0;
                for (String arg : arguments) {
                    b = Integer.parseInt(arg);
                    a = calculateGCD(a, b);
                }
                returnInt = a;
            }
        } catch (NumberFormatException e){
            return "GCD can only calculate numbers.\nCaluclation failure " + e.getMessage();
        }
        return "The highest common divisor is: " + returnInt;
    }

    private int calculateGCD(int a, int b) {

        List<Integer> aDivisors = DivisorsCommand.getDivisors(a);
        List<Integer> bDivisors = DivisorsCommand.getDivisors(b);
        List<Integer> commonDivisors = new ArrayList<>();
        for (int num : aDivisors) {
            if (bDivisors.contains(num)) {
                commonDivisors.add(num);
            }
        }
        int num = 0;
        for (int divisor : commonDivisors) {
            if (divisor > num) {
                num = divisor;
            }
        }
        return num;
    }


    @Override
    public String getShortDesc() {
        return "Finds the greatest common divisor between two integers";
    }

    @Override
    public String getLongDesc() {
        return "Finds the greatest common divisor between two integers";
    }

}
