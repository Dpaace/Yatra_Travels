import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDashboard implements ActionListener {
    JFrame dash;
    JLabel lbl_heading;
    String user;

    JButton btnProfile, btnDelete, btnTicket, btnDriver, btnPayment;


    public UserDashboard(String user){
        this.user=user;
        // Creating Dashboard Frame
        dash = new JFrame("Dashboard");
        dash.setLocation(350,150);
        dash.setSize(515,435);

        Font fon1=new Font("cambria",Font.BOLD,20);

        //Background Image
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("UserDashboard.png")));
        bg.setBounds(0,0,500,400);
        dash.add(bg);

        lbl_heading = new JLabel("Welcome");
        lbl_heading.setFont(new Font("CAMBRIA",Font.BOLD,45));
        lbl_heading.setForeground(Color.WHITE);
        lbl_heading.setBounds(290,130,300,60);
        bg.add(lbl_heading);

        lbl_heading = new JLabel(user);
        lbl_heading.setFont(new Font("CAMBRIA",Font.BOLD,20));
        lbl_heading.setForeground(Color.WHITE);
        lbl_heading.setBounds(290,170,300,50);
        bg.add(lbl_heading);


        btnProfile = new JButton("UPDATE PROFILE");
        btnProfile.setBounds(61,33,210,42);
        btnProfile.setBackground(Color.GREEN);
        btnProfile.setForeground(Color.BLACK);
        btnProfile.setBorder(null);
        btnProfile.setFont(fon1);
        btnProfile.addActionListener(this);
        bg.add(btnProfile);

        btnDelete = new JButton("DELETE ACCOUNT");
        btnDelete.setBounds(61,106,210,42);
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setBorder(null);
        btnDelete.setFont(fon1);
        btnDelete.addActionListener(this);
        bg.add(btnDelete);


        btnTicket = new JButton("BOOK TICKETS");
        btnTicket.setBounds(61,179,210,42);
        btnTicket.setBackground(Color.BLUE);
        btnTicket.setForeground(Color.BLACK);
        btnTicket.setBorder(null);
        btnTicket.setFont(fon1);
        btnTicket.addActionListener(this);
        bg.add(btnTicket);

        btnDriver = new JButton("DRIVER SECTION");
        btnDriver.setBounds(61,256,210,42);
        btnDriver.setBackground(Color.BLACK);
        btnDriver.setForeground(Color.BLACK);
        btnDriver.setBorder(null);
        btnDriver.setFont(fon1);
        btnDriver.addActionListener(this);
        bg.add(btnDriver);

        btnPayment = new JButton("PAYMENT");
        btnPayment.setBounds(61,331,210,42);
        btnPayment.setBackground(Color.GRAY);
        btnPayment.setForeground(Color.BLACK);
        btnPayment.setBorder(null);
        btnPayment.setFont(fon1);
        btnPayment.addActionListener(this);
        bg.add(btnPayment);


        dash.setLayout(null);
        dash.setVisible(true);
        dash.setResizable(false);
    }

    public static void main(String[] args) {
        new UserDashboard("Dpaace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnProfile){
            dash.dispose();
            new ProfileUpdate(user);
        }
        if (e.getSource()==btnDelete){
            dash.dispose();
            new UserDelete(user);
        }
        if (e.getSource() == btnTicket) {
            dash.dispose();
            new BookTicket(user);
        }
        if (e.getSource()==btnDriver){
            dash.dispose();
            new ShowDriver(user);
        }
        if (e.getSource()==btnPayment){
            dash.dispose();
            new ViewBooked(user);
        }
    }
}
