package mathclient;

/**
 * Interface for a single command supported by the server
 * @author Girts Strazdins, girts.strazdins@gmail.com, 2015-06-16
 */
public interface Command {
    /**
     * Command name, should be lowercase
     * @return 
     */
    String getName();

    /**
     * Process a command with specified arguments, return response
     * @param arguments optional arguments supplied with the command
     * @return Output of the command that should be sent back to the client
     */
    String process(String[] arguments);
    
    /**
     * Short description of the command (max one line)
     * @return 
     */
    String getShortDesc();

    /**
     * Long description of the command
     * @return 
     */
    String getLongDesc();
}
