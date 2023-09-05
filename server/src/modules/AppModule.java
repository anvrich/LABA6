package modules;

import java.util.Scanner;

public class AppModule {
    private static Scanner input;

    public static void setInput(Scanner scanner) {
        input = scanner;
    }

    public Scanner getInput() {
        return input;
    }
}
