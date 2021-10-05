import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard implements ActionListener {
    JFrame admin_dashboard;
    JButton addDriver, viewDriver, addroute, viewRoute;

    public AdminDashboard(){

        admin_dashboard = new JFrame("Admin Dashboard");
        admin_dashboard.setLocation(360,150);
        admin_dashboard.setSize(515,435);



        //Background Image
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("AdminDashboard.png")));
        bg.setBounds(0,0,500,400);
        admin_dashboard.add(bg);

        Font fon1=new Font("cambria",Font.BOLD,20);

        //JOptionPane.showMessageDialog(admin_dashboard,"Dashboard will load shortly");
        addDriver = new JButton("Add Driver");
        addDriver.setBounds(81,83,210,42);
        addDriver.setBackground(Color.RED);
        addDriver.setForeground(Color.WHITE);
        addDriver.setBorder(null);
        addDriver.setFont(fon1);
        addDriver.addActionListener(this);
        bg.add(addDriver);


        viewDriver = new JButton("Show Drivers");
        viewDriver.setBounds(81,156,210,42);
        viewDriver.setBackground(Color.GREEN);
        viewDriver.setForeground(Color.BLACK);
        viewDriver.setBorder(null);
        viewDriver.setFont(fon1);
        viewDriver.addActionListener(this);
        bg.add(viewDriver);

        addroute = new JButton("Add Route");
        addroute.setBounds(81,229,210,42);
        addroute.setBackground(Color.YELLOW);
        addroute.setForeground(Color.BLACK);
        addroute.setBorder(null);
        addroute.setFont(fon1);
        addroute.addActionListener(this);
        bg.add(addroute);

        viewRoute = new JButton("Show Added Routes");
        viewRoute.setBounds(81,305,210,42);
        viewRoute.setBackground(Color.BLUE);
        viewRoute.setForeground(Color.WHITE);
        viewRoute.setBorder(null);
        viewRoute.setFont(fon1);
        viewRoute.addActionListener(this);
        bg.add(viewRoute);

        admin_dashboard.setLayout(null);
        admin_dashboard.setVisible(true);
        admin_dashboard.setResizable(false);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addDriver){
            admin_dashboard.dispose();
            new AddDriver();
        }
        if (e.getSource()==viewDriver){
            admin_dashboard.dispose();
            new ViewDriver();
        }
        if (e.getSource()==addroute){
            admin_dashboard.dispose();
            new AddRoute();
        }
        if (e.getSource()==viewRoute){
            admin_dashboard.dispose();
            new ShowRoute();
        }
    }
}
