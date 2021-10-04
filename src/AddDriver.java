
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDriver implements ActionListener {
    JFrame driver;
    JTextField driverName, driverEmail, driverAddress;
    JRadioButton dMale, dFemale;
    JComboBox day, month, year, driverExperience;
    JButton addDriver, btnBack;
    Font fon1;

    public AddDriver(){
        driver = new JFrame("Add Driver Details");
        driver.setSize(815,635);

        fon1=new Font("algerian",Font.BOLD,50);

        //Background Image
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("Driver.png")));
        bg.setBounds(0,0,800,600);
        driver.add(bg);

        // Employee full name and Textfield
        driverName = new JTextField();
        driverName.setBounds(342,125,272,40);
        driverName.setBackground(Color.WHITE);
        driverName.setForeground(Color.BLACK);
        driverName.setBorder(null);
        driverName.setHorizontalAlignment(JTextField.CENTER);
        bg.add(driverName);

        // Employee full name and Textfield
        driverEmail = new JTextField();
        driverEmail.setBounds(342,191,272,40);
        driverEmail.setBackground(Color.WHITE);
        driverEmail.setForeground(Color.BLACK);
        driverEmail.setBorder(null);
        driverEmail.setHorizontalAlignment(JTextField.CENTER);
        bg.add(driverEmail);

        // Employee DOB

        String days[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31"};
        String months[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
        String years[] = {"2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006",
                "2005","2004","2003","2002","2001","2000","1999","1998"};

        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);

        day.setBounds(342,264,50,25);
        month.setBounds(402,264,60,25);
        year.setBounds(472,264,60,25);

        bg.add(day);
        bg.add(month);
        bg.add(year);

        // Emp Gender
        // Gender Male or Female
        dMale = new JRadioButton("Male");
        dMale.setBounds(350,325,60,30);
        dMale.setBackground(Color.decode("#002147"));
        dMale.setForeground(Color.WHITE);
        dMale.setSelected(true);
        bg.add(dMale);

        dFemale = new JRadioButton("Female");
        dFemale.setBounds(430,325,70,30);
        dFemale.setBackground(Color.decode("#002147"));
        dFemale.setForeground(Color.WHITE);
        bg.add(dFemale);

        // Grouping Male and Female
        ButtonGroup gen = new ButtonGroup();
        gen.add(dMale);
        gen.add(dFemale);


        // Employee Address and Textfield
        driverAddress = new JTextField();
        driverAddress.setBounds(342,389,272,40);
        driverAddress.setBackground(Color.WHITE);
        driverAddress.setForeground(Color.BLACK);
        driverAddress.setBorder(null);
        driverAddress.setHorizontalAlignment(JTextField.CENTER);
        bg.add(driverAddress);

        // Employee Category and Textfield
        String exp[] = {"< 1 year","1 to 5 years","5 to 10 years","> 10 years"};
        driverExperience = new JComboBox(exp);
        driverExperience.setBounds(350,462,150,25);
        bg.add(driverExperience);

        addDriver = new JButton("Add");
        addDriver.setBounds(427,509,102,27);
        addDriver.setBackground(Color.WHITE);
        addDriver.setForeground(Color.BLACK);
        addDriver.setBorder(null);
        addDriver.addActionListener(this);
        bg.add(addDriver);

        btnBack = new JButton("Back");
        btnBack.setBounds(72,64,80,30);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);

        driver.setLayout(null);
        driver.setVisible(true);
    }
    private void driverReset(){
        driverName.setText("");
        driverEmail.setText("");
        driverAddress.setText("");
        day.setSelectedIndex(0);
        month.setSelectedIndex(0);
        year.setSelectedIndex(0);
        dMale.setSelected(true);
        driverExperience.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AddDriver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = driverName.getText();
        String email = driverEmail.getText();
        String dob = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
        String gender = "Male";
        if (dFemale.isSelected()){
            gender = "Female";
        }
        String address = driverAddress.getText();
        String experience = (String) driverExperience.getSelectedItem();

        Drivers dl   = new Drivers(name,email,dob,gender,address,experience);

        if (e.getSource()==btnBack){
            driver.dispose();
            new AdminDashboard();
        } else if (dl.getFullname().length()==0 || dl.getEmail().length()==0 || dl.getDob().length()==0 || dl.getGender().length()==0
                || dl.getAddress().length()==0 || dl.getExperience().length()==0){
            JOptionPane.showMessageDialog(driver,"Enter All Fields Completely");
        }
        else {
            if (e.getSource()==addDriver){
                DatabaseOperation db = new DatabaseOperation();
                String query = "insert into addDriver (fullname, email, dob, gender, address, jobrole) values('"+dl.getFullname()+"', '"+dl.getEmail()+"','"+dl.getDob()+"','"+dl.getGender()+"', '"+dl.getAddress()+"', '"+dl.getExperience()+"')";
                int ans=db.insert(query);
                if (ans>0){
                    JOptionPane.showMessageDialog(addDriver ,"Data Added Successfully");
                    driverReset();
                }
            }
        }

    }
}
