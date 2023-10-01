package Commands;

import Person.Person;
import modules.Request;
import modules.TCPclient;

import java.util.Iterator;
import java.util.Map;

//import static modules.LoadCSV.personHashMap;

public class RemoveLowerKey extends Command {
    public RemoveLowerKey(){
        super("remove_lower_key null"," Удалить из коллекции все элементы, ключ которых меньше, чем заданный",true);
}
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("remove_lower_key", null , getArgument()));
        }catch (Exception e){
            System.out.println("Ошибка: "+e.getMessage());
        }
    }
}
