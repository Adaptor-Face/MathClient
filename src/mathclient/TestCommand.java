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
public class TestCommand implements Command{

    @Override
    public String getName() {
        return "Test";//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String process(String[] arguments) {
        return "Nothing";
    }

    @Override
    public String getShortDesc() {
        return "For testing only";
    }

    @Override
    public String getLongDesc() {
        return "For testing only";
    }
    
}
