package pl.jano;

import pl.jano.Window.CartesianFrame;

public class Main {

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.run();
        manager.sumUp();
        /*
        Generator gen = new Generator();
        for(double i=0.01;i<=1;i=i+0.01)
            System.out.println(i+";"+gen.cosinusValueGenerator(i));


         */
    }



}
