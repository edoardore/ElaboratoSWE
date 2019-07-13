package singleton;

import abstractFactory.Icon;

public class Cloud implements Icon {
    private Cloud() {
    }

    private static Cloud instance;

    public static Cloud getInstance() {
        if (instance == null)
            instance = new Cloud();
        return instance;
    }

    @Override
    public void print() {
        System.out.print("☁");
    }
}