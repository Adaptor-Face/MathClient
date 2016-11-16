/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

/**
 *
 * @author Face
 */
public class InverseModuloEquation {

    private int firstPartMulti = 1;
    private int firstPart = 0;
    private int multiplier = 0;
    private int lastPart = 0;
    private String operand = "";
    private boolean negativeEquation = false;

    //"1 => 5 + -1 * 4"
    public InverseModuloEquation(String equationString) {
        String[] str = equationString.split(" ");
        firstPart = Integer.parseInt(str[0]);
        operand = str[1];
        multiplier = Integer.parseInt(str[2]);
        lastPart = Integer.parseInt(str[4]);
    }

    public InverseModuloEquation(int firstPart, String operand, int multiplier, int lastPart) {
        this.firstPart = firstPart;
        this.lastPart = lastPart;
        this.multiplier = multiplier;
        this.operand = operand;
    }

    public InverseModuloEquation(int firstPartMulti, int firstPart, String operand, int multiplier, int lastPart) {
        this.firstPart = firstPart;
        this.lastPart = lastPart;
        this.multiplier = multiplier;
        this.operand = operand;
        this.firstPartMulti = firstPartMulti;
    }

    public InverseModuloEquation mergeEquations(InverseModuloEquation ime) {
        InverseModuloEquation returnIME;
        int tempMultiplier = (multiplier * ime.getMultiplier()) + firstPartMulti;
        returnIME = new InverseModuloEquation(multiplier, ime.getFirstPart(), ime.getOperand(), tempMultiplier, ime.getLastPart());
        return returnIME;
    }

    @Override
    public String toString() {
        return "" + firstPartMulti + " * " + firstPart + " " + operand + " " + multiplier + "  * " + lastPart;
    }

    public void setNegativeEquation(boolean negativeEquation) {
        this.negativeEquation = negativeEquation;
    }

    public int getFirstPart() {
        return firstPart;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getLastPart() {
        return lastPart;
    }

    public String getOperand() {
        return operand;
    }
}
