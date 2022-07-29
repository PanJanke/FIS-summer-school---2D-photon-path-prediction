package pl.jano.Window;

import pl.jano.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CartesianPanel extends JPanel {

    public double[][] tab = new double[800][800];
    public double[][] tabBorders = new double[800][800];

    public void getValues(double[][] tab){
        this.tab=tab;
    }

    public double getMaxValue(){
        double max=0.0;
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab[i].length; j++) {
                if(tab[i][j]>max)
                    max=tab[i][j];
            }
        return max;
    }

    public void setBorders(){
        for (int i = 0; i < tabBorders.length; i++)
            for (int j = 0; j < tabBorders[i].length; j++) {
                if(i==100 || i ==700 || j==100 || j==700)
                    tab[i][j]=-10;

            }
    }

    public void paintComponent(Graphics g) {

        Random rand = new Random();

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        double maxValue=getMaxValue();
        setBorders();



        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if(i==0 || j==0 || i==tab.length-1 || j ==tab.length-1)
                    g2.setColor(Color.orange);

                //else if(tab[i][j]>maxValue/1000.0) {
                else if(tab[i][j]>0.0) {
                   double gValue =  tab[i][j]/maxValue * 255;

                    g2.setColor(new Color(180,10,(int)gValue));
                }

                else
                    g2.setColor(Color.black);




                g.fillRect(i  , j , 1, 1);
            }
        }
        g2.setColor(Color.white);
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i == 100 || i == 700 || j == 100 || j == 700)
                    g.fillRect(i, j, 1, 1);
            }
            }


        /*
        g2.setColor(Color.green);
        int a = (int)Math.floor(0.5*800);
        int b = (int)Math.floor(0.05*800);
        g.fillRect(a  , b , 1, 1);
*/


    }
}