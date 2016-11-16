package mathclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Face
 */
public class InverseModuloCommand implements Command {

    private static List<Integer> factorList;
    private static HashMap<Integer, InverseModuloEquation> equationList;
    private static HashMap<Integer, String> equationListString;
    static int p = 0;

    @Override
    public String getName() {
        return "InverseModulo";
    }

    @Override
    public String process(String[] arguments) {
        p= 0;
        factorList  = new ArrayList<Integer>();
        equationList = new HashMap<>();
        equationListString = new HashMap<>();
        Pattern intPattern = Pattern.compile("-?\\d+");
        int n = 0;
        int m = 0;
        String mod = "";
        for (String string : arguments) {
            if (intPattern.matcher(string).matches() && mod.isEmpty()) {
                n = Integer.parseInt(string);
            } else if (intPattern.matcher(string).matches() && mod.toLowerCase().equals("mod")) {
                m = Integer.parseInt(string);
            } else {
                mod = string;
            }
        }
        coeffisient(m, n);
        InverseModuloEquation equation = equationList.get(1);
//        boolean wasReplaced = true;
//        while (wasReplaced) {
//            String oldEquation = equation;
        for (int key : equationList.keySet()) {
            if (key != 1) {
                equation = equation.mergeEquations(equationList.get(key));
                
            }
        }
//            if (oldEquation.equals(equation)) {
//                wasReplaced = false;
//            }
//        }
        int answer = equation.getMultiplier();
            while(answer<0){
                answer = answer + m;
            }
        return "" + answer;
    }

    public static List<Integer> coeffisient(int a, int b) {
        List<Integer> list = new ArrayList<Integer>();

        int n = (a / b * b) + (a - ((a / b) * b));
        if (n != a) {
            System.out.println("YOU ARE AN IDIOT");
        }
        int m = (a - ((a / b) * b));
        int c = (a / b * b);
        if (m != 0) {
            factorList.add(a / b);
            list.add(b);
            equationList.put(m, new InverseModuloEquation("" + a + " + -" + factorList.get(p) + " * " + b + ""));
            equationListString.put(m,"" + a + " + -" + factorList.get(p) + " * " + b + "");
            p++;
            list.addAll(coeffisient(b, m));
        }
        return list;
    }

    @Override
    public String getShortDesc() {
        return "Finds the inverse of a modulo";
    }

    @Override
    public String getLongDesc() {
        return "Finds the inverse of a modulo";
    }

    private String calcAlgebra(int key, String equation) {
        String[] str = equation.split("\\+");
        int i = doAllMultiplications("lul" + str[1]);
        if(i< 0){
            i = i * -1;
            return str[0] + "- " + i;
        }
        return str[0] + "+ " + i;
    }

    private int doAllMultiplications(String equation) {
        CommandParser cp = new CommandParser(equation);
        int number = 0;
        String[] args = cp.getArgArray();
        for (int i = 2; i < args.length; i++) {
            String firstNumber = args[i - 2];
            String multiplier = args[i - 1];
            String secondNumber = args[i];
            if (multiplier.equals("*")) {
                number = Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
            }

        }
        return number;
    }

}
