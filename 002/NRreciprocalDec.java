import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

class NRreciprocalDec
{
    public static void main(String args[]){
        int n=10,i;

        System.out.println("Insert a number X, -10<x<10");
        Scanner reader = new Scanner(System.in);
        int tmp=reader.nextInt();
        reader.close();

        // Check the input
        if (tmp < -10 | tmp > 10){
            System.out.println("Error: number x must be -10 < x < +10");
            System.exit(1);
        }
        
        if (tmp == 0 ){
            System.out.println("Result: Infinite");
            System.exit(0);
        }

        //Initialize the algorithm
        BigDecimal a = new BigDecimal(tmp);
        BigDecimal xnew=BigDecimal.ZERO;
        BigDecimal xold=new BigDecimal((a.compareTo(BigDecimal.ZERO)<0) ? "-0.01" : "0.01");

        //Run the algorithm
        for (i=0;i<n;i++){
            xnew=NRinv(xold,a);
            System.out.format("Result: %+f\n",xnew);
            xold=xnew;
        }
        System.out.format("Exact Result: %+f\n",trunc(BigDecimal.ONE.divide(a,5,RoundingMode.DOWN)));
        
        return;
    }

    static BigDecimal NRinv(BigDecimal xold,BigDecimal a){
        BigDecimal two=new BigDecimal("2");
        BigDecimal xnew=xold.multiply(two.subtract(a.multiply(xold)));
        xnew=xnew.setScale(5,RoundingMode.HALF_UP);  //rounding at the end
        return xnew;
    }

    static BigDecimal trunc(BigDecimal x){
        BigDecimal y;
        y = (x.compareTo(BigDecimal.ZERO)<0) ? x.setScale(5,RoundingMode.FLOOR) : x.setScale(5,RoundingMode.CEILING);
        return y;
    }
}