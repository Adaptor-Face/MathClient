package mathclient;

/**
 *
 * @author Kristoffer Rogne
 */
public class HelpCommand implements Command {

    public HelpCommand(){
    }
    @Override
    public String getName() {
        return "Help";
    }

    /**
     * Returns helpful information
     *
     * @param arguments
     * @return
     */
    @Override
    public String process(String[] arguments) {
        String returnString = "";
        if (arguments.length == 0) {
            returnString += "Your commands are:";
            for (String command : new CommandWords().getCommandWords()) {
                returnString += ", " + command;
            }
            for (String command : new CommandWords().getCommandWords()) {
                Command sc = new CommandWords().getCommand(command);
                returnString += "\n" + sc.getName() + ": " + sc.getShortDesc();
            }
        } else if ((arguments.length == 1) && (new CommandWords().isCommand(arguments[0]))) {

            returnString += "\n" + new CommandWords().getCommand(arguments[0]).getName() + ": " + new CommandWords().getCommand(arguments[0]).getLongDesc();
        } else {
            returnString += arguments[0] + " was not a command";
        }
        return returnString;
    }

    @Override
    public String getShortDesc() {
        return "Shows all commands available to the user.\n   For more details about a command use \"help \"command\"\"";
    }

    @Override
    public String getLongDesc() {
        return "Shows all commands available to the user.\n   For more details about a command use \"help \"command\"\"";
    }

}
