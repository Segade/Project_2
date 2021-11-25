import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Login extends JDialog implements ActionListener{

    JPanel formPanel = new JPanel(new GridLayout(3,2));


JLabel idLabel = new JLabel("ID:");
JLabel passwordLabel = new JLabel("Password:");
JLabel blanckLabel = new JLabel(" ");

    JTextField idTextField = new JTextField(15);
JPasswordField passwordField = new JPasswordField(20);

JButton loginButton = new JButton("Login");
Boolean succes = false;

int attempts = 0;

Login(JFrame loginFrame )
{
super(loginFrame, true);

    setResizable(true);
  loginFrame.setLayout(new FlowLayout());

    loginButton.addActionListener(this);

    loginFrame.add(idLabel);
    loginFrame.add(idTextField);
    formPanel.add(passwordLabel);
    formPanel.add(passwordField);
    formPanel.add(blanckLabel);
    formPanel.add(loginButton);


    loginFrame.add(formPanel);
    loginFrame.setSize(500,250);


    setVisible(true);
} // end constructor


    public void actionPerformed (ActionEvent e){

    } // end cation performed





} // end class

