import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AddTeacher extends JFrame implements ActionListener{
    JFrame addTeacherFrame = new JFrame("Add teacher");
    JPanel formPanel = new JPanel(new GridLayout(8,2, 5,5));
    JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));
    JPanel dobPanel = new JPanel(new FlowLayout());
    JPanel genderPanel = new JPanel(new FlowLayout());

    BorderLayout layout = new BorderLayout(2, 2);

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

    JTextField nameTextField = new JTextField(20);
    JTextField surnameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(40);
    JTextField townTextField = new JTextField(20);
    JTextField countyTextField = new JTextField(20);
    JTextField departmentTextField = new JTextField(25);

    JComboBox dayComboBox = new JComboBox();
    JComboBox monthComboBox = new JComboBox();
    JComboBox yearComboBox = new JComboBox();

    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();



    ArrayList<Teacher> allTeachers = new ArrayList<>();
    int size = 0;
    AddTeacher ()
    {

        addTeacherFrame.setSize(500,500);
        addTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(layout);

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



        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        buttonsPanel.add(addButton, BorderLayout.EAST);
        buttonsPanel.add(cancelButton, BorderLayout.WEST);

        addTeacherFrame.add(formPanel, BorderLayout.CENTER);
        addTeacherFrame.add(buttonsPanel, BorderLayout.SOUTH);
        formPanel.setVisible(true);
        addTeacherFrame.setVisible(true);
//pack();
        open();
    } // end constructor



    public void actionPerformed(ActionEvent e)
    {
        String option = e.getActionCommand();

        switch(option)
        {
            case "Add":
                String texto =validateForm();
                if (texto.equals("") )
                    saveTeacher();
                else
                    JOptionPane.showMessageDialog(null, texto , "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Cancel":
  addTeacherFrame.dispose();
                break;

        } // end switch

    } //end actionperformed

    private void saveTeacher()
    {
        String id = "TEA" + String.format("%05d", (size+1));
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String address = addressTextField.getText();
        String town = townTextField.getText();
        String county = countyTextField.getText();
        String email = "ivan@segade.carou";
        String phone = "00456987123";
        GregorianCalendar dob = new GregorianCalendar(1981,10,21);
        String department = departmentTextField.getText();
        char gender = 'X';

if (maleRadioButton.isSelected())
    gender = 'M';
else
    gender = 'F';

        Teacher t = new Teacher(id, name, surname, address, town, county, dob, email, phone, gender, department, null);
allTeachers.add(t);
        File outFile  = new File("teachers.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeObject(allTeachers);

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
JOptionPane.showMessageDialog(null, "Teacher added successfuly\n" + t, "Add success", JOptionPane.INFORMATION_MESSAGE);
    } // end save teacher

    private  String validateForm(){
        String error = "";
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String address = addressTextField.getText();
        String town = townTextField.getText();
        String county = countyTextField.getText();

        if (name.equals("") ||  isLetter(name))
            error ="Enter a valid name";

        if (surname.equals("") || isLetter(surname))
            error +="Enter a valid surname";

        if (address.equals(""))
            error +="\nEnter a address";

        if (town.equals("") || isLetter(town))
            error +="\nEnter a valid town";

        if (county.equals("") || isLetter(county))
            error +="\nEnter a county";

        String myDay = (String) dayComboBox.getSelectedItem();
        String myMonth = (String) monthComboBox.getSelectedItem();
        String myYear = (String) yearComboBox.getSelectedItem();

        if (!checkDate(myDay, myMonth, myYear))
            error += "\nEnter a valid Date of Birth";
        return error;
    } // end validate form

    private boolean isLetter(String enter){
        int x = 0;

        while (x<enter.length()){
            if (Character.isDigit(enter.charAt(x)))
                x = enter.length() +1;
            else
                x++;
        } // end while

        if (x>enter.length())
            return true;

        return false;
    } // end is letter


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

    private boolean checkDate(String myDay, String myMonth, String myYear) {
        boolean correct = true;

        if (!myDay.equals("Day") && !myMonth.equals("Month") && !myYear.equals("Year"))
        {
            int month = Integer.parseInt(myMonth);
            int day =Integer.parseInt(myDay);
            int year = Integer.parseInt(myYear);

            switch (month) {
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day == 31)
                        correct = false;

                    break;

                case 2:

                    if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                        if (day > 29)
                            correct = false;
                    }else
                    if (day >28)
                        correct = false;

            }// end switch
        } else
            correct = false;

        return correct;
    } //end check date



    // *********
    public void open() {
        try {
            //JB removed code here which reads a file containing staff data

            File file = new File("teachers.data"); //added by JB

            if(file.exists()) { //this if-else added by JB

                //the 3 lines below are DJ's
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allTeachers = (ArrayList<Teacher>) is.readObject();
                size =allTeachers.size();
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





} // end class
