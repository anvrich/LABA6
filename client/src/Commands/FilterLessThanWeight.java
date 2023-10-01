package Commands;

import modules.Request;
import modules.TCPclient;

import java.io.IOException;

public class FilterLessThanWeight extends Command {
    public FilterLessThanWeight(){
        super("filter_less_than_weight weight","Вывести элементы, значение поля weight которых меньше заданного",true);
    }
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("filter_less_than_weight",null,getArgument()));
        } catch (IOException e) {
            System.out.println("Ошибка : " + e);
        }

    }
}
