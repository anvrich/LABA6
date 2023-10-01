package Commands;

import Person.Person;
import modules.Response;

import java.util.ArrayList;
import java.util.Comparator;

import static modules.LoadCSV.personHashMap;

public class PrintFieldDescendingHairColor extends Command {
    public PrintFieldDescendingHairColor(){
        super("print_field_descending_hair_color", "Вывести значения поля hairColor всех элементов в порядке убывания",false);
    }
    @Override
    public Response execute() {
        if (personHashMap.isEmpty()) {
            return new Response("Коллекция пуста");
        }
        ArrayList<Person> personList = new ArrayList<>(personHashMap.values());
        personList.sort(Comparator.comparing(Person::getHairColor).reversed());
        String ans = "";

        for (Person person : personList) {
          ans += person.getHairColor() + "\n" ;
        }
        return new Response("Значения поля hairColor всех элементов в порядке убывания:\n" + ans);
    }
}
