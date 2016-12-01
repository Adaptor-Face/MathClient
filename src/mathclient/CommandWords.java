package mathclient;

import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Kristoffer Rogne
 */
public class CommandWords {
    private final static TreeMap<String, Command> validCommands = new TreeMap<>();

    public CommandWords() {
        validCommands.put("help", new HelpCommand());
        validCommands.put("time", new TimeCommand());
        validCommands.put("gcd", new GCDCommand());
        validCommands.put("lcm", new LCMCommand());
        validCommands.put("factorize", new FactorizeCommand());
        validCommands.put("divisors", new DivisorsCommand());
        validCommands.put("union", new UnionCommand());
        validCommands.put("intersect", new IntersectionCommand());
        validCommands.put("inversemodulo", new InverseModuloCommand());
        validCommands.put("e", new TestCommand());
        validCommands.put("convert", new NumberConverterCommand());
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString) {
        if (validCommands.containsKey(aString)) {
            return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public static TreeMap<String, Command> getValidCommands() {
        return validCommands;
    }

    /**
     * returns all valid commands for the user
     *
     * @Return all valid commands for the user
     */
    public String[] getCommandWords() {
        String[] returnString;
        returnString = new String[validCommands.size()];
        int i = 0;
        for (String string : validCommands.keySet()) {
            returnString[i] = string;
            i++;
        }
        return returnString;
    }

    public Command getCommand(String name) {
        Command serverCommand = null;
        serverCommand = validCommands.get(name);
        return serverCommand;
    }

}
