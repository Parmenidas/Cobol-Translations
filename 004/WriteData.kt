import java.io.File

class FixedLengthString(text: String, n: Int){
    val value: String

    init{
        if (text.length>n){
            value=text.substring(0,n)
        }
        else{
            value=String.format("%-"+n+"s",text)
        }
    }
}


class SSN(text: String){

    companion object Constants{
        val  MAX_A=3
        val  MAX_B=2
        val  MAX_C=4
        val length=MAX_A+MAX_B+MAX_C

    }

    val fText=FixedLengthString(text,MAX_A+MAX_B+MAX_C)
    var A= fText.value.substring(0,MAX_A)
    var B= fText.value.substring(MAX_A,MAX_A+MAX_B)
    var C= fText.value.substring(MAX_A+MAX_B,MAX_A+MAX_B+MAX_C)

    public fun GetAll(): String{
        val number=String.format("$A-$B-$C")
        return number
    }
    
}

class Employee(text: String){
    val MAX_L=10
    var tmp=FixedLengthString(text,3*MAX_L+SSN.length)

    var fname=tmp.value.substring(0,MAX_L)
    var lname=tmp.value.substring(MAX_L,2*MAX_L)
    var ssn=SSN(tmp.value.substring(2*MAX_L,2*MAX_L+SSN.length))
    var city=tmp.value.substring(2*MAX_L+SSN.length,3*MAX_L+SSN.length)
}

fun main(){

    val N=3
    var one = Employee("") // Let's allocate an empty object

    // Set values
    one.fname=FixedLengthString("Michael",10).value
    one.lname=FixedLengthString("Smith",10).value
    one.ssn=SSN("123456789")
    one.city=FixedLengthString("Portland",10).value
    println("${one.fname}, ${one.lname} SSN: ${one.ssn.GetAll()}")

    val FileUsed=File("data.kotlin.dat")

    FileUsed.printWriter().use{ out -> for (i in 1..N) out.print("${one.fname}${one.lname}${one.ssn.A}${one.ssn.B}${one.ssn.C}${one.city}")};  // Here we write the record N times

    // Nothing to close
}