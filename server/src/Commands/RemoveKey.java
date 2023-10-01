package Commands;

import modules.Response;

import static modules.LoadCSV.personHashMap;

public class RemoveKey extends Command {
    public RemoveKey(){
        super("remove_key null", "Удалить элемент из коллекции по его ключу",true);

    }
    @Override
    public Response execute() {
        try {
            Integer key = Integer.valueOf(getArgument());
            if (key < 0) {
                return new Response("Ключ не может быть отрицательным числом.");
            }
            if (personHashMap.containsKey(key)) {
                personHashMap.remove(key);
               return new Response("Элемент с ключом " + key + " успешно удален из коллекции.");
            } else {
                return new Response("Элемент с ключом " + key + " не найден в коллекции.");
        }
        }catch (NumberFormatException e) {
            return new Response("Ошибка: введите корректное числовое значение для ключа.");
        } catch (Exception e) {
            return new Response("Произошла ошибка: " + e.getMessage());
        }
    }
}
