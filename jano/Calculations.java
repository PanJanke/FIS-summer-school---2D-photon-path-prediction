package pl.jano;

import java.util.Random;

public class Calculations {

    public Point randomDirectionVector(Point vec, double cos){
        Random rand=new Random();

        double sin=Math.sqrt(1-cos*cos);

        if(rand.nextBoolean())
            sin=sin*-1;

        double x = cos*vec.x +  (-1.0*sin*vec.y);
        double y = sin*vec.x +  cos*vec.y;

        return new Point(x,y);
    }

    public Point turnDirectionVector(Point vec, double cos){
        double sin=Math.sqrt(1-cos*cos);
        double x = cos*vec.x +  (-sin*vec.y);
        double y = sin*vec.x +  cos*vec.y;

        return new Point(x,y);
    }

    public Point turnDirectionVectorClockwise(Point vec, double cos){
        double sin=Math.sqrt(1-cos*cos)*-1.0;
        double x = cos*vec.x +  (-sin*vec.y);
        double y = sin*vec.x +  cos*vec.y;

        return new Point(x,y);
    }







}

