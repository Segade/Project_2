import java.util.GregorianCalendar;

public class Student extends Person{

    public Student(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone, char gender) {
        super(id, name, surname, address, town, county, dob, email, phone, gender);
    }// end constructor

} // end class
