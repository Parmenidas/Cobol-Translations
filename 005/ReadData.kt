import java.io.File
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    
    companion object Constants{ // Added
        val  MAX_L=10
        val length=3*MAX_L+SSN.length
    }


    //val MAX_L=10
    var tmp=FixedLengthString(text,3*MAX_L+SSN.length)

    var fname=tmp.value.substring(0,MAX_L)
    var lname=tmp.value.substring(MAX_L,2*MAX_L)
    var ssn=SSN(tmp.value.substring(2*MAX_L,2*MAX_L+SSN.length))
    var city=tmp.value.substring(2*MAX_L+SSN.length,3*MAX_L+SSN.length)

}

fun main(){

    lateinit var one : Employee
    var buffRead: BufferedReader?=null
    val FileUsed=File("data.kotlin.dat")
    var tmpString = CharArray(Employee.length);
    
    try{
        val reader = FileReader(FileUsed); 
        buffRead=BufferedReader(reader);        
        var charsRead = buffRead.read(tmpString,0,Employee.length);
        if (charsRead == -1){
            println("ERROR : File Empty")
        }else{
            println("No errors. Reading...")
            while(charsRead > 0){
                one=Employee( String(tmpString))
                println("${one.fname}, ${one.lname} SSN: ${one.ssn.GetAll()}")
                charsRead = buffRead.read(tmpString,0,Employee.length);
            }
            tmpString.fill('*')
            one=Employee( String(tmpString))
            println("${one.fname}, ${one.lname} SSN: ${one.ssn.GetAll()}")
            println("End of File Reached")
        }
    
    }catch (e : FileNotFoundException){
        println("ERROR : File NOT found")
    } catch ( e : IOException){
        println("You are not catching this error!")
    }finally{
        if (buffRead!=null){
            try{
                buffRead.close()
            } catch ( e : IOException){
                println("You are not catching this error!")
            }            
        }
    }
}