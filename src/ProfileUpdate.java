import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileUpdate implements ActionListener {
    String user;
    JFrame profile;
    JLabel name, mob, email, gender, dob, address, pass, cpass;
    JTextField txtName,txtMobile, txtEmail,txtPassword, txtConPassword;
    JTextArea taAddress;
    JRadioButton male, female;
    JComboBox day, month, year;
    JButton btn_update, btnBack;
//    JPasswordField txtPassword, txtConPassword;


    public ProfileUpdate(String user){
        this.user= user;

        profile = new JFrame("Profile Page");



        name = new JLabel("Name");
        name.setBounds(20,50,100,20);
        profile.add(name);

        mob = new JLabel("Mobile");
        mob.setBounds(20,100,100,20);
        profile.add(mob);

        email = new JLabel("Email");
        email.setBounds(20,130,100,20);
        profile.add(email);

        gender = new JLabel("Gender");
        gender.setBounds(20,150,100,20);
        profile.add(gender);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(150,150,80,20);
        female.setBounds(240,150,80,20);


        profile.add(male);
        profile.add(female);

        ButtonGroup gen = new ButtonGroup();
        gen.add(male);
        gen.add(female);

        dob = new JLabel("DOB");
        dob.setBounds(20,200,100,20);
        profile.add(dob);

        String days[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31"};
        String months[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
        String years[] = {"2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006",
                "2005","2004","2003","2002","2001","2000","1999","1998"};

        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);

        day.setBounds(150,200,50,20);
        month.setBounds(210,200,50,20);
        year.setBounds(270,200,60,20);

        profile.add(day);
        profile.add(month);
        profile.add(year);

        address = new JLabel("Address");
        address.setBounds(20,250,100,20);
        profile.add(address);

        pass = new JLabel("Password");
        pass.setBounds(20,300,100,20);
        profile.add(pass);

        cpass = new JLabel("Confirm Password");
        cpass.setBounds(20,330,120,20);
        profile.add(cpass);

        DatabaseOperation db =new DatabaseOperation();
        String query="select * from register where name='"+user+"'";
        ResultSet rs=db.select(query);

        try {
            while (rs.next()){
                txtName = new JTextField(rs.getString("name"));
                txtName.setBounds(150,50,200,20);
                profile.add(txtName);

                txtMobile = new JTextField(rs.getString("mobile"));
                txtMobile.setBounds(150,100,200,20);
                profile.add(txtMobile);

                txtEmail = new JTextField(rs.getString("email"));
                txtEmail.setBounds(150,130,200,20);
                profile.add(txtEmail);

                String gender = rs.getString("gender");

                if (gender.length()==4){
                    male.setSelected(true);
                } else {
                    female.setSelected(true);
                }

                //DOB is left to be edited

                taAddress = new JTextArea(rs.getString("address"));
                taAddress.setBounds(150,240,200,30);
                profile.add(taAddress);

                txtPassword = new JTextField(rs.getString("password"));
                txtPassword.setBounds(150,300,200,20);
                profile.add(txtPassword);


                txtConPassword = new JTextField(rs.getString("con_password"));
                txtConPassword.setBounds(150,330,200,20);
                profile.add(txtConPassword);

                btn_update = new JButton("Update Details");
                btn_update.setBounds(100,360,200,20);
                btn_update.addActionListener(this);
                profile.add(btn_update);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(100, 390,200,25);
        btnBack.addActionListener(this);
        profile.add(btnBack);

        profile.setLocation(400,100);
        profile.setSize(500,500);
        profile.setLayout(null);
        profile.setVisible(true);


    }

    public static void main(String[] args) {
        new ProfileUpdate("bbb");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack){
            profile.dispose();
            new UserDashboard(user);
        }
        else {
            if (e.getSource()==btn_update){
                String gender="";
                if (male.isSelected()){
                    gender = male.getText();
                }
                if (female.isSelected()){
                    gender = female.getText();
                }
                if (txtName.getText().length()==0 || txtMobile.getText().length()==0 || txtEmail.getText().length()==0 ||
                        taAddress.getText().length()==0 ||txtPassword.getText().length()==0 || txtConPassword.getText().length()==0){
                    JOptionPane.showMessageDialog(btn_update,"Enter all the fields");
                }else if (!txtPassword.getText().equals(txtConPassword.getText())){
                    JOptionPane.showMessageDialog(btn_update,"The password does not match");
                }else {
                    String query = "update register set name='"+txtName.getText()+"', mobile='"+txtMobile.getText()+"'," +
                            "email='"+txtEmail.getText()+"', gender='"+gender+"', address='"+taAddress.getText()+"', " +
                            "password='"+txtPassword.getText()+"', con_password='"+txtConPassword.getText()+"' where name ='"+user+"'";

                    DatabaseOperation db = new DatabaseOperation();
                    int rs=db.Update(query);
                    JOptionPane.showMessageDialog(btn_update, "Update successsful " +
                            "Please Login Through the Login Page again with your updated Details");
                    profile.dispose();
                    new LoginForm();
                }
            }
        }

    }
}
