package ru.netology;

public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;
    public static char[] field = new char[MAX_POSITION + 1];


    protected int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        // сделаем прыжок, если прыжок слишком большой
        // для поля, то не прыгнем и вернём false
        if (position + steps < MIN_POSITION || position + steps > MAX_POSITION) {
            System.out.println("Некуда прыгать!");
            printField();
            return false;
        } else {
            position += steps;
            printField();
            return true;
        }
    }

    public void printField() {
        for (int i = MIN_POSITION; i <= MAX_POSITION; i++) {
            field[i] = i == position ? 'O' : '-';
            System.out.print(field[i] + " ");
        }
        System.out.println();
    }
}
