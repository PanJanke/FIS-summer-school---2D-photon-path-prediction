package pl.jano.Objects;

import pl.jano.Algorithm.Calculations;
import pl.jano.Algorithm.Generator;

import java.util.Random;

public class Photon {
    public double wage;
    public Point pos;
    public Point uVector;
    public Point StatVector;
    public Medium medium;
    public boolean alive;
    public boolean changeMedium = false;

    public Photon(Point position, Point vector) {
        wage = 1.0;
        pos = position;
        uVector = vector;
        StatVector = vector;
        alive = true;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void wageUpdate() {
        this.wage -= medium.Ua / medium.Ut;
    }


    public void wageRoulette(double border) {
        Random rand = new Random();
        if (rand.nextDouble() < border)
            this.alive = false;
        else
            this.wage = this.wage * 1 / border;
    }

    public void UvectorUpdate() {
        Generator gen = new Generator();
        Calculations calc = new Calculations();
        double cos = gen.cosinusValueGenerator(medium.g);

        this.uVector = calc.randomDirectionVector(uVector, cos);
    }

    public Point moveToCandidate(double length) {
        double x = this.pos.x + this.uVector.x * length;
        double y = this.pos.y + this.uVector.y * length;
        return new Point(x, y);
    }

    public void move(Point p) {
        this.pos = p;
    }

}
