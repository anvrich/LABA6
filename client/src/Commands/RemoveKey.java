package Commands;

import modules.Request;
import modules.TCPclient;


public class RemoveKey extends Command {
    public RemoveKey(){
        super("remove_key null", "Удалить элемент из коллекции по его ключу",true);

    }
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("remove_key" , null, getArgument()));
        }catch (Exception e){
            System.out.println("Ошибка : " + e);
        }
    }
}
