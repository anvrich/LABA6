package Commands;

import static modules.LoadCSV.personHashMap;

public class Clear extends Command {
    public Clear(){
        super("clear","Очистить коллекцию",false);
}
    @Override
    public void execute() {
        personHashMap.clear();
        System.out.println("Коллекцие очистилось");
    }
}
