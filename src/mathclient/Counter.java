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
public class Counter {
    private int number = 0;
    public void set(int i){
        number = i;
    }
    public void increment(){
        number++;
    }
    
    public void decrement(){
        number--;
    }
    public int getNumber(){
        return number;
    }
    @Override
    public String toString(){
        return "" + number;
    }
}
