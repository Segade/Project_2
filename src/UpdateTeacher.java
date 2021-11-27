import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.GregorianCalendar;


public class UpdateTeacher extends JFrame implements ActionListener {
    JFrame updateTeacherFrame = new JFrame("Update teacher");
    JPanel formPanel = new JPanel(new GridLayout(10,2, 5,5));
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
    JTextField phoneTextField = new JTextField(12);
    JTextField emailTextField = new JTextField(30);

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
JLabel phoneLabel = new JLabel("Phone:");
JLabel emailLabel = new JLabel("Email:");

JButton searchButton = new JButton("Search");
JButton updateButton = new JButton("Update");
JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();

    ArrayList<Teacher> allTeachers = new ArrayList<>();
    Teacher t = null;


     UpdateTeacher (){

         updateTeacherFrame.setSize(500,500);
         updateTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
updateTeacherFrame.setLayout(layout);

searchButton.addActionListener(this);
         searchPanel.add(searchLabel);
         searchPanel.add(searchTextField);
         searchPanel.add(searchButton);


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
         formPanel.add(phoneLabel);
         formPanel.add(phoneTextField);
         formPanel.add(emailLabel);
         formPanel.add(emailTextField);
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
updateButton.setVisible(false) ;
open();
     } // end constructor

    public  void actionPerformed (ActionEvent e){
        String option = e.getActionCommand();

        switch (option)
        {
            case "Search":
search();

break;
            case "Update":
                String myDay = (String) dayComboBox.getSelectedItem();
                String myMonth = (String) monthComboBox.getSelectedItem();
                String myYear = (String)yearComboBox.getSelectedItem();


                String texto = Validator.validateTeacherForm(nameTextField.getText(), surnameTextField.getText(), addressTextField.getText(),townTextField.getText(), countyTextField.getText(), myDay, myMonth, myYear, phoneTextField.getText(), emailTextField.getText(), departmentTextField.getText());

                char gender = 'X';
                if (! maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
                    texto += "\nEnter a gender";

                if (texto.equals("") )
                    saveTeacher();
                else
                    JOptionPane.showMessageDialog(null, texto , "Error", JOptionPane.ERROR_MESSAGE);
formPanel.setVisible(false);
updateButton.setVisible(false);
                break;
            case "Cancel":
                updateTeacherFrame.dispose();
        } // end switch
    }// end action performed

// ********
public void setComboBoxes(String day, String month, String year)
{

    dayComboBox.addItem("Day");;
    for (int x=1;x<32;x++) {
        dayComboBox.addItem(Integer.toString(x));
        if (day.equals(Integer.toString(x)))
dayComboBox.setSelectedIndex(x);
    } // end for day

    monthComboBox.addItem("Month");
    for (int x=1;x<13;x++) {
        monthComboBox.addItem(Integer.toString(x));
        if (month.equals(Integer.toString(x)))
            monthComboBox.setSelectedIndex(x);
    } // end for month

int y = 0;
    yearComboBox.addItem("Year");;
    for (int x=2021;x>1910;x--) {
        yearComboBox.addItem(Integer.toString(x));
        y++;
        if (year.equals(Integer.toString(x)))
yearComboBox.setSelectedIndex(y);
    } // end for year

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
    int x= 0;

while (x<allTeachers.size()){
    t = allTeachers.get(x);

    if (t.getId().equalsIgnoreCase(id)) {
        display(t);
x = allTeachers.size() +2;
    }
x++;
     } // end while
if (x == allTeachers.size()) {
    JOptionPane.showMessageDialog(null, "No match", "Error", JOptionPane.ERROR_MESSAGE);
    formPanel.setVisible(false);
    updateButton.setVisible(false);
}
    } // end search


    private void display(Teacher t){
    nameTextField.setText(t.getName());
 surnameTextField.setText(t.getSurname());
 addressTextField.setText(t.getAddress());
 townTextField.setText(t.getTown());
 countyTextField.setText(t.getCounty());
 int year =t.getDob().get(GregorianCalendar.YEAR);
        int month =t.getDob().get(GregorianCalendar.MONTH);
        int day =t.getDob().get(GregorianCalendar.DATE);
         setComboBoxes(Integer.toString(day),  Integer.toString(month), Integer.toString(year));

         char gender = t.getGender();
         if (gender == 'M')
             maleRadioButton.setSelected(true);
         else
             femaleRadioButton.setSelected(true);

         departmentTextField.setText(t.getDepartment());

         formPanel.setVisible(true);
         updateButton.setVisible(true);
    } // end display


    private void saveTeacher()
    {

        String myDay = (String) dayComboBox.getSelectedItem();
        String myMonth = (String) monthComboBox.getSelectedItem();
        String myYear = (String)yearComboBox.getSelectedItem();
        GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(myYear), Integer.parseInt(myMonth), Integer.parseInt(myDay));
        String department = departmentTextField.getText();
        char gender = 'X';

        if (maleRadioButton.isSelected())
            gender = 'M';
        else
            gender = 'F';

int y = 0;

for (int x=0; x<allTeachers.size() ;x++){

if (allTeachers.get(x).getId().equals(t.getId() )){
    allTeachers.get(x).setName(nameTextField.getText());
allTeachers.get(x).setSurname(surnameTextField.getText());
allTeachers.get(x).setAddress(addressTextField.getText());
allTeachers.get(x).setTown(townTextField.getText());
    allTeachers.get(x).setCounty(countyTextField.getText());
    allTeachers.get(x).setGender(gender);
    allTeachers.get(x).setDob(dob);
     allTeachers.get(x).setPhone(phoneTextField.getText());
     allTeachers.get(x).setEmail(emailTextField.getText());
y = x;
} // end if

        } // end for

        File outFile  = new File("teachers.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);



            objectOutStream.writeObject(allTeachers);

            outStream.close();

            JOptionPane.showMessageDialog(null, "Teacher updated successfuly\n" + allTeachers.get(y).toString(), "Update success", JOptionPane.INFORMATION_MESSAGE);
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

    } // end save teacher



} // end class
