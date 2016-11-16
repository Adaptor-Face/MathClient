/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kristoffer Rogne
 */
public class AddCommand implements Command {
    int i;
    @Override
    public String getName() {
        return "Add";
    }

    @Override
    public String process(String[] arguments) {
    BigInteger bigInt = new BigInteger("0");
    i = 0;
        try{
        for (i = 0; i < arguments.length; i++) {
            bigInt = bigInt.add(new BigInteger(arguments[i]));
        }
        return bigInt.toString();
    } catch (NumberFormatException e){
        return "Add can only add numbers, \"" + arguments[i] + "\" is not a number";
    }
    }

    @Override
    public String getShortDesc() {
        return "Adds a series of numbers together";
    }

    @Override
    public String getLongDesc() {
        return "Adds a series of numbers together.\n   Use: add \"nr1\" \"nr2\" ... \"nrN\"\nUses BigInteger as a datatype so you can enter as long of a number as you wish";
    }
    
}
