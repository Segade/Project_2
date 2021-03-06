import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class AssignStudent extends JFrame implements ActionListener {
    JFrame assignStudentFrame = new JFrame("Assign student");
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

    BorderLayout layout = new BorderLayout();

    JLabel courseLabel = new JLabel("Course ID:");
    JLabel studentLabel = new JLabel("Student ID:");

    JTextField courseTextField = new JTextField(10);
    JTextField studentTextField = new JTextField(10);

    JButton assignButton = new JButton("Assign");
    JButton unassignButton = new JButton("Unassign");

    ArrayList<Course> allCourses = new ArrayList<>();
    ArrayList<Student> allStudents = new ArrayList<>();

    Course c= null;
    Student s = null;
    int size = 0;
    int positionStudent;
    int positionCourse ;


    public AssignStudent() {
        assignStudentFrame.setSize(500, 500);
        assignStudentFrame.setLayout(layout);

        assignButton.addActionListener(this);
        unassignButton.addActionListener(this);

        formPanel.add(courseLabel);
        formPanel.add(courseTextField);
        formPanel.add(studentLabel);
        formPanel.add(studentTextField);
        formPanel.add(assignButton);
        formPanel.add(unassignButton);

        assignStudentFrame.add(formPanel);
assignStudentFrame.setVisible(true);
open();
openStudents();
    } // end constructor


    public void actionPerformed(ActionEvent e) {
String option = e.getActionCommand();

switch (option){
    case "Assign":

        if (searchCourse()){
if (searchStudent()){

    if (assignStudent() ) {

        saveCourse();
         JOptionPane.showMessageDialog(null, allCourses.get(positionCourse).toString(), "Assign success", JOptionPane.PLAIN_MESSAGE );
    } // end if assign
            } else // if search student
            JOptionPane.showMessageDialog(null, "Student not found", "Student not found", JOptionPane.PLAIN_MESSAGE);
    } else // if search course
    JOptionPane.showMessageDialog(null, "Course not found", "Course not found", JOptionPane.PLAIN_MESSAGE);
break;

    case "Unassign":
 if (searchCourse()) {

     if (searchStudent()) {

         if (unassignStudent()) {
             saveCourse();
             JOptionPane.showMessageDialog(null, allCourses.get(positionCourse).toString(), "Unassign success", JOptionPane.PLAIN_MESSAGE);
         }
     } else // if student
         JOptionPane.showMessageDialog(null, "Student not found", "Student not found", JOptionPane.PLAIN_MESSAGE);
 } else // if course
     JOptionPane.showMessageDialog(null, "Course not found", "Course not found", JOptionPane.PLAIN_MESSAGE);

} // end switch
    } // end action performed

    public void open() {
        try {


            File file = new File("courses.data"); //added by JB

            if(file.exists()) { //this if-else added by JB


                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allCourses = (ArrayList<Course>) is.readObject();
                size =allCourses.size();
                JOptionPane.showMessageDialog(null, "", ""+size, JOptionPane.PLAIN_MESSAGE);
                is.close();



 //                JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{ //This code added by JB
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        } //these individual catch clauses added by JB, replacing a single "Exception" catch clause
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialised not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    } // end open

    public void openStudents() {
        try {
            //JB removed code here which reads a file containing staff data

            File file = new File("students.data"); //added by JB

            if(file.exists()) { //this if-else added by JB

                //the 3 lines below are DJ's
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allStudents = (ArrayList<Student>) is.readObject();
                size =allStudents.size();
                JOptionPane.showMessageDialog(null, "", ""+size, JOptionPane.PLAIN_MESSAGE);
                is.close();

                //JB removed code here which reads a file containing sales data

//                JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{ //This code added by JB
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + file.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        } //these individual catch clauses added by JB, replacing a single "Exception" catch clause
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialised not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    } // end open student

    private  boolean searchCourse (){
        boolean pass = false;
        String idCourse = courseTextField.getText();
        int x= 0;

        while (x<allCourses.size()){
            c = allCourses.get(x);

            if (c.getId().equalsIgnoreCase(idCourse)) {
                pass = true;
                positionCourse = x;
                x = allCourses.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search course


    private  boolean searchStudent (){
        boolean pass = false;
        String idStudent = studentTextField.getText();
 int x=0;

        while (x<allStudents.size()){
            s = allStudents.get(x);

            if (s.getId().equalsIgnoreCase(idStudent)) {
                pass = true;
                positionStudent = x;
                x = allStudents.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search student


    public boolean assignStudent() {
        boolean pass = false;

        if (c.getStudent() == null) {
            Student student[] = new Student[30];
            student[0] = s;
            c.setStudent(student);
        } else {
            int x = 0;
            while (x < c.getStudent().length && !pass) {
                if (c.getStudent()[x] == null){
                    Student student[] = c.getStudent();
student[x] = s;

allCourses.get(positionCourse).setStudent(student);
x = c.getStudent().length;
pass = true;

                } else if (c.getStudent()[x].getId().equalsIgnoreCase(studentTextField.getText())   )
                {
                    x = c.getStudent().length + 2;
                    JOptionPane.showMessageDialog(null, "This student already is assigned to this course", "already assigned", JOptionPane.ERROR_MESSAGE);
                } // if c student id is = student text filed

x++;
            } // end while
if (x == c.getStudent().length)
    JOptionPane.showMessageDialog(null, "This course is full", "Full", JOptionPane.ERROR_MESSAGE);
        } // if student array is null

    return pass;
    } // end method assign student



    public boolean unassignStudent() {
        boolean pass = false;

        if (c.getStudent() == null) {
            JOptionPane.showMessageDialog(null, "This course is empty", "Course empty", JOptionPane.PLAIN_MESSAGE);

        } else {
            int x = 0;
            while (x < c.getStudent().length && !pass) {
if (c.getStudent()[x] != null )
                if (c.getStudent()[x].getId().equalsIgnoreCase(studentTextField.getText())   )
                {
                    Student student[] = c.getStudent();
                    student[x] = null;
                    allCourses.get(positionCourse).setStudent(student);
pass = true;
                    x = c.getStudent().length + 2;
                    JOptionPane.showMessageDialog(null, "This student already is assigned to this course", "already assigned", JOptionPane.ERROR_MESSAGE);
                } // if c student id is = student text fieled
                 x++;
                //JOptionPane.showMessageDialog(null, ""+x, ""+x, JOptionPane.PLAIN_MESSAGE);
            } // end while

              if (x == c.getStudent().length)
                JOptionPane.showMessageDialog(null, "This student is not in this course", "Student not assigned", JOptionPane.ERROR_MESSAGE);
        } // if student array is null

        return pass;
    } // end method unassign student


    private void saveCourse()
    {

        File outFile  = new File("courses.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeObject(allCourses);

            outStream.close();
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "File could not be found!",
                    "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be written!",
                    "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }

    } // end save course


}// end cclass