package pl.jano.Algorithm;

import pl.jano.GUI.CartesianFrame;
import pl.jano.Objects.Barrier;
import pl.jano.Objects.Medium;
import pl.jano.Objects.Photon;
import pl.jano.Objects.Point;

import java.util.Random;

public class Manager {
    int helpCounter = 0;
    int interfaceCounter = 0;
    int absorbCounter = 0;
    int deadCounter = 0;
    int arraySize = 800;
    int numberOfPhotons = 600000;
    double[][] tab = new double[arraySize][arraySize];
    Point vector = new Point(0.8, 0.8);
    Barrier barrier = new Barrier(0.125, 600, new Medium(200, 10, 0.9999, 2), new Medium(1, 210, -1.0, 3.0));

    public void run() {

        int border = 1;
        double adaptVision = arraySize / (double) border;

        Medium medium = new Medium(1, 1, 0.99999, 1);
        vector.normalizeVector();

        for (int i = 0; i < numberOfPhotons; i++) {
            Photon p = new Photon(new Point(0.9, 0.02), new Point(-2, 0.5));
            p.setMedium(medium);
            working(p, adaptVision);
        }

        CartesianFrame frame = new CartesianFrame();
        frame.panel.getValues(tab);
        frame.showUI("photon MC");

    }


    public void working(Photon p, double adaptV) {
        Generator gen = new Generator();
        Point moveCandid;

        while (p.alive) {
            double length;


            length = gen.distanceGenerator(p.medium);

            moveCandid = p.moveToCandidate(length);

            if (absorbed(moveCandid)) {
                absorbCounter++;
                break;
            }

            if (!p.changeMedium && moveCandid.y >= 0.125) {
                interfaceCounter++;
                barrier.caluclatePosition(p);
                double criticalAngle = p.medium.calculateCriticalAngle(barrier.medium0.n);
                p.uVector.normalizeVector();
                double alpha = Math.acos(Math.abs(p.uVector.y));
                double alphaB = barrier.calculateAngle(p);

                if (alpha > criticalAngle && p.medium.n > barrier.medium0.n) {
                    p.uVector.y *= -1.0;
                    continue;
                } else {
                    double R = getR(alpha, alphaB);
                    if (getThrough(R)) {
                        p.uVector = barrier.setAngle(p, moveCandid, alphaB);
                    } else {
                        p.uVector.y *= -1.0;
                    }
                }
                p.setMedium(barrier.medium0);
                helpCounter++;
                p.changeMedium = !p.changeMedium;
                continue;
            }

            p.move(moveCandid);
            p.UvectorUpdate();
            p.wageUpdate();

            if (p.wage < 0.1) {
                p.wageRoulette(0.1);
            }

            int a = (int) Math.floor(p.pos.x * adaptV);
            int b = (int) Math.floor(p.pos.y * adaptV);
            if (a < 700 && b < 700 && a > 100 && b > 10)
                this.tab[a][b] += p.medium.Ua / p.medium.Ut;

            if (!p.alive) {
                deadCounter++;
            }
        }
    }

    public boolean absorbed(Point p) {
        return !(p.x < 1) || !(p.y < 1) || !(p.x > 0) || !(p.y > 0);
    }

    public void sumUp() {
        System.out.println(deadCounter + absorbCounter);
        System.out.println(numberOfPhotons);
        System.out.println(helpCounter);
    }


    public double getR(double La, double Lb) {

        if (La + Lb < 1.0E-05)
            return 0.0;
        double a = Math.pow(Math.sin(La - Lb), 2.0) / Math.pow(Math.sin(La + Lb), 2.0);
        double b = Math.pow(Math.tan(La - Lb), 2.0) / Math.pow(Math.tan(La + Lb), 2.0);
        return 0.5 * (a - b);
    }

    public boolean getThrough(double R) {
        Random rand = new Random();
        return rand.nextDouble() > R;
    }


}
