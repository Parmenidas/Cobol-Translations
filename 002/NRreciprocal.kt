import java.util.Scanner
import java.math.BigDecimal
import java.math.RoundingMode

import kotlin.system.exitProcess

fun main(){
    val n=10;

    println("Insert a number X, -10<x<10")
    val reader = Scanner(System.`in`)
    val tmp: Int =reader.nextInt()

    reader.close()

    // Check the input
    if ( (tmp < -10) || (tmp > 10) ) {
        println("Error: number x must be -10 < x < +10")
        exitProcess(1)
    }
    
    if (tmp == 0 ){
        println("Result: Infinite")
        exitProcess(0)
    }

    //  Initialize the algorithm
    val a = BigDecimal(tmp)

    var xold=if (a.compareTo(BigDecimal.ZERO)<0) BigDecimal("-0.01") else BigDecimal("0.01")

    // Run the algorithm

    for (i in 0..n){
        var xnew=NRinv(xold,a)
        println("Result: %+f".format(xnew))
        xold=xnew;
    }

    println("Exact Result: %+f".format(trunc(BigDecimal.ONE.divide(a,5,RoundingMode.DOWN))))
}

fun NRinv(xold:BigDecimal, a:BigDecimal):BigDecimal{
    val two=BigDecimal("2")
    var xnew=xold.multiply(two.subtract(a.multiply(xold)))
    xnew=xnew.setScale(5,RoundingMode.HALF_UP)  //rounding at the end
    return xnew
}

fun  trunc(x:BigDecimal):BigDecimal{
    val y = if (x.compareTo(BigDecimal.ZERO)<0) x.setScale(5,RoundingMode.FLOOR) else x.setScale(5,RoundingMode.CEILING)
    return y
}

