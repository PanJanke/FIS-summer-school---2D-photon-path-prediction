package pl.jano.Objects;

public class Point {
    public double x;
    public  double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void normalizeVector() {
        double a = Math.sqrt(this.x * this.x + this.y * this.y);
        this.x = this.x / a;
        this.y = this.y / a;
    }

}