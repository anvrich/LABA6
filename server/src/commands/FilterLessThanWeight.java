package commands;

import Person.Person;
import modules.Response;

import static modules.LoadCSV.personHashMap;

public class FilterLessThanWeight extends Command {
    public FilterLessThanWeight(){
        super("filter_less_than_weight weight","Вывести элементы, значение поля weight которых меньше заданного",true);
    }
    @Override
    public Response execute() {
        try {
            Long weight = Long.valueOf(getArgument());
            boolean hasElements = false;
            for (Person person : personHashMap.values()) {
                if (person.getWeight() < weight) {
                    hasElements = true;
                    System.out.println(person);
                }
            }
            if (!hasElements) {
                System.out.println("Нет элементов с весом меньше заданного значения.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    return new Response("");
    }
}
