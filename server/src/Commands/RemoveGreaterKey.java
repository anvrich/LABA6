package Commands;

import Person.Person;
import modules.Response;

import java.util.Iterator;
import java.util.Map;

import static modules.LoadCSV.personHashMap;

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(){
        super("remove_greater_key null"," Удалить из коллекции все элементы, ключ которых превышает заданный",true);

    }
    @Override
    public Response execute() {
        try {
            Integer givenKey = Integer.valueOf(getArgument());
            if (givenKey < 0) {
                return new Response("Ошибка: ключ не может быть отрицательным.");
            }
            Iterator<Map.Entry<Integer, Person>> iterator = personHashMap.entrySet().iterator();
            int count = 0;
            while (iterator.hasNext()) {
                Map.Entry<Integer, Person> entry = iterator.next();
                if (entry.getKey() > givenKey) {
                    iterator.remove();
                    count++;
                }
            }
            return new Response("Удалено элементов: " + count);
        }catch (NumberFormatException e) {
            return new Response("Ошибка: введите корректное числовое значение для ключа.");
        } catch (Exception e) {
            return new Response("Произошла ошибка: " + e.getMessage());
        }
    }
}
