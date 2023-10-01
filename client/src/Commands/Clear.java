package Commands;

import modules.Request;
import modules.TCPclient;

import java.io.IOException;


public class Clear extends Command {
    public Clear(){
        super("clear","Очистить коллекцию",false);
}
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("clear"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
