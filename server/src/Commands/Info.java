package Commands;

import modules.LoadCSV;
import modules.Response;

import static modules.LoadCSV.personHashMap;

public class Info extends Command{

    public Info() {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции",false);
    }

    @Override
    public Response execute() {
        return new Response("Тип коллекции: HashMap" + "\n" +
          "Дата инициализации:" + LoadCSV.date + "\n" +
            "Количество элементов :" + personHashMap.size());
    }
}
