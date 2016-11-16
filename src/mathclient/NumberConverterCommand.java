/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.util.HashMap;
import java.lang.reflect.Method;

/**
 *
 * @author Face
 */
public class NumberConverterCommand implements Command {

    @Override
    public String getName() {
        return "Convert";
    }

    @Override
    public String process(String[] arguments) {
        String returnString = "";
        if (arguments[0].toLowerCase().equals("to")) {
            if (arguments[1].toLowerCase().equals("binary")) {
                returnString = NumberConverter.convertTo(arguments[2], 2);
            } else if (arguments[1].toLowerCase().equals("ternary")) {
                returnString = NumberConverter.convertTo(arguments[2], 3);
            } else if (arguments[1].toLowerCase().equals("quaternary")) {
                returnString = NumberConverter.convertTo(arguments[2], 4);
            } else if (arguments[1].toLowerCase().equals("quinary")) {
                returnString = NumberConverter.convertTo(arguments[2], 5);
            } else if (arguments[1].toLowerCase().equals("senary")) {
                returnString = NumberConverter.convertTo(arguments[2], 6);
            } else if (arguments[1].toLowerCase().equals("octal")) {
                returnString = NumberConverter.convertTo(arguments[2], 8);
            } else if (arguments[1].toLowerCase().equals("undecimal")) {
                returnString = NumberConverter.convertTo(arguments[2], 11);
            } else if (arguments[1].toLowerCase().equals("duodecimal")) {
                returnString = NumberConverter.convertTo(arguments[2], 12);
            } else if (arguments[1].toLowerCase().equals("tridecimal")) {
                returnString = NumberConverter.convertTo(arguments[2], 13);
            } else if (arguments[1].toLowerCase().equals("tetradecimal")) {
                returnString = NumberConverter.convertTo(arguments[2], 14);
            } else if (arguments[1].toLowerCase().equals("pentadecimal")) {
                returnString = NumberConverter.convertTo(arguments[2], 15);
            } else if (arguments[1].toLowerCase().equals("hex")) {
                returnString = NumberConverter.convertTo(arguments[2], 16);
            }
        } else if (arguments[0].toLowerCase().equals("from")) {
            if (arguments[1].toLowerCase().equals("binary")) {
                returnString = NumberConverter.convertFrom(arguments[2], 2);
            } else if (arguments[1].toLowerCase().equals("ternary")) {
                returnString = NumberConverter.convertFrom(arguments[2], 3);
            } else if (arguments[1].toLowerCase().equals("quaternary")) {
                returnString = NumberConverter.convertTo(arguments[2], 4);
            } else if (arguments[1].toLowerCase().equals("quinary")) {
                returnString = NumberConverter.convertFrom(arguments[2], 5);
            } else if (arguments[1].toLowerCase().equals("senary")) {
                returnString = NumberConverter.convertTo(arguments[2], 6);
            } else if (arguments[1].toLowerCase().equals("octal")) {
                returnString = NumberConverter.convertFrom(arguments[2], 8);
            } else if (arguments[1].toLowerCase().equals("undecimal")) {
                returnString = NumberConverter.convertFrom(arguments[2], 11);
            } else if (arguments[1].toLowerCase().equals("duodecimal")) {
                returnString = NumberConverter.convertFrom(arguments[2], 12);
            } else if (arguments[1].toLowerCase().equals("tridecimal")) {
                returnString = NumberConverter.convertFrom(arguments[2], 13);
            } else if (arguments[1].toLowerCase().equals("tetradecimal")) {
                returnString = NumberConverter.convertFrom(arguments[2], 14);
            } else if (arguments[1].toLowerCase().equals("pentadecimal")) {
                returnString = NumberConverter.convertFrom(arguments[2], 15);
            } else if (arguments[1].toLowerCase().equals("hex")) {
                returnString = NumberConverter.convertFrom(arguments[2], 16);
            }
        }
        return returnString;
    }

    @Override
    public String getShortDesc() {
        return "converts a number to or from the specified number system";
    }

    @Override
    public String getLongDesc() {
        return "converts a number to or from the specified number system";
    }

}
