//package Commands;
//public class ExecuteScript extends Command {
//    public ExecuteScript() {
//        super("execute_script", "Считать и исполнить скрипт из указанного файла.", true);
//    }
//    @Override
//    public void execute() {
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package Commands;
////
////import modules.InputDataForm;
////
////import java.io.*;
////import java.util.HashSet;
////import java.util.Set;
////
////public class ExecuteScript extends Command {
////
////    private static Set<String> executeSet = new HashSet<>();
////
////    public ExecuteScript() {
////        super("execute_script file_name", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", true);
////    }
////
////    public void executeScript(BufferedReader inputStreamReader) throws IOException {
////        System.out.println("Выполнение команд.");
////
////        Command cmd = null;
////        String nextLine;
////        while ((nextLine = inputStreamReader.readLine()) != null) {
////            // Если cmd пустой, то мы считываем новую команду
////            if (cmd == null) {
////                if (CommandManager.getComands().containsKey(nextLine.trim())) {
////                    cmd = CommandManager.getComands().get(nextLine.trim());
////                    System.out.println(nextLine.trim());
////                } else {
////                    System.out.println("Команда не найдена. Мануал по командам - команда help");
////                    cmd = null;
////                }
////            } else {
////                // Считываем данные для команды
////                InputDataForm.executeScriptData.add(nextLine);
////
////                // Проверяем, собрали ли мы все необходимые данные
////                if (InputDataForm.executeScriptData.size() == 8) {
////                    cmd.execute();
////                    cmd = null; // сбросить команду для следующей итерации
////                    InputDataForm.executeScriptData.clear();
////                }
////            }
////        }
////        if (InputDataForm.executeScriptData.size() != 0) {
////            System.out.println("!!! Не хватает данных на файле !!!");
////            InputDataForm.executeScriptData.clear();
////        }
////    }
////
////    @Override
////    public void execute() {
////        if(!executeSet.contains(getArgument())) {
////            executeSet.add(getArgument());
////            File file;
////            try {
////                file = new File(getArgument());
////            } catch (NullPointerException e) {
////                System.out.println("Файл не найден!");
////                return;
////            }
////
////            try (BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
////                executeScript(inputStreamReader);
////                executeSet.remove(getArgument());
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
////        } else {
////            System.out.println("Упс, такой файл уже запущен. Рекурсия ЗАПРЕЩЕНА!!!");
////        }
////    }
////}
