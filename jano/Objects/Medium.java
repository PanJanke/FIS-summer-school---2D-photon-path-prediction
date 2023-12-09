package pl.jano.Objects;

public class Medium {
    public double Ua;
    public double Us;
    public double Ut;
    public double g;
    public double n;

    public Medium(double ua, double us, double g, double n) {
        Ua = ua;
        Us = us;
        Ut = ua + us;
        this.g = g;
        this.n = n;
    }

    public double calculateCriticalAngle(double nb) {
        return Math.asin(nb / this.n);
    }


}
