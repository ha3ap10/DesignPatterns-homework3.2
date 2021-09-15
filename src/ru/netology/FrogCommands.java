package ru.netology;

public class FrogCommands{
    public static FrogCommand jumpCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean doIt() {
                return frog.jump(steps);
            }

            @Override
            public boolean undo() {
                return frog.jump(-steps);
            }
        };
    }
}
