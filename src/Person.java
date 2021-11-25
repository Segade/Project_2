import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class Person implements Serializable {
    private String id;

    private String name;
    private String surname;
    private String address;
    private String town;
    private String county;
    private GregorianCalendar dob;
    private String email;
    private String phone;
    private char  gender;

    private boolean active;

    public Person() {
     setId("");
     setName("");
     setSurname("");
     setAddress("");
     setTown("");
     setCounty("");
     setPhone("");
     setEmail("");
     setDob(null);
     setActive(false);
    }

    public Person(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone, char gender) {
        setId(id);
        setName(name);
        setSurname(surname);
        setAddress(address);
        setTown(town);
        setCounty(county);
        setDob(dob);
        setEmail(email);
        setPhone(phone);
        setActive(true);
        setGender(gender);
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
            this.name= "no specified";
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (!surname.equals(""))
            this.surname = surname;
        else
            this.surname = "No specified";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!address.equals(""))
            this.address = address;
        else
            this.address = "No specified";
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        if (!town.equals(""))
            this.town = town;
        else
            this.town = "No specified";
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        if (!county.equals(""))
            this.county = county;
        else
            this.county = "No specified";
    }

    public GregorianCalendar getDob() {
        return dob;
    }

    public void setDob(GregorianCalendar dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.equals(""))
            this.email = email;
        else
            this.email = "No specified";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phone.equals(""))
            this.phone = phone;
        else
            this.phone = "No specified";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {


        this.gender = gender;
    }

    @Override
    public String toString() {
        String texto = "Personal details\nID: " + getId() +
                ", Name: " + getName() + ", Surname: " + getSurname() +
", Gender: ";
        if (getGender() == 'M')
            texto += "Male";
        else
            texto += "Female";

        Date dobDate = getDob().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        texto +=  ", Date of Birth: " + formatDate.format(dobDate);


        texto += "\nContact details \nAddress: " + getAddress()+
                "\nEmail: " + getEmail() + ", Phone: " + getPhone();

        return texto;
    } // to string
} //      end class
