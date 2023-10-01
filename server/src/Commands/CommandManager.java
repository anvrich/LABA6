package Commands;

import java.util.HashMap;

public class CommandManager {
    private static HashMap<String, Command> commands;
    static {
        commands = new HashMap<>();
        commands.put("insert", new Insert());
        commands.put("show", new Show());
        commands.put("save", new Save());
        commands.put("clear", new Clear());
        commands.put("info", new Info());
        commands.put("average_of_weight" , new AverageOfWeight());
        commands.put("filter_less_than_weight" , new FilterLessThanWeight());
        commands.put("remove_key", new RemoveKey());
        commands.put("print_field_descending_hair_color" , new PrintFieldDescendingHairColor());
        commands.put("remove_greater_key" , new RemoveGreaterKey());
        commands.put("remove_lower_key",new RemoveLowerKey());
        commands.put("update", new Update());

    }
    public static HashMap<String, Command> getComands() {
        return commands;
    }
}
