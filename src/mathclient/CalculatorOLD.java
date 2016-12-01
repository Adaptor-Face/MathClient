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
class CalculatorOLD {

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
        String test = calculationRules(expression);
        String returnString = "";
        if (steps) {
            expression = expression.replace("with steps", "");
        }
        if (expression.contains("(")) {
            expression = parseParanthesises(expression);
        }
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
        if (steps) {
            returnString += "\n" + expression + " = ";
            returnString = returnString.substring(0, returnString.length() - 3);
        }
        BigDecimal answer = calc(expression);
        if (steps) {
            returnString += "\n= " + stepString;
        } else {
            returnString += "\n= " + answer.toString();
        }
        return returnString;
    }

    private String calculationRules(String expression) {
        char[] operands = {'^', '/', '*', '-', '+', '%'};
        String expressionString = expression.replace("+", " + ").replace("*", " * ").replace("/", " / ").replace("^", " ^ ").replace("%", " % ").replace("-", " - ");
        String returnString = expressionString;
        for (char operand : operands) {
            while (returnString.contains("" + operand)) {
                int index = expressionString.indexOf("" + operand);
                returnString = expressionString.substring(0, index - 1) + operand + expressionString.substring(index + 2, expressionString.length());
                String[] str = expressionString.substring(0, index).split(" ");
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
                while (secondNumber.equals("")){
                    secondNumber = str[count];
                    count++;
                }
                if(secondNumber.equals("-")){
                    secondNumber = " - " +  str[count];
                }
                String calcExpression = firstNumber + operand + secondNumber;
                String result = calc(calcExpression).toString();
                returnString = returnString.replace(calcExpression, result);
            }
            expressionString = returnString;
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
    private BigDecimal calc(String expression) {
        stepString = "";
        ArrayList<BigDecimal> numbers = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        //Regex expression for any number
        Pattern intPattern = Pattern.compile("-?\\d+");
        //Regex expression for each operand
        Pattern operandPattern = Pattern.compile("\\+?\\-?\\*?\\^?\\%?\\/?");
        String[] str = expression.replace(" ", "").replace("+", " ").replace("*", " ").replace("/", " ").replace("^", " ").replace("%", " ").replace("-", " -").split(" ");
        //adds each number to the number list
        String operandString = expression;
        if(expression.startsWith("-")){
            operandString = expression.substring(1);
        }
        boolean wasNumber = false;
        for (String number : str) {
            if (wasNumber) {
                number = number.replace("-", "");
            }
            if (intPattern.matcher("" + number).matches()) {
                numbers.add(new BigDecimal(number));
                operandString = operandString.replaceFirst(number, "");
                wasNumber = true;
            } else {
                wasNumber = false;
            }
        }
        char[] chars = operandString.replace("-?\\d+", "").replace(" ", "").toCharArray();
        //adds each operator to the operator list
        for (char character : chars) {
            if (operandPattern.matcher("" + character).matches()) {
                operands.add("" + character);
            }
        }
        //Decide what operation must be used.
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
            if (steps) {
                stepString += number.toString();
                boolean anotherOperation = false;
                for (int o = i + 1; o < numbers.size(); o++) {
                    stepString += operands.get(o - 1) + numbers.get(o).toString();
                    anotherOperation = true;
                }
                if (anotherOperation) {
                    stepString += "\n= ";
                }
            }
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
