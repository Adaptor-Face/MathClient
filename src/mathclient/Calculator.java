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

    /**
     * Returns true if the given expression is a math expression (and/or contains "with steps" at the end)
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
     * Calculates a given math expression and can show steps if "requested" by adding "with steps" after the expression.
     * @param expression the math expression to calculate
     * @return a string representing the answer.
     */
    public String calculate(String expression) {
        String returnString = "";
        if (steps) {
            expression = expression.replace("with steps", "");
        }
        expression = parseParanthesises(expression);
        while (expression.contains("(")) {
            if (steps) {
                returnString += "\n" + expression + " = ";
            }
            int index = expression.indexOf(")");
            int lastIndex = expression.substring(0, expression.indexOf(")")).lastIndexOf("(");
            String calcString = "[" + expression.substring(lastIndex + 1, index) + "]";
            expression = expression.substring(0, lastIndex) + calcString + expression.substring(index + 1);
            expression = expression.replace(calcString, calc(calcString.substring(1, calcString.length() - 1)).toString());
        }
        if(steps){
            returnString += "\n" + expression + " = ";
            returnString = returnString.substring(0,returnString.length()-3);
        }
        returnString += "\n= " + calc(expression).toString();
        return returnString;
    }

    /**
     * Calculates a given expression. calulates squentially, so 2 + 5 * 2 = 20, and not 12
     * @param expression the math expression to calculate
     * @return returns a BigDecimal with the answer
     */
    private BigDecimal calc(String expression) {

        ArrayList<BigDecimal> numbers = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        Pattern intPattern = Pattern.compile("-?\\d+");
        Pattern operandPattern = Pattern.compile("\\+?\\-?\\*?\\^?\\%?\\/?");
        String[] str = expression.replace(" ", "").replace("+", " ").replace("-", " ").replace("*", " ").replace("/", " ").replace("^", " ").replace("%", " ").split(" ");
        for (String number : str) {
            if (intPattern.matcher("" + number).matches()) {
                numbers.add(new BigDecimal(number));
            }
        }
        char[] chars = expression.replace("\\d+", "").replace(" ", "").toCharArray();
        for (char character : chars) {
            if (operandPattern.matcher("" + character).matches()) {
                operands.add("" + character);
            }
        }
        BigDecimal number = new BigDecimal("" + numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            String operand = operands.get(i - 1);
            switch (operand) {
                case "+":
                    number = number.add(new BigDecimal("" + numbers.get(i)));
                    break;
                case "-":
                    number = number.subtract(new BigDecimal("" + numbers.get(i)));
                    break;
                case "*":
                    number = number.multiply(new BigDecimal("" + numbers.get(i)));
                    break;
                case "/":
                    number = number.divide(new BigDecimal("" + numbers.get(i)));
                    break;
                case "^":
                    number = number.pow(Integer.parseInt(numbers.get(i).toString()));
                    break;
                case "%":
                    number = number.remainder(new BigDecimal("" + numbers.get(i)));
                    break;
                default:
                    break;
            }
        }
        return number;
    }

    /**
     * Checks if a paranthesis has an operator in front, or after. Inserts a multiplier if none is present
     * @param expression the math expression to parse
     * @return the finished expression, ready for calculation
     */
    private String parseParanthesises(String expression) {
        Pattern operandPattern = Pattern.compile("\\+?\\-?\\*?\\^?\\%?\\/?");
        boolean finished = false;
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
