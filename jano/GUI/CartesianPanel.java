package pl.jano.GUI;

import javax.swing.*;
import java.awt.*;

public class CartesianPanel extends JPanel {

    private final int size = 800;

    public double[][] tab = new double[size][size];
    public double[][] tabBorders = new double[size][size];

    public void getValues(double[][] tab) {
        this.tab = tab;
    }

    public double getMaxValue() {
        double max = 0.0;
        for (double[] doubles : tab) {
            for (double aDouble : doubles) {
                if (aDouble > max)
                    max = aDouble;
            }
        }
        return max;
    }

    public void setBorders() {
        for (int i = 0; i < tabBorders.length; i++)
            for (int j = 0; j < tabBorders[i].length; j++) {
                if (i == 100 || i == 700 || j == 100 || j == 700)
                    tab[i][j] = -10;

            }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double maxValue = getMaxValue();
        setBorders();


        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i == 0 || j == 0 || i == tab.length - 1 || j == tab.length - 1)
                    g2.setColor(Color.orange);

                else if (tab[i][j] > 0.0) {
                    double gValue = tab[i][j] / maxValue * 255;

                    g2.setColor(new Color(180, 10, (int) gValue));
                } else
                    g2.setColor(Color.black);
                g.fillRect(i, j, 1, 1);
            }
        }
        g2.setColor(Color.white);

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i == 100 || i == 700 || j == 100 || j == 700)
                    g.fillRect(i, j, 1, 1);
            }
        }

    }
}