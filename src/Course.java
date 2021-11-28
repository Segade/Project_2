import java.io.Serializable;

public class Course implements Serializable {
private String id;
private String name;
private double price;
private double pay;
    private Teacher teacher;
    private Student student[];

    public Course(String id, String name, double price, double pay, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pay = pay;
        this.teacher = teacher;
    } // end constructor

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!id.equals(""))
        this.id = id;
        else
            this.id = "No specified";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
if (!name.equals(""))
        this.name = name;
else
    this.name = "No specified";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price<0)
            this.price = 0;
        else
        this.price = price;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
if (pay<0)
    this.pay = 0;
else
        this.pay = pay;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudent() {
        return student ;
    }

    public void setStudent(Student student[]) {
        this.student = student;
    }

    @Override
    public String toString() {
 String texto = "Course Details \nID: " + getId() + ", Name: " + getName() +
         "\nPrice: " + getPrice() + ", Payment: " + getPay()+
          "\nTeacher: " + getTeacher();


 if (student != null){
     for (int x=0; x< student.length;x++)
         if (student[x] != null)
 texto += "student name";
 } else
     texto += "\nThere is no student";

 return texto;
    } // end to string



} // end class
