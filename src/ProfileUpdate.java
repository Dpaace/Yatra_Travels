import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileUpdate implements ActionListener {
    String user;
    JFrame profile;
    JLabel name, mob, email, gender, dob, address, pass, cpass;
    JLabel lbl_heading1, lbl_heading2, lbl_heading3, lbl_heading4;
    JTextField txtName,txtMobile, txtEmail,txtPassword, txtConPassword, taAddress;
    JRadioButton male, female;
    JComboBox day, month, year;
    JButton btn_update, btnBack;
//    JPasswordField txtPassword, txtConPassword;


    public ProfileUpdate(String user){
        this.user= user;

        profile = new JFrame("Profile Update Page");
        profile.setSize(815,635);
        profile.setLayout(null);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("ProfileUpdate.png")));
        bg.setBounds(0,0,800,600);
        profile.add(bg);

        lbl_heading1 = new JLabel("Welcome");
        lbl_heading1.setFont(new Font("CAMBRIA",Font.BOLD,45));
        lbl_heading1.setForeground(Color.WHITE);
        lbl_heading1.setBounds(90,130,300,60);
        bg.add(lbl_heading1);

        lbl_heading2 = new JLabel("To");
        lbl_heading2.setFont(new Font("CAMBRIA",Font.BOLD,45));
        lbl_heading2.setForeground(Color.WHITE);
        lbl_heading2.setBounds(160,200,300,60);
        bg.add(lbl_heading2);

        lbl_heading3 = new JLabel("Profile");
        lbl_heading3.setFont(new Font("CAMBRIA",Font.BOLD,45));
        lbl_heading3.setForeground(Color.WHITE);
        lbl_heading3.setBounds(120,270,300,60);
        bg.add(lbl_heading3);

        lbl_heading4 = new JLabel("Update Page");
        lbl_heading4.setFont(new Font("CAMBRIA",Font.BOLD,45));
        lbl_heading4.setForeground(Color.WHITE);
        lbl_heading4.setBounds(60,340,300,60);
        bg.add(lbl_heading4);


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
        male.setBounds(520,280,60,38);
        male.setBackground(Color.decode("#002147"));
        male.setForeground(Color.WHITE);
        male.setSelected(true);
        bg.add(male);

        female = new JRadioButton("Female");
        female.setBounds(620,280,70,38);
        female.setBackground(Color.decode("#002147"));
        female.setForeground(Color.WHITE);
        bg.add(female);


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

        day.setBounds(520,333,50,25);
        month.setBounds(580,333,60,25);
        year.setBounds(650,333,60,25);

        bg.add(day);
        bg.add(month);
        bg.add(year);

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
                txtName.setBounds(512,120,270,38);
                txtName.setBackground(Color.WHITE);
                txtName.setForeground(Color.BLACK);
                txtName.setBorder(null);
                txtName.setHorizontalAlignment(JTextField.CENTER);
                bg.add(txtName);

                txtMobile = new JTextField(rs.getString("mobile"));
                txtMobile.setBounds(512,175,270,38);
                txtMobile.setBackground(Color.WHITE);
                txtMobile.setForeground(Color.BLACK);
                txtMobile.setBorder(null);
                txtMobile.setHorizontalAlignment(JTextField.CENTER);
                bg.add(txtMobile);

                txtEmail = new JTextField(rs.getString("email"));
                txtEmail.setBounds(512,231,270,38);
                txtEmail.setBackground(Color.WHITE);
                txtEmail.setForeground(Color.BLACK);
                txtEmail.setBorder(null);
                txtEmail.setHorizontalAlignment(JTextField.CENTER);
                bg.add(txtEmail);

                String gender = rs.getString("gender");

                if (gender.length()==4){
                    male.setSelected(true);
                } else {
                    female.setSelected(true);
                }

                //DOB is left to be edited

                taAddress = new JTextField(rs.getString("address"));
                taAddress.setBounds(512,386,270,38);
                taAddress.setBackground(Color.WHITE);
                taAddress.setForeground(Color.BLACK);
                taAddress.setBorder(null);
                taAddress.setHorizontalAlignment(JTextField.CENTER);
                bg.add(taAddress);

                txtPassword = new JTextField(rs.getString("password"));
                txtPassword.setBounds(512,442,270,38);
                txtPassword.setBackground(Color.WHITE);
                txtPassword.setForeground(Color.BLACK);
                txtPassword.setBorder(null);
                txtPassword.setHorizontalAlignment(JTextField.CENTER);
                bg.add(txtPassword);


                txtConPassword = new JTextField(rs.getString("con_password"));
                txtConPassword.setBounds(512,498,270,38);
                txtConPassword.setBackground(Color.WHITE);
                txtConPassword.setForeground(Color.BLACK);
                txtConPassword.setBorder(null);
                txtConPassword.setHorizontalAlignment(JTextField.CENTER);
                bg.add(txtConPassword);

                btn_update = new JButton("Update Details");
                btn_update.setBounds(560,553,175,28);
                btn_update.setBackground(Color.WHITE);
                btn_update.setForeground(Color.BLACK);
                btn_update.setBorder(null);
                btn_update.addActionListener(this);
                bg.add(btn_update);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        btnBack = new JButton("Back");
        btnBack.setBounds(72,38,80,32);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);

        profile.setVisible(true);

    }

    public static void main(String[] args) {
        new ProfileUpdate("Dipesh Nepali");
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
