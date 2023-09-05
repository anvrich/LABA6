package modules;

import java.util.Scanner;

public class AppModule {
    private static Scanner input;
    private static TCPclient client;

    public static void setInput(Scanner scanner) {
        input = scanner;
    }
    public static Scanner getInput() {
        return input;
    }

    public static void setClient(TCPclient client) {
        AppModule.client = client;
    }

    public static TCPclient getClient() {
        return client;
    }
}
