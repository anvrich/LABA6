package Commands;

import Person.Person;
import modules.Response;

import java.util.Map;

import static modules.LoadCSV.personHashMap;

public class FilterLessThanWeight extends Command {
    public FilterLessThanWeight(){
        super("filter_less_than_weight weight","Вывести элементы, значение поля weight которых меньше заданного",true);
    }
    @Override
    public Response execute() {
        try {
            String ans = "\n";
            Long weight = Long.valueOf(getArgument());

            if (personHashMap.isEmpty()) {
                return new Response("Нет элементов коллекция пуста!!!");
            }
            boolean found = false;
            for (Map.Entry<Integer, Person> el : personHashMap.entrySet()) {
                if (el.getValue().getWeight() <= weight) {
                    ans += el.getValue() + "\n";
                    found = true;
                }
            }
            if (!found) {
                return new Response("Нет элементов с весом меньше заданного значения");
            }
            return new Response(ans);
        }catch (Exception e) {
            return new Response("Произошла ошибка: " + e.getMessage());
        }
    }
}
