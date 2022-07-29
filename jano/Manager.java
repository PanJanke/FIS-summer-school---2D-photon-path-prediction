package pl.jano;

import pl.jano.Window.CartesianFrame;

import java.util.Random;

public class Manager {
    int helpcounter=0;
    int interfaceCounter=0;
    int absorbCounter=0;
    int deadCounter =0;
    Photon p;
    int arraySize=800;
    int numberOfPhotons=600000;
    double[][] tab = new double[arraySize][arraySize];
    Point vector =new Point(0.8,0.8);

    Barrier barrier = new Barrier(0.125,600,new Medium(200,10,0.9999,2),new Medium(1,210,-1.0,3.0));


    public void run(){

        int border =1;
        double adaptVision=arraySize/(double)border;

        Medium medium =new Medium(1,1,0.99999,1);
        vector.normalizeVector();

        for(int i=0;i<numberOfPhotons;i++) {
            Photon p = new Photon(new Point(0.9,0.02),new Point(-2,0.5));
            p.setMedium(medium);
            working(p,adaptVision,i);
        }
        //System.out.println("interface" + interfaceCounter);

        CartesianFrame frame = new CartesianFrame();
        frame.panel.getValues(tab);
        frame.showUI("photon MC");

    }

    public static void nonZEro(double[][] tab){
        int counter=0;
        for(int i=0;i<800;i++)
            for(int j=0;j<800;j++)
                if(tab[i][j]>0)
                    counter++;
        System.out.println(counter);
    }

    public  void  working(Photon p,double adaptV,int i){
        Calculations calc = new Calculations();
        Generator gen = new Generator();
        //System.out.println(p.Uvector.info());
        Point moveCandid;
        boolean first=true;

        while(p.alive){
            double length;
/*
            if(first){
                length = 0.1;
                first=false;
            }

 */

            length= gen.distanceGenerator(p.medium);

            moveCandid = p.moveToCandidate(length);

            //check candidate
            if(absorbed(moveCandid)){
                absorbCounter++;
                break;
            }

            if(!p.changeMedium)
            if(moveCandid.y>=0.125){
                interfaceCounter++;
                //System.out.println(i);


                barrier.caluclatePoisiton(p);

                double criticalAngle=p.medium.calculateCriticalAngle(barrier.medium0.n);
                p.Uvector.normalizeVector();
                double alpha = Math.acos(Math.abs(p.Uvector.y));
                double alphab= barrier.calculateAngle(p,moveCandid);
                //System.out.println(alpha+" "+criticalAngle);


                if(alpha>criticalAngle && p.medium.n>barrier.medium0.n){
                    //System.out.println(p.pos.info());
                    p.Uvector.y*=-1.0;
                    //p.changeMedium=true;
                    continue;

                }

                else{

                    double R = getR(alpha,alphab);

                    if(getThrougth(R)){
                        p.Uvector=barrier.setAngle(p,moveCandid,alphab);
                    }
                    else {
                        p.Uvector.y *= -1.0;

                    }

                }





                p.setMedium(barrier.medium0);
                helpcounter++;
                p.changeMedium=!p.changeMedium;
                continue;
            }

            p.move(moveCandid);
            p.UvectorUpdate();
            p.wageUpdate();

            if(p.wage<0.1) {
                p.wageRoulette(0.1);
            }

            int a = (int)Math.floor(p.pos.x*adaptV);
            int b = (int)Math.floor(p.pos.y*adaptV);
            if(a<700 && b < 700 && a>100 && b>10)
                this.tab[a][b]+=p.medium.Ua / p.medium.Ut;

            if(!p.alive){
                deadCounter++;
            }

        }
    }

    public boolean absorbed(Point p){
        if(p.x<1 && p.y < 1 && p.x>0 && p.y>0)
            return false;
        else
            return true;
    }

    public void sumUp(){
        System.out.println(deadCounter +absorbCounter);
        System.out.println(numberOfPhotons);
        System.out.println(helpcounter);
    }





    public void Mediumchange(double nb){
        double angle = p.calculateAngle();

    }

    public double getR(double La, double Lb){

        if(La+Lb<1.0E-05)
            return 0.0;
        double a= Math.pow(Math.sin(La-Lb),2.0)/ Math.pow(Math.sin(La+Lb),2.0);
        double b= Math.pow(Math.tan(La-Lb),2.0)/ Math.pow(Math.tan(La+Lb),2.0);
        return 0.5*(a-b);
    }

    public boolean getThrougth(double R){
        Random rand = new Random();
        return rand.nextDouble()>R;
    }




}
