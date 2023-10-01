package Commands;

import Person.Person;
import modules.InputDataForm;
import modules.Request;
import modules.TCPclient;

import java.io.IOException;

public class Insert extends Command{
    public Insert(){
        super("insert null {element}", "Добавить новый элемент с заданным ключом",false);
    }
    @Override
    public void execute() {
        Person person = InputDataForm.input();
            try {
                TCPclient.sendCommand(new Request("insert",person));
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
    }

