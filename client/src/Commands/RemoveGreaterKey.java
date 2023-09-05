package Commands;

import Person.Person;

import java.util.Iterator;
import java.util.Map;

import static modules.LoadCSV.personHashMap;

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(){
        super("remove_greater_key null"," Удалить из коллекции все элементы, ключ которых меньше, чем заданный",true);

    }
    @Override
    public void execute() {
        try {
        Integer givenKey  = Integer.valueOf(getArgument());
        Iterator<Map.Entry<Integer, Person>> iterator = personHashMap.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Person> entry = iterator.next();
            if (entry.getKey() > givenKey) {
                iterator.remove();
                count++;
            }
        }
        System.out.println("Удалено элементов: " + count);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
