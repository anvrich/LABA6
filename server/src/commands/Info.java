package commands;

import modules.LoadCSV;
import modules.Response;

import java.lang.ref.Reference;

import static modules.LoadCSV.personHashMap;

public class Info extends Command{

    public Info() {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции",false);
    }

    @Override
    public Response execute() {
        System.out.println("Тип коллекции: HashMap");
        System.out.println("Дата инициализации:" + LoadCSV.date);
        System.out.println("Количество элементов :" + personHashMap.size());
        return new Response("");
    }
}
