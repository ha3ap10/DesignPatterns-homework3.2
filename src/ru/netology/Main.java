package ru.netology;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean cycle = true;
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

        while (cycle) {

            System.out.println("Номер текущей команды: " + (curCommand + 1));
            System.out.println("Всего команд: " + commands.size());

            System.out.println("Введите команду:");
            input = scanner.nextLine();
            FrogCommand cmd = null;
            int steps;

            switch (input) {
                case "0":
                    cycle = false;
                    System.out.println("Game over! Bye!");
                    break;
                case "<<":
                    if (curCommand < 0) {
                        System.out.println("Нечего отменять!");
                    } else {
                        commands.get(curCommand).undo();
                        curCommand--;
                    }
                    break;
                case ">>":
                    if (curCommand == commands.size() - 1) {
                        System.out.println("Нечего повторять!");
                    } else {
                        curCommand++;
                        commands.get(curCommand).doIt();
                    }
                    break;
                case "!!":
                    if (curCommand == -1) {
                        System.out.println("Ещё не было других команд!");
                    } else {
                        cmd = commands.get(curCommand);
                    }
                default:
                    if (input.startsWith("-")) {
                        steps = Integer.parseInt(input.substring(1));
                        cmd = FrogCommands.jumpLeftCommand(frog, steps);
                    } else if (input.startsWith("+")) {
                        steps = Integer.parseInt(input.substring(1));
                        cmd = FrogCommands.jumpRightCommand(frog, steps);
                    }
                    if (cmd != null && cmd.doIt()) {
                        curCommand++;
                        commands.add(cmd);
                    }
            }
        }
    }
}
