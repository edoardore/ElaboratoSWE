package singleton;

import abstractFactory.Icon;

public class Sun implements Icon {
    private Sun() {
    }

    private static Sun instance;
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static Sun getInstance() {
        if (instance == null)
            instance = new Sun();
        return instance;
    }

    @Override
    public void print() {
        System.out.print(ANSI_YELLOW + "☀" + ANSI_RESET);
    }
}