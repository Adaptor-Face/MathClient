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
import java.util.regex.Pattern;

/**
 *
 * @author Face
 */
public class UnionCommand implements Command {

    @Override
    public String getName() {
        return "Union";
    }

    public static List<Integer> union(List<Integer> a, List<Integer> b) {

        List<Integer> list = new ArrayList<>();
        Set<Integer> factorSet = new HashSet<>();
        factorSet.addAll(a);
        factorSet.addAll(b);
        List<Integer> factorList = new ArrayList<>(factorSet);
        for (int number : factorList) {
            int numberCountA = 0;
            int numberCountB = 0;
            for (int i : a) {
                if (number == i) {
                    list.add(i);
                    numberCountA++;
                }
            }
            for (int i : b) {
                if (number == i) {
                    numberCountB++;
                    if (numberCountB > numberCountA) {
                        list.add(i);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public String process(String[] arguments) {
        Pattern intPattern = Pattern.compile("-?\\d+");
        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();
        List<String> sets = new ArrayList<String>();
        for (String string : arguments) {
            if (intPattern.matcher(string).matches()) {
                if (sets.size() < 1) {
                    listA.add(Integer.parseInt(string));
                } else if (sets.size() >= 1) {
                    listB.add(Integer.parseInt(string));
                }
            } else if(sets.size() < 1){
                sets.add(string);
            } else if (sets.size() >= 1){
                listA = union(listA, listB);
                listB = new ArrayList<Integer>();
                sets.add(string);
            }

        }
        String returnString = "The union for sets";
        for(String set : sets){
            returnString += " \"" + set + "\"";
        }
        returnString += " is:";
        for (int i : listA) {
            if (returnString.endsWith(":")) {
                returnString += " " + i;
            } else {
                returnString += ", " + i;
            }
        }
        return returnString;
    }

    @Override
    public String getShortDesc() {
        return "Finds the union between number sets.";
    }

    @Override
    public String getLongDesc() {
        return "Finds the union between number sets.";
    }

}
