package commands;

import java.util.HashMap;

public class CommandManager {
    private static HashMap<String, Command> commands;
    static {
        commands = new HashMap<>();
        commands.put("insert", new Insert());
        commands.put("show", new Show());
        commands.put("save", new Save());

    }
    public static HashMap<String, Command> getComands() {
        return commands;
    }
}
