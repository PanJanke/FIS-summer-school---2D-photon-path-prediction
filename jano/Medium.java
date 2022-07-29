package pl.jano;

public class Medium {
    double Ua;
    double Us;
    double Ut;
    double g;
    double n;

    public Medium(double ua, double us,double g, double n) {
        Ua = ua;
        Us = us;
        Ut=ua+us;
        this.g=g;
        this.n=n;
    }

    public double calculateCriticalAngle(double nb){
        return Math.asin(nb/this.n);
    }



}
