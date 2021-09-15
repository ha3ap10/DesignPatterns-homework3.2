package ru.netology;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        Frog frog = new Frog();

        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;

        System.out.println("Есть поле - одномерный массив из 11 клеток, посередине сидит лягушка. " +
                "Мы можем давать ей команды:\n\n" +
                "+N - прыгни на N шагов направо\n" +
                "-N - прыгни на N шагов налево\n" +
                "<< - Undo (отмени последнюю команду)\n" +
                ">> - Redo (повтори отменённую команду)\n" +
                "!! - повтори последнюю команду\n" +
                "0 - выход\n");

        while (true) {

//            System.out.println("Номер текущей команды: " + (curCommand + 1));
//            System.out.println("Всего команд: " + commands.size());

            System.out.println("Введите команду:");
            input = scanner.nextLine();

            if (input.equals("0")) break;
            if (input.equals("<<")/*пользователь хочет отменить действие*/) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    curCommand--;
                }
            } else if (input.equals(">>")/*пользователь хочет повторить действие*/) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего повторять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).doIt();
                }
            } else { //пользователь ввёл новое движение для лягушки
                if (curCommand != commands.size() - 1) {
                    //удаляем все команды которые были отменены
                    Iterator<FrogCommand> commandIterator = commands.iterator();
                    while (commandIterator.hasNext()) {
                        FrogCommand frogCommands = commandIterator.next();
                        if (commands.indexOf(frogCommands) > curCommand) {
                            commandIterator.remove();
                        }
                    }
                }

                FrogCommand cmd;
                if (input.equals("!!")) {
                    if (curCommand == -1) {
                        System.out.println("Ещё не было других команд!");
                        continue;
                    }
                    cmd = commands.get(curCommand);
                } else {
                    cmd = FrogCommands.jumpCommand(frog, Integer.parseInt(input));
                }

                if (cmd.doIt()) {
                    curCommand++;
                    commands.add(cmd);
                }
            }
        }
    }
}
