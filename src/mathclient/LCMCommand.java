/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Face
 */
public class LCMCommand implements Command {

    @Override
    public String getName() {
        return "Lcm";
    }

    @Override
    public String process(String[] arguments) {
        int returnInt = 0;
        try {
            if (arguments.length < 2) {
                return arguments[0] + " is its own least common multiple";
            } else if (arguments.length >= 2) {
                int a = Integer.parseInt(arguments[0]);
                int b = 0;
                for (String arg : arguments) {
                    b = Integer.parseInt(arg);
                    a = calculateLCM(a, b);
                }
                returnInt = a;
            }
        } catch (NumberFormatException e){
                return "LCM can only calculate numbers.\nCaluclation failure " + e.getMessage();
        }
        return "The least common multiplier is: " + returnInt;
    }

    @Override
    public String getShortDesc() {
        return "Calculates the least common multiplier";
    }

    @Override
    public String getLongDesc() {
        return "Calculates the least common multiplier";
    }

    private int calculateLCM(int a, int b) {
        List<Integer> listA = FactorizeCommand.factorize(a);
        List<Integer> listB = FactorizeCommand.factorize(b);

        List<Integer> list = UnionCommand.union(listA, listB);
        int returnInt = 1;
        for (int o : list) {
            returnInt = returnInt * o;
        }
        return returnInt;
    }

}
