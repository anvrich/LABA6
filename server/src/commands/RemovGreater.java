package commands;

import modules.Response;

public class RemovGreater extends Command {
    public RemovGreater(){
        super("remove_greater {element}", "Удалить из коллекции все элементы, превышающие заданный",true);
    }

    @Override
    public Response execute() {
        System.out.println("");
        return new Response("");
    }
}
