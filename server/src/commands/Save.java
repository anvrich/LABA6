package commands;

import Person.*;
import modules.Response;

import static modules.LoadCSV.personHashMap;

import static Server.Server.pathFile;
import java.io.*;
import java.time.LocalDate;
import java.util.Map;

public class Save extends Command {
    public Save(){
        super("save","Сохранить коллекцию в файл",false);
    }
    @Override
    public Response execute() {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathFile, false));
             BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(bos))) {

            for (Map.Entry<Integer, Person> inf : personHashMap.entrySet()) {
                Integer id = inf.getKey();
                Person person = inf.getValue();
                String name = person.getName();
                Coordinates coordinates = person.getCoordinates();
                LocalDate creationDate = person.getCreationDate();
                Double height = person.getHeight();
                Long weight = person.getWeight();
                Color eyeColor = person.getEyeColor();
                ColorH hair = person.getHairColor();
                Location location = person.getLocation();
                String line = id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate + "," + height + "," + weight + "," + eyeColor + "," + hair + "," + location.getX() + "," + location.getY() + "," + location.getZ();

                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            if (personHashMap.isEmpty()) {
                System.out.println("Коллекция пуста и поэтому сохранилось 0 элементов");
            } else {
                System.out.println("Данные успешно записаны в файл file.csv");
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
        return new Response("Коллекция сохранена.");
    }
}
