
//Standard classes
import java.io.File; 
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

// Some personalized classes
class FixedLengthString{
    private String value;

    public FixedLengthString(String text,int n){
        value=new String();

        if (text.length()>n){
            value=text.substring(0,n);
        }
        else{
            value=String.format("%-"+n+"s",text);
        }
    }

    public String GetString(){
        return value;
    }
}

class SSN{
    private static final int MAX_A=3;
    private static final int MAX_B=2;
    private static final int MAX_C=4;

    private FixedLengthString A;
    private FixedLengthString B;
    private FixedLengthString C;

    public void SetA(String value){
        A=new FixedLengthString(value, MAX_A);
    }

    public String GetA(){
        return A.GetString();
    }

    public void SetB(String value){
        B=new FixedLengthString(value, MAX_B);
    }

    public String GetB(){
        return B.GetString();
    }

    public void SetC(String value){
        C=new FixedLengthString(value,MAX_C);
    }

    public String GetC(){
        return C.GetString();
    }

    public int length(){
        return MAX_A+MAX_B+MAX_C;
    }

    public void SetAll(String value){
        FixedLengthString tmp1=new FixedLengthString(value,length());//Convert to fixed lentgh...
        String tmp=tmp1.GetString(); // ...and back to string

        SetA(tmp.substring(0,MAX_A));
        SetB(tmp.substring(MAX_A,MAX_A+MAX_B));
        SetC(tmp.substring(MAX_A+MAX_B,MAX_A+MAX_B+MAX_C));
    }

    public String GetAll(){
        return String.format("%s-%s-%s",GetA(),GetB(),GetC());
    }

    public String GetNumbers(){
        return String.format("%s%s%s",GetA(),GetB(),GetC());
    }
}

class Employee{
    private static final int MAX_L=10;

    private FixedLengthString fname;
    private FixedLengthString lname;
    public SSN ssn=new SSN();
    private FixedLengthString city;

    public void SetFname(String value){
        fname=new FixedLengthString(value, MAX_L);
    }

    public String GetFname(){
        return fname.GetString();
    }

    public void SetLname(String value){
        lname=new FixedLengthString(value, MAX_L);
    }

    public String GetLname(){
        return lname.GetString();
    }

    public void SetSSN(String value){
        ssn.SetAll(value);
    }

    public String GetSSN(){
        return ssn.GetAll();
    }

    public void SetCity(String value){
        city=new FixedLengthString(value, MAX_L);
    }

    public String GetCity(){
        return city.GetString();
    }

    public void SetAll(String value){
        FixedLengthString tmp1=new FixedLengthString(value, MAX_L+MAX_L+ssn.length()+MAX_L);//Convert to fixed lentgh...
        String tmp=tmp1.GetString(); // ...and back to string
        
        fname=new FixedLengthString(tmp.substring(0,MAX_L), MAX_L);
        lname=new FixedLengthString(tmp.substring(MAX_L,2*MAX_L), MAX_L);
        ssn.SetAll(tmp.substring(2*MAX_L,2*MAX_L+ssn.length()));
        city=new FixedLengthString(tmp.substring(2*MAX_L+ssn.length(),3*MAX_L+ssn.length()), MAX_L);
    }

    public void SetSSNA(String value){
        ssn.SetA(value);
    }

    public void SetSSNB(String value){
        ssn.SetB(value);
    }

    public void SetSSNC(String value){
        ssn.SetC(value);
    }

    public String GetFullString(){
        String tmp=String.format("%s%s%s%s",GetFname(),GetLname(),ssn.GetNumbers(),GetCity());
        return tmp;
    }

    public int Length(){  // Added a new method
        return MAX_L+MAX_L+ssn.length()+MAX_L;
    }

}

public class ReadData
{
    public static void main(String args[]){

        BufferedReader buffRead = null;
        int charsRead = -1;
        Employee one = new Employee();
        char[] tmpString = new char[one.Length()];

        try{
            File FileUsed = new File("data.java.dat"); // "open" file. In java you do not really open a file
            FileReader reader=new FileReader(FileUsed); 
            buffRead=new BufferedReader(reader);

            charsRead = buffRead.read(tmpString,0,one.Length());
            if (charsRead == -1){
                System.out.println("ERROR : File Empty"); 
            }else{
                System.out.println("No errors. Reading...");
                while(charsRead>0){                
                    one.SetAll( new String(tmpString) ); // note conversion char[] ==> string
                    System.out.format("%s, %s SSN: %s\n",one.GetFname(),one.GetLname(),one.GetSSN());
                    charsRead = buffRead.read(tmpString,0,one.Length());
                }
                Arrays.fill(tmpString,'*');  // Note the type of quote: '' for a char, "" for a string
                one.SetAll( new String(tmpString) ); // note conversion char[] ==> string
                System.out.format("%s, %s SSN: %s\n",one.GetFname(),one.GetLname(),one.GetSSN());
                System.out.println("End of File Reached"); 
                //buffRead.close(); No need for it here. the finally{} block later on takes care of it
            }
        } catch (FileNotFoundException e){
            System.out.println("ERROR : File NOT found"); 
                       
        } catch (IOException  e){
            e.printStackTrace();
            System.out.println("You are not catching this error!"); 
        } finally {
            if (buffRead !=  null){
                try{
                    buffRead.close(); // This closes FileReader as well
                } catch (IOException e){
                    System.out.println("You are not catching this error!"); 
                }
            }
        }
        
        // NOTE: in  Java files are *not* closed
    }
}