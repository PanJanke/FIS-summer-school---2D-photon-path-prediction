package pl.jano;

public class Barrier {
    double y0;
    double y1;
    Medium medium0;
    Medium medium1;


    public Barrier(double y0, double y1, Medium medium0,Medium medium1) {
        this.y0 = y0;
        this.y1 = y1;
        this.medium0=medium0;
        this.medium1=medium1;
    }




    public void caluclatePoisiton(Photon p){
        double calculate = (y0-p.pos.y)/p.Uvector.y;
        p.pos.y=y0;
        p.pos.x+=calculate*p.Uvector.x;
        //p.pos.x+=(y0-p.pos.y)*p.Uvector.x;
    }
    public double calculateAngle(Photon p, Point candidate){
        double y = p.Uvector.y;
        // if(y>1.0)
        // y=1.0;
        double alpha=Math.acos(Math.abs(y));

        return (p.medium.n/medium0.n) * alpha;
    }

    public Point setAngle(Photon p, Point candidate,double alphab){
        Calculations calculations = new Calculations();

    if(candidate.x<p.pos.x)
        return calculations.turnDirectionVector(new Point(0,1.0),Math.cos(alphab));

    else
        return calculations.turnDirectionVectorClockwise(new Point(0,1.0),Math.cos(alphab));
    }
}
