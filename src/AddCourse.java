import com.sun.jdi.NativeMethodException;
import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddCourse extends JFrame  implements ActionListener {
JFrame addCourseFrame = new JFrame("Add course");
JPanel formPanel = new JPanel( new GridLayout(4,2,5,5));
JPanel buttonsPanel = new JPanel(new FlowLayout());

BorderLayout layout = new BorderLayout(2,2);

JLabel nameLabel = new JLabel("Name:");
JLabel priceLabel = new JLabel("Price:");
JLabel payLabel = new JLabel("Payment:");
JLabel teacherLabel = new JLabel("Teacher ID:");
    JLabel blank = new JLabel("   ");


JTextField nameTextField = new JTextField(25);
JTextField priceTextField = new JTextField(10);
JTextField payTextField = new JTextField(10);
JTextField teacherTextField = new JTextField(10);

JButton addButton = new JButton("Add");
JButton cancelButton = new JButton("Cancel");


ArrayList<Course> allCourses = new ArrayList<>()  ;
ArrayList<Teacher> allTeachers = new ArrayList<>();
int size = 0;

Teacher t = null;


public AddCourse (){
addCourseFrame.setSize(500,500);
addCourseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
addCourseFrame.setLayout(layout);

formPanel.add(nameLabel);
formPanel.add(nameTextField);
formPanel.add(priceLabel);
formPanel.add(priceTextField);
formPanel.add(payLabel);
formPanel.add(payTextField);
formPanel.add(teacherLabel);
formPanel.add(teacherTextField);


addButton.addActionListener(this);
cancelButton.addActionListener(this);

buttonsPanel.add(cancelButton);
buttonsPanel.add(blank);
buttonsPanel.add(addButton);

addCourseFrame.add(formPanel, BorderLayout.CENTER);
addCourseFrame.add(buttonsPanel, BorderLayout.SOUTH);

addCourseFrame.setVisible(true);;
open();
openTeachers();
} // end constructor



    public void actionPerformed(ActionEvent e){
String option = e.getActionCommand();
switch(option){
    case "Add":
         if (!searchTeacher()){
         int opt = JOptionPane.showConfirmDialog(null, "The teacher ID entered does not correspond with anyy teacher.\nDo you want to save this course with no teacher?");
         if (opt == JOptionPane.YES_OPTION)
             option = "";
         } // end if search teacher

        saveCourse();
        break;
    case "Cancel":
addCourseFrame.dispose();
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


    public void openTeachers() {
        try {
            //JB removed code here which reads a file containing staff data

            File file = new File("teachers.data"); //added by JB

            if(file.exists()) { //this if-else added by JB

                //the 3 lines below are DJ's
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allTeachers = (ArrayList<Teacher>) is.readObject();
                is.close();

                //JB removed code here which reads a file containing sales data

                 JOptionPane.showMessageDialog(null, file.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
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
    } // end open teachers


    private  boolean searchTeacher (){
        boolean pass = false;
        String id = teacherTextField.getText();
        int x= 0;

        while (x<allTeachers.size()){
            t = allTeachers.get(x);

            if (t.getId().equalsIgnoreCase(id)) {
                pass = true;
                x = allTeachers.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search teacher


    private void saveCourse()
    {

 String id = "Cou" + String.format("%02d", (size+1));
        String name = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
double pay = Double.parseDouble(payTextField.getText());

         Course c = new Course(id, name, price, pay, t);
allCourses.add(c);
JOptionPane.showMessageDialog(null, allCourses.get(0).toString(), "pass", JOptionPane.PLAIN_MESSAGE);
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
        JOptionPane.showMessageDialog(null, "Course added successfuly\n" + c, "Add success", JOptionPane.INFORMATION_MESSAGE);
    } // end save course



} // end class
