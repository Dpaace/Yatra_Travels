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
        lbl_heading = new JLabel("Welcome " + user);
        lbl_heading.setFont(new Font("arial",Font.BOLD,24));
        lbl_heading.setForeground(Color.RED);
        lbl_heading.setBounds(150,10,300,50);
        dash.add(lbl_heading);
        btnProfile = new JButton("UPDATE PROFILE");
        btnProfile.setBounds(30, 50 ,200 ,40);
        btnProfile.addActionListener(this);
        dash.add(btnProfile);
        btnDelete = new JButton("DELETE ACCOUNT");
        btnDelete.setBounds(30, 100 ,200 ,40);
        btnDelete.addActionListener(this);
        dash.add(btnDelete);

        btnTicket = new JButton("BOOK TICKETS");
        btnTicket.setBounds(30, 200, 200, 40);
        btnTicket.addActionListener(this);
        dash.add(btnTicket);
        btnDriver = new JButton("DRIVER SECTION");
        btnDriver.setBounds(30,250,200,40);
        btnDriver.addActionListener(this);
        dash.add(btnDriver);
        btnPayment = new JButton("PAYMENT");
        btnPayment.setBounds(30,300, 200, 40);
        btnPayment.addActionListener(this);
        dash.add(btnPayment);
        dash.setLocation(300,100);
        dash.setSize(700,500);
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
            new BookTicket(user);
        }
        if (e.getSource()==btnDriver){
//            new ShowDriver(user);
        }
        if (e.getSource()==btnPayment){
//            new ViewBooked(user);
        }
    }
}