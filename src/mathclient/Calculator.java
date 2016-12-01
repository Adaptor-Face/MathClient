/*
* a basic calculator that accepts a wide varerity of math expressions, can supply with steps to see each calculation step for complex expressions
 */
package mathclient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author Face
 */
class Calculator {

    private static boolean steps = false;
    private String stepString = "";

    /**
     * Returns true if the given expression is a math expression (and/or
     * contains "with steps" at the end)
     *
     * @param string the string to check if it is a math expression
     * @return true if it is a math expression, false otherwise
     */
    static boolean isMathExpression(String string) {
        if (string.endsWith("with steps")) {
            steps = true;
            string = string.replace("with steps", "");
        } else {
            steps = false;
        }

        String numberString = string.replace(" ", "").replace("+", "").replace("-", "").replace("*", "").replace("/", "").replace("^", "").replace("%", "").replace("(", "").replace(")", "");
        Pattern intPattern = Pattern.compile("-?\\d+");
        return intPattern.matcher(numberString).matches();
    }

    /**
     * Calculates a given math expression and can show steps if "requested" by
     * adding "with steps" after the expression.
     *
     * @param expression the math expression to calculate
     * @return a string representing the answer.
     */
    public String calculate(String expression) {
        String returnString = " =";
        if (steps) {
            expression = expression.replace("with steps", "");
        }
        if (expression.contains("(")) {
            expression = parseParanthesises(expression);
        }
        if (steps) {
            stepString += "\n" + expression;
        }
        while (expression.contains("(")) {
            int index = expression.indexOf(")");
            int lastIndex = expression.substring(0, expression.indexOf(")")).lastIndexOf("(");
            String calcString = "[" + expression.substring(lastIndex + 1, index) + "]";
            if (steps) {
                stepString += "\n" + "Calculate paranthesises first: " + expression.substring(lastIndex + 1, index) + ":\n" + expression.substring(lastIndex + 1, index);
            }
            expression = expression.substring(0, lastIndex) + calcString + expression.substring(index + 1);
            expression = expression.replace(calcString, calculationRules(calcString.substring(1, calcString.length() - 1)));
        if (steps) {
            stepString += "\n\n" + expression;
        }
        }

        if (steps) {
            String test = calculationRules(expression);
            stepString += "\n= " + test;
            return stepString;
        }
        returnString += "\n= " + calculationRules(expression);
        return returnString;
    }

    private String calculationRules(String expression) {
        char[] operands = {'^', '/', '*', '+', '-', '%'};
        String expressionString = expression.replace(" ", "").replace("+", " + ").replace("*", " * ").replace("/", " / ").replace("^", " ^ ").replace("%", " % ").replace("-", " - ").replace("  -", " -");
        String returnString = expressionString;
        for (char operand : operands) {
            while (returnString.trim().substring(1).contains("" + operand)) {
                int index = expressionString.indexOf("" + operand);
                if (expressionString.startsWith("-")) {
                    index = (" " + expressionString.substring(1)).indexOf("" + operand);
                }
                returnString = expressionString.substring(0, index - 1) + operand + expressionString.substring(index + 2, expressionString.length());
                String[] str = expressionString.substring(0, index).split(" ");
                if (str.length > 0) {
                    String firstNumber = str[str.length - 1];
                    if (str.length - 1 > 1) {
                        if (str[str.length - 2].equals("-")) {
                            Pattern intPattern = Pattern.compile("-?\\d+");
                            if (!intPattern.matcher(str[str.length - 3]).matches()) {
                                firstNumber = "- " + firstNumber;
                            }
                        }
                    } else if (str.length - 1 > 0) {
                        if (str[str.length - 2].equals("-")) {
                            firstNumber = "- " + firstNumber;
                        }
                    }
                    str = expressionString.substring(index + 2).split(" ");
                    int count = 0;
                    String secondNumber = "";
                    while (secondNumber.equals("")) {
                        secondNumber = str[count];
                        count++;
                    }
                    if (secondNumber.equals("-")) {
                        secondNumber = "- " + str[count];
                    }
                    String calcExpression = firstNumber + operand + secondNumber;
                    String result = calc(firstNumber.replace(" ", ""), "" + operand, secondNumber.replace(" ", "")).toString();
                    returnString = returnString.replace(calcExpression, result);
                    if (steps) {
                        stepString += " =\n" + returnString;
                    }
                }
                expressionString = returnString;
            }
        }
        return returnString;
    }

    /**
     * Calculates a given expression. calulates squentially, so 2 + 5 * 2 = 20,
     * and not 12
     *
     * @param expression the math expression to calculate
     * @return returns a BigDecimal with the answer
     */
    private BigDecimal calc(String firstNumber, String operand, String secondNumber) {
        //Decide what operation must be used.
        BigDecimal number = new BigDecimal(firstNumber);
        switch (operand) {
            case "+":
                number = number.add(new BigDecimal(secondNumber));
                break;
            case "-":
                number = number.subtract(new BigDecimal(secondNumber));
                break;
            case "*":
                number = number.multiply(new BigDecimal(secondNumber));
                break;
            case "/":
                number = number.divide(new BigDecimal(secondNumber));
                break;
            case "^":
                number = number.pow(Integer.parseInt(secondNumber));
                break;
            case "%":
                number = number.remainder(new BigDecimal(secondNumber));
                break;
            default:
                break;
        }
        return number;
    }
    /**
     * Checks if a paranthesis has an operator in front, or after. Inserts a
     * multiplier if none is present
     *
     * @param expression the math expression to parse
     * @return the finished expression, ready for calculation
     */
    private String parseParanthesises(String expression) {
        Pattern operandPattern = Pattern.compile("\\+?\\-?\\*?\\^?\\%?\\/?");
        boolean finished = false;
        //will check if each parenthesis has an operator in front or after the paranthesis.
        while (!finished) {
            char[] chars = expression.replace(" ", "").toCharArray();
            for (int i = 1; i < chars.length - 1; i++) {
                char character = chars[i];
                if ((character == '(') && (chars[i - 1] != character) & !(operandPattern.matcher("" + chars[i - 1]).matches() || chars[i - 1] == '(')) {
                    expression = expression.substring(0, i) + "*" + expression.substring(i);
                    i = chars.length - 1;
                } else if ((character == ')') && (chars[i + 1] != character) & !(operandPattern.matcher("" + chars[i + 1]).matches() || chars[i - 1] == ')')) {
                    expression = expression.substring(0, i + 1) + "*" + expression.substring(i + 1);
                    i = chars.length - 1;
                }
                if (i + 2 == chars.length) {
                    finished = true;
                }
            }
        }
        return expression;
    }
}
