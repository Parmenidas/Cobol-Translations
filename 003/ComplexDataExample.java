

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
        String tmp=String.format("%s%s%s",GetFname(),GetLname(),ssn.GetNumbers(),GetCity());
        return tmp;
    }

}

public class ComplexDataExample
{
    public static void main(String args[]){

        Employee one=new Employee();

        // Set the values of the fields.
        // This does not work
        
        one.SetAll("Michael Smith     123456789 Portland");
        System.out.println(one.GetFname());

        //This works
        one.SetAll("Michael   Smith     123456789Portland");
        System.out.format("%s, %s SSN: %s\n",one.GetFname(),one.GetLname(),one.GetSSN());

        //This works, too
        one.SetFname("Michael");
        one.SetLname("Smith");
        one.SetSSN("123456789"); //Inserted as string not number!
        one.SetCity("Portland");
        System.out.format("%s, %s SSN: %s\n",one.GetFname(),one.GetLname(),one.GetSSN());

        //Another way
        one.SetFname("Michael");
        one.SetLname("Smith");
        one.SetSSNA("123"); //Inserted as string not number!
        one.SetSSNB("45"); //Inserted as string not number!
        one.SetSSNC("6789"); //Inserted as string not number!
        one.SetCity("Portland");
        System.out.format("%s, %s SSN: %s\n",one.GetFname(),one.GetLname(),one.GetSSN());

        //As a "vector"
        one.SetAll("Michael   Smith     123456789Portland");
        String tmp=one.GetFullString();
        System.out.println(tmp.substring(0,10)+", "+tmp.substring(10,20)+" SSN: "+tmp.substring(20,23)+"-"+tmp.substring(23,25)+"-"+tmp.substring(25,29));

        //NOTE: if you want to remove the trailing spaces:
        System.out.format("%s, %s SSN: %s\n",one.GetFname().trim(),one.GetLname(),one.GetSSN());
        return;
    }

}