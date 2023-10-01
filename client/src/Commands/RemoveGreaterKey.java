package Commands;

import modules.Request;
import modules.TCPclient;

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(){
        super("remove_greater_key null"," Удалить из коллекции все элементы, ключ которых превышает заданный",true);

    }
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("remove_greater_key",null, getArgument()));
        }catch (Exception e){
            System.out.println("Ошибка: "+e.getMessage());
        }
    }
}
