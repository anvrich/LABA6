package Commands;

import Person.Person;
import modules.Response;

import static modules.LoadCSV.personHashMap;

public class AverageOfWeight extends Command {
    public AverageOfWeight(){
        super("average_of_weight", "Вывести среднее значение поля weight для всех элементов коллекции",false);
    }
    @Override
    public Response execute() {
        try {
        if (personHashMap.isEmpty()) {
            return new Response("Коллекция пуста");
        }
        double sum = 0;
        int personWeight = personHashMap.size();
        for (Person person : personHashMap.values()) {
            sum += person.getWeight();
        }
        double averageWeight = sum / personWeight;
        return new Response("Средний вес всех элементов коллекции: " + averageWeight);
     }catch (Exception e){
            return new Response("Произошла ошибка при выполнении:" + e.getMessage());
        }
    }
}
