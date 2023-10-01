package Commands;

import modules.Response;

import static modules.LoadCSV.personHashMap;

public class Clear extends Command {
    public Clear(){
        super("clear","Очистить коллекцию",false);
}
    @Override
    public Response execute() {
        personHashMap.clear();
        return new Response("Коллекция очищена.");
    }
}
