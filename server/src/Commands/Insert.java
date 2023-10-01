package Commands;

import Person.Person;
import modules.Response;
import static modules.LoadCSV.personHashMap;
public class Insert extends Command{

    public Insert(){
        super("insert null {element}", "Добавить новый элемент с заданным ключом",false);
    }

    @Override
    public Response execute() {
        Person person = getPerson();
        if (person != null) {
            personHashMap.put(person.getId(), person);
            return new Response("Добавлена в коллекцию.");
        }else  return new Response("!!! Ошибка !!!");

    }
}

