package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    protected void notifyObservers() {
        for (Observer o : observers)
            o.update(this);
    }

    public abstract int getTemp();

    public abstract void setTemp(int s);

    public abstract String getName();
}