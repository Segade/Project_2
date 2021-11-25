import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;


public class UpdateTeacher extends JFrame implements ActionListener {
    JFrame updateTeacherFrame = new JFrame("Update teacher");
    JPanel formPanel = new JPanel(new GridLayout(3,2, 5,5));
    JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));
    JPanel dobPanel = new JPanel(new FlowLayout());
    JPanel genderPanel = new JPanel(new FlowLayout());
    JPanel searchPanel = new JPanel(new GridLayout(1,3,5,5));


    BorderLayout layout = new BorderLayout(2, 2);



JTextField searchTextField = new JTextField(10);
    JTextField nameTextField = new JTextField(20);
    JTextField surnameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(40);
    JTextField townTextField = new JTextField(20);
    JTextField countyTextField = new JTextField(20);
    JTextField departmentTextField = new JTextField(25);

    JComboBox dayComboBox = new JComboBox();
    JComboBox monthComboBox = new JComboBox();
    JComboBox yearComboBox = new JComboBox();

    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel addressLabel = new JLabel("Address:");
    JLabel townLabel = new JLabel("Town:");
    JLabel countyLabel = new JLabel("County:");
    JLabel dobLabel = new JLabel("Date of Birth:");
    JLabel slashLabel1  = new JLabel(" / ");
    JLabel slashLabel2 = new JLabel(" / ");
    JLabel genderLabel = new JLabel("Gender:");
    JLabel departmentLabel = new JLabel("Department:");
    JLabel blank = new JLabel("   ");
JLabel searchLabel = new JLabel("ID:");

JButton searchButton = new JButton("Search");
JButton updateButton = new JButton("Update");
JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();

    ArrayList<Teacher> allTeachers = new ArrayList<>();

     UpdateTeacher (){

         updateTeacherFrame.setSize(500,500);
         updateTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
updateTeacherFrame.setLayout(layout);

searchButton.addActionListener(this);
         searchPanel.add(searchLabel);
         searchPanel.add(searchTextField);
         searchPanel.add(searchButton);

generateComboBoxes();

         dobPanel.add(dayComboBox);
         dobPanel.add(slashLabel1);
         dobPanel.add(monthComboBox);
         dobPanel.add(slashLabel2);
         dobPanel.add(yearComboBox);

         genderGroup.add(maleRadioButton);
         genderGroup.add(femaleRadioButton);
         genderPanel.add(maleRadioButton);
         genderPanel.add(femaleRadioButton);

         formPanel.add(nameLabel);
         formPanel.add(nameTextField);
         formPanel.add(surnameLabel);
         formPanel.add(surnameTextField);
         formPanel.add(addressLabel);
         formPanel.add(addressTextField);
         formPanel.add(townLabel);
         formPanel.add(townTextField);
         formPanel.add(countyLabel);
         formPanel.add(countyTextField);
         formPanel.add(dobLabel);
         formPanel.add(dobPanel);
         formPanel.add(genderLabel);
         formPanel.add(genderPanel);
         formPanel.add(departmentLabel);
         formPanel.add(departmentTextField);

updateButton.addActionListener(this);
cancelButton.addActionListener(this);

         buttonsPanel.add(updateButton, BorderLayout.EAST);
         buttonsPanel.add(cancelButton, BorderLayout.WEST);


         updateTeacherFrame.add(searchPanel, BorderLayout.NORTH);
         updateTeacherFrame.add(formPanel, BorderLayout.CENTER);
updateTeacherFrame.add(buttonsPanel, BorderLayout.SOUTH );
formPanel.setVisible(false);
updateTeacherFrame.setVisible(true);

open();
     } // end constructor

    public  void actionPerformed (ActionEvent e){
        String option = e.getActionCommand();

        switch (option)
        {
            case "Search":
search();
                formPanel.setVisible(true);
break;

            case "Cancel":
                updateTeacherFrame.dispose();
        } // end switch
    }// end action performed

// ********
public void generateComboBoxes()
{

    dayComboBox.addItem("Day");;
    for (int x=1;x<32;x++)
        dayComboBox.addItem(Integer.toString(x));

    monthComboBox.addItem("Month");
    for (int x=1;x<13;x++)
        monthComboBox.addItem(Integer.toString(x));

    yearComboBox.addItem("Year");;
    for (int x=2021;x>1910;x--)
        yearComboBox.addItem(Integer.toString(x));

} // end generate combo boxes


    public void open() {
        try {
            //JB removed code here which reads a file containing staff data

            File file = new File("teachers.data"); //added by JB

            if(file.exists()) { //this if-else added by JB

                //the 3 lines below are DJ's
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allTeachers = (ArrayList<Teacher>) is.readObject();
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


    private void search (){
    String id = searchTextField.getText();
    int y= 0;
Teacher t = null;
for (int x=0; x<allTeachers.size(); x++){
    t = allTeachers.get(x);

    if (t.getId().equalsIgnoreCase(id))
        display(t);

} // end for


    } // end search


    private void display(Teacher t){
    nameTextField.setText(t.getName());
 surnameTextField.setText(t.getSurname());
 addressTextField.setText(t.getAddress());
 townTextField.setText(t.getTown());
 countyTextField.setText(t.getCounty());



    } // end display
} // end class
