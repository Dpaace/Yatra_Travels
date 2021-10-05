import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BookTicket implements ActionListener {
    JFrame viewFrame;
    //JButton btnBack;
    String[] routeColumns = {"Route ID", "Route Name", "Travel Price"};
    DefaultTableModel routeModel;
    JTable routeData;

    JLabel lblRouteBooking, routeId, itemQuantity;
    JTextField routeGetId, routeGetQuantity;
    JButton bookTicket,btnBack, reset;
    String user;


    public BookTicket(String user) {
        this.user = user;
        viewFrame = new JFrame("Book Ticket");
        Font f = new Font("arial", Font.BOLD, 18);
        viewFrame.setSize(915, 535);
        viewFrame.setLocation(300,100);


        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("BookTicket.png")));
        bg.setBounds(0,0,900,500);
        viewFrame.add(bg);

        btnBack = new JButton("Back");
        btnBack.setBounds(27,19,85,32);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);


        // Table for showing food items
        routeModel = new DefaultTableModel(new String[][]{}, routeColumns);
        routeData = new JTable(routeModel);
        JScrollPane jScrollPaneFood = new JScrollPane(routeData);
        jScrollPaneFood.setBounds(50, 91, 550, 360);
        bg.add(jScrollPaneFood);


        try {
            DatabaseOperation db = new DatabaseOperation();
            String query = "select * from route";
            ResultSet rs = db.select(query);
            while (rs.next()) {
                routeModel.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Booking Portal

        //Book Items over here
        lblRouteBooking = new JLabel("Book Tickets over here");
        lblRouteBooking.setBounds(650, 50, 150, 25);
        viewFrame.add(lblRouteBooking);

        routeId = new JLabel("Enter Route ID : ");
        routeId.setBounds(620, 70, 100, 25);
        viewFrame.add(routeId);

        routeGetId = new JTextField();
        routeGetId.setBounds(662, 220, 135, 25);
        routeGetId.setBackground(Color.WHITE);
        routeGetId.setForeground(Color.BLACK);
        routeGetId.setBorder(null);
        routeGetId.setHorizontalAlignment(JTextField.CENTER);
        bg.add(routeGetId);

        itemQuantity = new JLabel("Enter Quantity : ");
        itemQuantity.setBounds(620, 100, 100, 25);
        viewFrame.add(itemQuantity);

        routeGetQuantity = new JTextField();
        routeGetQuantity.setBounds(662, 274, 135, 25);
        routeGetQuantity.setBackground(Color.WHITE);
        routeGetQuantity.setForeground(Color.BLACK);
        routeGetQuantity.setBorder(null);
        routeGetQuantity.setHorizontalAlignment(JTextField.CENTER);
        bg.add(routeGetQuantity);

        bookTicket = new JButton("Book");
        bookTicket.setBounds(634, 314, 70, 25);
        bookTicket.setBackground(Color.WHITE);
        bookTicket.setForeground(Color.BLACK);
        bookTicket.setBorder(null);
        bookTicket.addActionListener(this);
        bg.add(bookTicket);

        reset = new JButton("Reset");
        reset.setBounds(753, 314, 70, 25);
        reset.setBackground(Color.WHITE);
        reset.setForeground(Color.BLACK);
        reset.setBorder(null);
        reset.addActionListener(this);
        bg.add(reset);

        viewFrame.setLayout(null);
        viewFrame.setVisible(true);
        viewFrame.setResizable(false);

    }

    private void ticketReset() {
        routeGetId.setText("");
        routeGetQuantity.setText("");
    }



    public static void main(String[] args) {
        new BookTicket("bbb");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GetUserID userid = new GetUserID(user);
        //System.out.println(userid.getId());

        // For Food item
        String id = routeGetId.getText();
        String qty = routeGetQuantity.getText();

        if (e.getSource()==btnBack){
            viewFrame.dispose();
            new UserDashboard(user);
        }

        TicketOrder td = new TicketOrder(id, qty);
        if (e.getSource() == bookTicket) {
            if (td.getTicketID().length() == 0 || td.getTicketQuantity().length() == 0) {
                JOptionPane.showMessageDialog(bookTicket, "Please fill all the fields");
            } else {
                DatabaseOperation db = new DatabaseOperation();
                String query = "insert into bookedTicket () values('" + userid.getId() + "', '" + td.getTicketID() + "', '" + td.getTicketQuantity() + "')";
                int ans = db.insert(query);
                if (ans > 0) {
                    JOptionPane.showMessageDialog(bookTicket, "Ticket Booked Successfully");
                    ticketReset();
                }
            }
        }

        if (e.getSource() == reset) {
            ticketReset();
        }

    }
}

