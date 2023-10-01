package Server;

import Commands.Save;
import modules.LoadCSV;
import modules.TCPserver;

import java.io.IOException;
import java.util.Scanner;
import static modules.LoadCSV.personHashMap;

public class Main {
    public static String  pathFile = null;

    public static void main(String[] args) throws IOException {
        TCPserver server = new TCPserver("localhost", 7894);

        if (args.length != 1) {
            System.out.println("Путь до файла csv нужно передать через аргумент командной строки!!!");
            System.exit(0);}
        else pathFile = args[0];

            LoadCSV.load(pathFile);
        System.out.println("Идёт загрузка коллекции");
            if (personHashMap.isEmpty()){System.out.println("Коллекция пуста и поэтому загруженно 0 элемента");}
            else System.out.println("Данные успешно загружены из файла file.csv: " +"Загружено объектов: " + personHashMap.size());
        JTread t = new JTread();
        t.start();
        server.start();
    }

    static class JTread extends Thread{
        public void run(){

            Scanner scan = new Scanner(System.in);
            while(true){
                String input = scan.nextLine();
                if(input.equals("save")){
                    try {
                        new Save().execute();
                        System.out.println("[Server]: Изменения сохранены.");
                    } catch (Exception ignored){}
                }
                else{
                    System.err.println("Неизвестная команда");
                }
            }
        }
    }
}