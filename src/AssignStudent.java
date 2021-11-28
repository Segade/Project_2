import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    ArrayList<Course> allCourse = new ArrayList<>();
    ArrayList<Student> allStudent = new ArrayList<>();

    Course c = null;
    Student s = null;

    public AssignStudent() {
        assignStudentFrame.setSize(500, 500);
        assignStudentFrame.setLayout(layout);


        formPanel.add(courseLabel);
        formPanel.add(courseTextField);
        formPanel.add(studentLabel);
        formPanel.add(studentTextField);
        formPanel.add(assignButton);
        formPanel.add(unassignButton);

        assignStudentFrame.add(formPanel);
assignStudentFrame.setVisible(true);
    } // end constructor


    public void actionPerformed(ActionEvent e) {


    } // end action performed

}// end cclass