package Commands;

import modules.Request;
import modules.TCPclient;

import java.io.IOException;

public class PrintFieldDescendingHairColor extends Command {
    public PrintFieldDescendingHairColor(){
        super("print_field_descending_hair_color", "Вывести значения поля hairColor всех элементов в порядке убывания",false);
    }
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("print_field_descending_hair_color"));
        } catch (IOException e) {
            System.out.println("Ошибка : " + e);
        }
    }
}
