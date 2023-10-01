package Commands;

import modules.Request;
import modules.TCPclient;



public class AverageOfWeight extends Command {
    public AverageOfWeight(){
        super("average_of_weight", "Вывести среднее значение поля weight для всех элементов коллекции",false);
    }
    @Override
    public void execute() {
        try {
            TCPclient.sendCommand(new Request("average_of_weight"));

        }catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }
    }
}
