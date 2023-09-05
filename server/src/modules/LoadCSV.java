package modules;

import Person.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;


public class LoadCSV {
    public static HashMap<Integer, Person> personHashMap = new HashMap<>();
    public static Date date = new Date();
    public static void load(String pathFile)throws IOException {
        try (BufferedInputStream bIs = new BufferedInputStream(new FileInputStream(pathFile));
             BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(bIs))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");

                Integer id = Integer.parseInt(parts[0]);

                String name = parts[1];

                Float coordinatesX = Float.valueOf(parts[2]);
                Float coordinatesY = Float.valueOf(parts[3]);
                Coordinates coordinates = new Coordinates(coordinatesX, coordinatesY);

                LocalDate localDate = LocalDate.parse(parts[4]);

                Double height = Double.parseDouble(parts[5]);

                Long weight = Long.parseLong(parts[6]);

                Color eyeColor = Color.valueOf(parts[7]);

                ColorH hairColor = ColorH.valueOf(parts[8]);

                Float x = Float.parseFloat(parts[9]);
                double y = Double.parseDouble(parts[10]);
                float z = Float.parseFloat(parts[11]);
                Location location = new Location(x, y, z);

                Person obj = new Person(name, coordinates, localDate, height, weight, eyeColor, hairColor, location);
                personHashMap.put(id, obj);
            }
        }

    }
}
