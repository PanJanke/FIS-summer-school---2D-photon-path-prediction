package pl.jano;

import java.util.Random;

public class Generator {

    Random random = new Random();

    public double distanceGenerator(Medium m){
        double distance = -Math.log(1-random.nextDouble())/m.Ut;
        return distance;
    }

    public double cosinusValueGenerator(double g){
        if(Math.abs(g)<10.0E-05)
            return 2*random.nextDouble()-1;
        else{
            double a = 1+g*g;
            double b = (1-g*g)/(1-g+2*g* random.nextDouble());
            return 1/(2*g)*(a-b*b);
            //return 0.999;
            //return 1.0;
        }
    }



}
