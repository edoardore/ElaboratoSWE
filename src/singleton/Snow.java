package singleton;

import abstractFactory.Icon;

public class Snow implements Icon {
    private static Snow instance;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private Snow() {
    }

    public static Snow getInstance() {
        if (instance == null)
            instance = new Snow();
        return instance;
    }

    @Override
    public void print() {
        System.out.print(ANSI_BLUE + "❄" + ANSI_RESET);
    }
}