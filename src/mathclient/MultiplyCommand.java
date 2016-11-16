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
public class MultiplyCommand implements Command {
    int i;
    @Override
    public String getName() {
        return "Multiply";
    }

    @Override
    public String process(String[] arguments) {
    BigInteger bigInt = new BigInteger("1");
    i = 0;
        try{
        for (i = 0; i < arguments.length; i++) {
            bigInt = bigInt.multiply(new BigInteger(arguments[i]));
        }
        return bigInt.toString();
    } catch (NumberFormatException e){
        return "Multiply can only multiply numbers, \"" + arguments[i] + "\" is not a number";
    }
    }

    @Override
    public String getShortDesc() {
        return "Multiplies a series of numbers together";
    }

    @Override
    public String getLongDesc() {
        return "Multiplies a series of numbers together.\n   Use: multiply \"nr1\" \"nr2\" ... \"nrN\"\nUses BigInteger as a datatype so you can enter as long of a number as you wish";
    }
    
}
