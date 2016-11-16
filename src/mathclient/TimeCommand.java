package mathclient;

import java.time.LocalDateTime;

/**
 *
 * @author Kristoffer Rogne
 */
public class TimeCommand implements Command {

    @Override
    public String getName() {
        return "Time";
    }

    @Override
    public String process(String[] arguments) {
        return LocalDateTime.now().toString();
    }

    @Override
    public String getShortDesc() {
        return "Shows current system time";
    }

    @Override
    public String getLongDesc() {
        return "Shows current system time";
    }
    
}
