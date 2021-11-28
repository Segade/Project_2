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


    public AssignStudent() {
        assignStudentFrame.setSize(500, 500);
        assignStudentFrame.setLayout(layout);

        assignButton.addActionListener(this);

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
assignStudent();
JOptionPane.showMessageDialog(null, c.toString(), "Good", JOptionPane.PLAIN_MESSAGE);
            } else // if search student
            JOptionPane.showMessageDialog(null, "Student not found", "Student not found", JOptionPane.PLAIN_MESSAGE);
    } else // if search course
    JOptionPane.showMessageDialog(null, "Course not found", "Course not found", JOptionPane.PLAIN_MESSAGE);
break;

} // end switch
    } // end action performed

    public void open() {
        try {
            //JB removed code here which reads a file containing staff data

            File file = new File("courses.data"); //added by JB

            if(file.exists()) { //this if-else added by JB

                //the 3 lines below are DJ's
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allCourses = (ArrayList<Course>) is.readObject();
                size =allCourses.size();
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
                x = allCourses.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search course


    private  boolean searchStudent (){
        boolean pass = false;
        String idStudent = studentTextField.getText();
        int x= 0;

        while (x<allCourses.size()){
            s = allStudents.get(x);

            if (s.getId().equalsIgnoreCase(idStudent)) {
                pass = true;
                x = allStudents.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search student


    public void assignStudent() {
        if (c.getStudent() == null) {
            Student student[] = new Student[30];
            student[0] = s;
            c.setStudent(student);
        } else {
            int x = 0;
            while (x < c.getStudent().length) {
                if (c.getStudent()[x] == null){
                    Student student[] = c.getStudent();
student[x] = s;
c.setStudent(student);
x = c.getStudent().length;
                }else // if there is a blank space
                JOptionPane.showMessageDialog(null, "This course is full.\nYou cannot assign more students", "Course full", JOptionPane.PLAIN_MESSAGE);

            } // end while
        } // if student array is null
    } // end method assign student


}// end cclass