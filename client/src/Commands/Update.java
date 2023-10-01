package Commands;

import modules.InputDataForm;
import modules.Request;
import modules.TCPclient;

import java.io.IOException;


public class Update extends Command {
    public Update(){
        super("update id {element}","Обновить значение элемента коллекции, id которого равен заданному",true);

    }
    @Override
    public void execute() {
        try{
            TCPclient.sendCommand(new Request("update", InputDataForm.input(),getArgument()));
        }catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
