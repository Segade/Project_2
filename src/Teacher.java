import java.util.Arrays;
import java.util.GregorianCalendar;

public class Teacher extends Person{
    private String department;


    public Teacher(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone,char gender, String department ) {
        super(id, name, surname, address, town, county, dob, email, phone, gender);
setDepartment(department);

    } // end constructor


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
if (!department.equals(""))
        this.department = department;
else
    this.department = "No specified";
    }




    @Override
    public String toString() {
 String texto = super.toString() +"\nDepartment: " + getDepartment();


      return texto;
    } // end to string
} // end class
