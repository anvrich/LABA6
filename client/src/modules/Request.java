package modules;

import Person.Person;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String arg;
    private Person person;

    public Request(String command) {
        this.command = command;
    }

    public Request(String command, Person person) {
        this.command = command;
        this.person = person;
    }

    public Request(String command, Person person, String arg) {
        this.command = command;
        this.person = person;
        this.arg = arg;
    }

    public String getCommand() {
        return command;
    }

    public Person getPerson() {
        return person;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", arg='" + arg + '\'' +
                ", person=" + person +
                '}';
    }
}
