import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.event.FocusEvent;

public class RegistrationForm implements ActionListener {

    // Variable defined over here
    JFrame reg_frame;
    JTextField txtName, txtMobile, txtEmail,txtAddress;
    JRadioButton male, female;
    JComboBox day, month, year;
    JCheckBox terms;
    JButton register, login;
    JPasswordField txtPassword, txtConPassword;

    public RegistrationForm(){

        reg_frame = new JFrame("Registration Page");
        reg_frame.setSize(615,435);
        reg_frame.setLayout(null);


        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("register.png")));
        bg.setBounds(0, 0, 600, 400);
        reg_frame.add(bg);

        JLabel lbl = new JLabel("Already registered?");
        lbl.setBounds(350,15,120,15);
        bg.add(lbl);

        login = new JButton("Login Here!");
        login.setBounds(370,32,80,15);
        login.setBackground(Color.WHITE);
        login.setBorder(null);
        login.addActionListener(this);
        bg.add(login);


        txtName = new JTextField();
        txtName.setBounds(108,66,193,23);
        txtName.setBorder(null);
        txtName.setHorizontalAlignment(JPasswordField.CENTER);
        bg.add(txtName);


        txtMobile = new JTextField();
        txtMobile.setBounds(109,102,193,23);
        txtMobile.setBorder(null);
        txtMobile.setHorizontalAlignment(JPasswordField.CENTER);
        bg.add(txtMobile);


        txtEmail = new JTextField();
        txtEmail.setBounds(110,138,190,25);
        txtEmail.setBorder(null);
        txtEmail.setHorizontalAlignment(JPasswordField.CENTER);
        bg.add(txtEmail);

        male = new JRadioButton();
        female = new JRadioButton();

        male.setBounds(108,180,18,20);
        female.setBounds(178,180,18,20);
        male.setSelected(true);

        bg.add(male);
        bg.add(female);

        ButtonGroup gen = new ButtonGroup();
        gen.add(male);
        gen.add(female);


        String days[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31"};
        String months[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
        String years[] = {"2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006",
                "2005","2004","2003","2002","2001","2000","1999","1998"};

        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);

        day.setBounds(108,210,50,20);
        month.setBounds(168,210,50,20);
        year.setBounds(228,210,60,20);

        bg.add(day);
        bg.add(month);
        bg.add(year);

        txtAddress = new JTextField();
        txtAddress.setBounds(112,241,191,23);
        txtAddress.setBorder(null);
        txtAddress.setHorizontalAlignment(JPasswordField.CENTER);
        bg.add(txtAddress);


        txtPassword = new JPasswordField();
        txtPassword.setBounds(112,275,192,23);
        txtPassword.setBorder(null);
        txtPassword.setHorizontalAlignment(JPasswordField.CENTER);
        bg.add(txtPassword);


        txtConPassword = new JPasswordField();
        txtConPassword.setBounds(113,310,193,23);
        txtConPassword.setBorder(null);
        txtConPassword.setHorizontalAlignment(JPasswordField.CENTER);
        bg.add(txtConPassword);

        terms = new JCheckBox();
        terms.setBounds(10,363,20,20);
        bg.add(terms);

        Font font = new Font("Arial", Font.PLAIN, 11);

        register = new JButton("Register");
        register.setBounds(243,360,80,30);
        register.setBackground(Color.ORANGE);
        register.setForeground(Color.BLACK);
        register.setBorder(null);
        register.setFont(font);
        register.addActionListener(this);
        bg.add(register);

        reg_frame.setVisible(true);
    }

    private void registerReset(){
        txtName.setText("");
        txtMobile.setText("");
        txtEmail.setText("");
        male.setSelected(true);
        day.setSelectedIndex(0);
        month.setSelectedIndex(0);
        year.setSelectedIndex(0);
        txtAddress.setText("");
        txtPassword.setText("");
        txtConPassword.setText("");
        terms.setSelected(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = txtName.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String gender = "male";
        if (female.isSelected()) {
            gender = "female";
        }
        String dob = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
        String address = txtAddress.getText();
        String pass1 = txtPassword.getText();
        String pass2 = txtConPassword.getText();

        User ul = new User(name,mobile,email,gender,dob,address,pass1,pass2);

        if (e.getSource()==login){
            reg_frame.dispose();
            new LoginForm();
        }else if (ul.getUsername().length()==0 || ul.getMobileNumber().length()==0 || ul.getUserEmail().length()==0 ||
                ul.getUserGender().length()==0 || ul.getUserDob().length()==0 || ul.getUserAddress().length()==0 ||
                ul.getPassword1().length()==0 || ul.getPassword2().length()==0){

                JOptionPane.showMessageDialog(reg_frame,"Enter All Fields Completely");

        } else if(!ul.getPassword1().equals(ul.getPassword2())){
                JOptionPane.showMessageDialog(reg_frame,"The password does not match," +
                        "Please try again");
        }
        else {
            if (terms.isSelected()) {
                if (e.getSource() == register) {
                    DatabaseOperation db = new DatabaseOperation();
                    String query = "insert into register(name,mobile,email,gender,dob,address,password,con_password) values('" + ul.getUsername() + "','" + ul.getMobileNumber() + "','" + ul.getUserEmail() + "','" + ul.getUserGender() + "','" + ul.getUserDob() + "','" + ul.getUserAddress() + "','" + ul.getPassword1() + "','" + ul.getPassword2() + "')";
                    int ans = db.insert(query);
                    if (ans > 0) {
                        JOptionPane.showMessageDialog(reg_frame, "Data Registered Successfully");
                        registerReset();
                    }
                }
            }else {
                JOptionPane.showMessageDialog(reg_frame, "Accept Terms and Conditions");
            }
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }

}