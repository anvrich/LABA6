package Commands;

import Person.*;
import modules.Response;

import java.util.Map;
import static modules.LoadCSV.personHashMap;

public class Update extends Command {
    public Update() {
        super("update id {element}", "Обновить значение элемента коллекции, id которого равен заданному", true);

    }

    @Override
    public Response execute() {
        Integer id;
        try {
            id = Integer.valueOf(getArgument());
        } catch (NumberFormatException e) {
            return new Response("Введен некорректный формат ID!");
        }
        Person person = getPerson();
        if (id < 0) {
            return new Response("Неверный формат аргумента!!!");
        }
        if (personHashMap.containsKey(id)) {
            personHashMap.get(id).update(person);
            return new Response("Информация пользователя с ID " + id + " успешно обновлена");
        }
        return new Response("!!! Пользователь с таким ID не найден !!!");
    }
}
