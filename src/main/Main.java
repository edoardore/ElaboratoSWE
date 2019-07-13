package main;

import abstractFactory.AbstractFactory;
import abstractFactory.Icon;
import observer.Observer;
import observer.Subject;
import singleton.Cloud;
import singleton.Snow;
import singleton.Sun;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] paesi = {"NewYork", "SanMarino", "Firenze", "Prato", "Pistoia", "Empoli", "Arezzo", "Viareggio",
                "Sutri", "Siracusa", "Trento", "Rimini", "Genova", "Alassio", "Assisi", "Londra", "Sassari",
                "Pontassieve", "Milano", "Roma", "Napoli", "Pisa", "Lucca"};
        Subject[] termometri = new Termometro[paesi.length];
        for (int i = 0; i < termometri.length; i++) {
            termometri[i] = new Termometro((int) (Math.random() * 40), paesi[i]);
        }
        Display display = new Display();
        for (Subject t : termometri)
            t.attach(display);
        int h = 9;
        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("\u001B[31m" + "Rilevazione Meteo delle ore " + (h++) + ":00" + "\u001B[0m");
            System.out.println();
            for (Subject t : termometri) {
                if ((int) (Math.random() * 2) == 1)
                    t.setTemp(t.getTemp() - (int) (Math.random() * 5));
                else
                    t.setTemp(t.getTemp() + (int) (Math.random() * 5));
            }
            Thread.sleep(5000);
        }
        for (Subject t : termometri)
            t.detach(display);
    }
}

class Termometro extends Subject {
    private int temperatura;
    private final String cityName;

    public Termometro(int t, String n) {
        temperatura = t;
        cityName = n;
    }

    @Override
    public void setTemp(int s) {
        temperatura = s;
        notifyObservers();
    }

    @Override
    public int getTemp() {
        return temperatura;
    }


    @Override
    public String getName() {
        return cityName;
    }
}

class Display implements Observer {
    private int temp;
    private String city;
    private AbstractFactory iconFactory;

    @Override
    public void update(Subject s) {
        temp = s.getTemp();
        city = s.getName();
        System.out.println("Meteo attuale di " + city);
        iconFactory = new IconFactory();
        if (temp < 4) {
            Icon snow = iconFactory.getIcon("SNOW");
            snow.print();
            System.out.println(" " + temp + "° C");
        } else if (temp < 15) {
            Icon cloud = iconFactory.getIcon("CLOUD");
            cloud.print();
            System.out.println(" " + temp + "° C");
        } else if (temp > 14) {
            Icon sun = iconFactory.getIcon("SUN");
            sun.print();
            System.out.println(" " + temp + "° C");
        }
    }
}


class IconFactory extends AbstractFactory {

    @Override
    public Icon getIcon(String iconType) {

        if (iconType == null) {
            return null;
        }

        if (iconType.equalsIgnoreCase("CLOUD")) {
            return Cloud.getInstance();

        } else if (iconType.equalsIgnoreCase("SNOW")) {
            return Snow.getInstance();

        } else if (iconType.equalsIgnoreCase("SUN")) {
            return Sun.getInstance();
        }

        return null;
    }
}