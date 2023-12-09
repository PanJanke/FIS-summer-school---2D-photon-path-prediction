package pl.jano.Objects;

import pl.jano.Algorithm.Calculations;

public class Barrier {
    public double y0;
    public double y1;
    public Medium medium0;
    public Medium medium1;

    public Barrier(double y0, double y1, Medium medium0,Medium medium1) {
        this.y0 = y0;
        this.y1 = y1;
        this.medium0=medium0;
        this.medium1=medium1;
    }

    public void caluclatePosition(Photon p){
        double calculate = (y0-p.pos.y)/p.uVector.y;
        p.pos.y=y0;
        p.pos.x+=calculate*p.uVector.x;

    }
    public double calculateAngle(Photon p){
        double y = p.uVector.y;
        double alpha=Math.acos(Math.abs(y));
        return (p.medium.n/medium0.n) * alpha;
    }

    public Point setAngle(Photon p, Point candidate, double alphaB){
        Calculations calculations = new Calculations();

    if(candidate.x<p.pos.x)
        return calculations.turnDirectionVector(new Point(0,1.0),Math.cos(alphaB));
    else
        return calculations.turnDirectionVectorClockwise(new Point(0,1.0),Math.cos(alphaB));
    }
}
