package Commands;


import Person.Person;
import modules.Request;
import modules.TCPclient;

//import static modules.LoadCSV.personHashMap;

import java.io.IOException;
import java.util.Map;

public class Show extends Command {
    public Show(){
        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении",false);

    }
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("show"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
