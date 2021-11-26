import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Academy extends JFrame implements ActionListener{
    JFrame academyFrame;


    JMenuBar mainMenu = new  JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem exitMenuItem = new JMenuItem("Exit");

    JMenu teacherMenu = new JMenu("Teachers");
    JMenuItem addTeacherMenuItem = new JMenuItem("Add teacher");
    JMenuItem updateTeacherMenuItem = new JMenuItem("Update/Delete teacher");

    JMenu courseMenu = new JMenu("Courses");
    JMenuItem addCourseMenuItem = new JMenuItem("Add course");
    JMenuItem updateCourseMenuItem = new JMenuItem("Update/Delete course");

    JMenu studentMenu = new JMenu("Students");
    JMenuItem addStudentMenuItem = new JMenuItem("Add student");
    JMenuItem updateStudentMenuItem = new JMenuItem("Update/Delete student");

    JMenuItem  MenuItem = new JMenuItem("");

    public Academy( ){
        Login myLogin = new Login(this );

         academyFrame = new JFrame("Academy");
        academyFrame.setSize(1000,1000);
        academyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlowLayout myFlow = new FlowLayout();
        academyFrame.setLayout(myFlow);

        exitMenuItem.addActionListener(this);
        addTeacherMenuItem.addActionListener(this);
        updateTeacherMenuItem.addActionListener(this);
        addCourseMenuItem.addActionListener(this);
        updateCourseMenuItem.addActionListener(this);
        addStudentMenuItem.addActionListener(this);
        updateStudentMenuItem.addActionListener(this);


        fileMenu.add(exitMenuItem);
        fileMenu.setMnemonic('f');

        teacherMenu.add(addTeacherMenuItem);
        teacherMenu.add(updateTeacherMenuItem);

        courseMenu.add(addCourseMenuItem);
        courseMenu.add(updateCourseMenuItem);

        studentMenu.add(addStudentMenuItem);
        studentMenu.add(updateStudentMenuItem);

        mainMenu.add(fileMenu);
        mainMenu.add(teacherMenu);
        mainMenu.add(courseMenu);
        mainMenu.add(studentMenu);


        academyFrame.add(mainMenu);
         academyFrame.setVisible(true);
    } // end constructor

    public static void main(String[] args) {
//AddTeacher at = new AddTeacher();
//at.setVisible(true);;
        Academy myAcademy = new Academy();
    } // end main


    public void actionPerformed  (ActionEvent e)
    {
        String option = e.getActionCommand();

        switch(option)
        {
            case "Exit":
int exit = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
if (exit == JOptionPane.YES_OPTION)
    System.exit(0);
                break;
            case "Add teacher": AddTeacher addTeacher = new AddTeacher();
                break;

             case "Update/Delete teacher": UpdateTeacher updateTeacher = new UpdateTeacher();
                 break;

            //case "Add course": AddCourse addCourse = new AddCourse();
//                break;

            //case "Update/Delete course": UpdateCourse updateCourse = new UpdateCourse();
//                break;
        } // end switch


    } // end actionperformed

} // end of class