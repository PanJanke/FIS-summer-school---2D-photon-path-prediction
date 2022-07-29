package pl.jano;

import java.util.Random;

public class Photon {
    double wage;
    Point pos;
    Point Uvector;
    Point StatVector;
    Medium medium;
    boolean alive;
    boolean changeMedium=false;

    Photon(Point position,Point Vector){
        wage=1.0;
        pos=position;
        Uvector=Vector;
        StatVector=Vector;
        alive=true;
    }

    public void setMedium(Medium medium){
        this.medium=medium;
    }

    public void wageUpdate(){
        this.wage-= medium.Ua / medium.Ut;
    }



    public void wageRoulette(double border){
        Random rand = new Random();
        if(rand.nextDouble()<border)
            this.alive=false;
        else
            this.wage=this.wage * 1/border;
    }

    public void UvectorUpdate(){
        Generator gen = new Generator();
        Calculations calc = new Calculations();
        double cos = gen.cosinusValueGenerator(medium.g);

        this.Uvector= calc.randomDirectionVector(Uvector,cos);
    }

    public void info() {
        System.out.println("Position: "+this.pos.info());
        System.out.println("Static Vector: "+this.Uvector.info());
        System.out.println("changing Vector: "+this.StatVector.info());
        System.out.println("Wage: "+this.wage);
        System.out.println();
    }

    public double calculateAngle(){
        return Math.acos(Math.abs(this.Uvector.y));
    }

    public Point moveToCandidate(double length){
        double x=this.pos.x+ this.Uvector.x*length;
        double y=this.pos.y+ this.Uvector.y*length;
        return new Point(x,y);
    }
    public void move(Point p){
        this.pos=p;
    }

}
