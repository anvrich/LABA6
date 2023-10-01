package Commands;
import Person.Person;
public abstract class Command implements Executable, Describable{
    private String name;
    private Person person;
    private String description;
    private String argument;
    private boolean withArgument = false;
    public Command(String name, String description, boolean withArgument) {
        this.name = name;
        this.description = description;
        this.withArgument = withArgument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getArgument() {
        return argument;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
    public boolean isWithArgument(){
        return withArgument;
    }


}