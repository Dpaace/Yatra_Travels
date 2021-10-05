import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewBooked implements ActionListener {
    JFrame showBooked;
    JTextField totalCost;
    JButton btnBack;
    int totalPrice;

    String[] ticketBooked = {"User ID","User Name", "Route ID","Route Name","Travel Cost", "Ticket QTY", "Total Cost"};
    DefaultTableModel ticketBookedModel;
    JTable ticketData;
    String user;

    public ViewBooked(String user){
        this.user = user;
        showBooked = new JFrame("Booked Tickets");
        Font f = new Font("arial", Font.BOLD, 18);
        showBooked.setLocation(300,100);
        showBooked.setSize(665, 535);


        Font fon1=new Font("cambria",Font.BOLD,20);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("ShowPayment.png")));
        bg.setBounds(0,0,650,500);
        showBooked.add(bg);

        btnBack = new JButton("Back");
        btnBack.setBounds(27,19,85,32);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);


        // Table for showing food booked items
        ticketBookedModel = new DefaultTableModel(new String[][]{}, ticketBooked);
        ticketData = new JTable(ticketBookedModel);
        JScrollPane jScrollPaneFood = new JScrollPane(ticketData);
        jScrollPaneFood.setBounds(50, 91, 550, 230);
        bg.add(jScrollPaneFood);


        try {
            DatabaseOperation db = new DatabaseOperation();
            String query = "select  bt.id, name, bt.routeid, routename, routeprice, ticketQty , (routeprice * ticketQty) as 'Total Price' from register as rg, route as ro, bookedTicket as bt where rg.id = bt.id and bt.routeid = ro.routeid and rg.name = '"+user+"' ";
            ResultSet rs = db.select(query);
            while (rs.next()) {
                ticketBookedModel.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Getting total price to pay by a particular user
        try {
            DatabaseOperation tp = new DatabaseOperation();
            String query = "select  sum(routeprice * ticketQty) as 'Total Price' from register as rg, route as ro, bookedTicket as bt where rg.id = bt.id and bt.routeid = ro.routeid and rg.name = '"+user+"' ";
            ResultSet tc = tp.select(query);
            while (tc.next()) {
                //System.out.println(tc.getInt(1));
                totalPrice = tc.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        totalCost = new JTextField("Rs. " + String.valueOf(totalPrice));
        totalCost.setBounds(212, 400, 226, 34);
        totalCost.setBackground(Color.WHITE);
        totalCost.setForeground(Color.BLACK);
        totalCost.setBorder(null);
        totalCost.setFont(fon1);
        totalCost.setHorizontalAlignment(JTextField.CENTER);
        bg.add(totalCost);

        showBooked.setLayout(null);
        showBooked.setVisible(true);
        showBooked.setResizable(false);
    }

    public static void main(String[] args) {
        new ViewBooked("Dpaace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack){
            showBooked.dispose();
            new UserDashboard(user);
        }
    }
}
