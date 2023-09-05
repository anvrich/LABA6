package commands;


import Person.Person;
import modules.LoadCSV;
import modules.Response;

import static modules.LoadCSV.personHashMap;

import java.util.HashMap;
import java.util.Map;


public class Show extends Command{
    public Show() {
        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении",false);
    }
    @Override
    public Response execute() {
        HashMap<Integer,Person> people = LoadCSV.personHashMap;
        if(people.size() > 0) {
            String ans = "";
            for (Map.Entry<Integer, Person> el :  personHashMap.entrySet()) {
                ans +=  "Имя: " + el.getValue().getName() + "\n"+
                        "Рост: "+ el.getValue().getHeight()+ "\n";
            }
            return new Response(ans);
        }
        else {
            return new Response("Коллекция пуста!");
        }
    }
}
