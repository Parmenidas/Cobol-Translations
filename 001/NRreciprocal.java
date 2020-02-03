import java.util.Scanner;

class NRreciprocal
{
    public static void main(String args[]){
        int n=10,i;

        System.out.println("Insert a number X, -10<x<10");
        Scanner in = new Scanner(System.in);
        double a=in.nextDouble();
        in.close();

        // Check the input
        if (a<-10 | a>10){
            System.out.println("Error: number x must be -10 < x < +10");
            System.exit(1);
        }
        
        if (a==0 ){
            System.out.println("Result: Infinite");
            System.exit(0);
        }

        //Initialize the algorithm
        double xnew=0.;
        double xold=(a<0) ? -0.01 : 0.01;

        //Run the algorithm
        System.out.format("Exact Result:%f\n",1./a);
        for (i=0;i<n;i++){
            xnew=NRinv(xold,a);
            System.out.format("Result:%f\n",xnew);
            xold=xnew;
        }

        return;
    }

    static double NRinv(double xold,double a){
        double xnew=xold * (2.-a * xold);
        return xnew;
    }

}
