package Commands;

import modules.Request;
import modules.TCPclient;

import java.io.IOException;


public class Info extends Command{

    public Info() {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции",false);
    }

    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("info"));
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }
}
