
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoute implements ActionListener {
    JFrame route;
    JLabel routeName, routePrice;
    JTextField routeGetName, routeGetPrice;
    JButton addroute, btnBack;



    Font fon1;

    public AddRoute(){
        route = new JFrame("Add Route");
        route.setSize(815,635);


        fon1=new Font("algerian",Font.BOLD,50);

//        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("MenuItem.png")));
//        bg.setBounds(0,0,800,600);
//        menuItemFrame.add(bg);
        // Route name and Text Field
        routeName = new JLabel("Route:");
        routeName.setBounds(64,180,60,30);
        route.add(routeName);

        routeGetName = new JTextField();
        routeGetName .setBounds(64,210,272,40);
        routeGetName .setBackground(Color.WHITE);
        routeGetName .setForeground(Color.BLACK);
        routeGetName .setBorder(null);
        routeGetName .setHorizontalAlignment(JTextField.CENTER);
        route.add(routeGetName );

        // Food Price and Text Field
        routePrice = new JLabel("Price:");
        routePrice.setBounds(64,330,60,30);
        route.add(routePrice);

        routeGetPrice = new JTextField();
        routeGetPrice.setBounds(64,365,272,40);
        routeGetPrice.setBackground(Color.WHITE);
        routeGetPrice.setForeground(Color.BLACK);
        routeGetPrice.setBorder(null);
        routeGetPrice.setHorizontalAlignment(JTextField.CENTER);
        route.add(routeGetPrice);

        // Add Food Button
        addroute = new JButton("Add Route");
        addroute.setBounds(148,442,102,28);
        addroute.setBackground(Color.WHITE);
        addroute.setForeground(Color.BLACK);
        addroute.setBorder(null);
        addroute.addActionListener(this);
        route.add(addroute);



        btnBack = new JButton("Back");
        btnBack.setBounds(27,19,85,32);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        route.add(btnBack);

        route.setLayout(null);
        route.setVisible(true);

    }
    private  void resetFood(){
        routeGetName.setText("");
        routeGetPrice.setText("");
    }


    public static void main(String[] args) {
        new AddRoute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = routeGetName.getText();
        String price = routeGetPrice.getText();

        if (e.getSource()==btnBack){
            route.dispose();
            new AdminDashboard();
        }
        else{
            if (e.getSource()==addroute){
                if (name.length()==0 || price.length()==0){
                    JOptionPane.showMessageDialog(route,"Enter All Fields Completely");
                }else {
                    DatabaseOperation mi = new DatabaseOperation();
                    String query = "insert into route (routename, routeprice) values('"+name+"', '"+price+"')";
                    int ans=mi.insert(query);
                    if (ans>0){
                        JOptionPane.showMessageDialog(route ,"Route Added Successfully");
                        resetFood();
                    }
                }

            }
        }
    }
}


