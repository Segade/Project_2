import java.util.Arrays;
import java.util.GregorianCalendar;

public class Teacher extends Person{
    private String department;
    private Course course[];

    public Teacher(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone,char gender, String department, Course[] course) {
        super(id, name, surname, address, town, county, dob, email, phone, gender);
setDepartment(department);
        setCourse(null);
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

    public Course[] getCourse() {
        return course;
    }

    public void setCourse(Course[] course) {
        this.course = course;
    }

    @Override
    public String toString() {
 String texto = super.toString() +"\nDepartment: " + getDepartment()+
 "\nCourses: " ;

 if (course != null)
 for (int x=0; x<course.length;x++)
     if (course[x] != null) {
         texto += course[x];
     }
         else
    texto += "No course enterd";

     return texto;
    } // end to string
} // end class
