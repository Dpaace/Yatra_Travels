import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class ShowRoute implements ActionListener {
    JFrame viewFrame;
    JTextField routeID;
    JButton btnBack, btnDelete;
    String[] routeColumns = {"Route ID", "Route Name", "Travel Price"};
    DefaultTableModel model;
    JTable tblRouteData;

    public ShowRoute() {
        viewFrame = new JFrame("Route Details");
        Font f = new Font("arial", Font.BOLD, 18);
        viewFrame.setLocation(350,150);
        viewFrame.setSize(700, 435);


        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("ViewRoute.png")));
        bg.setBounds(0,0,685,400);
        viewFrame.add(bg);

        btnBack = new JButton("Back");
        btnBack.setBounds(24, 362, 55, 23);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);

        routeID = new JTextField();
        routeID.setBounds(375, 324, 150, 23);
        routeID.setBackground(Color.WHITE);
        routeID.setForeground(Color.BLACK);
        routeID.setBorder(null);
        routeID.setHorizontalAlignment(JTextField.CENTER);
        bg.add(routeID);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(541, 328, 60, 18);
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setBorder(null);
        btnDelete.addActionListener(this);
        bg.add(btnDelete);

        model = new DefaultTableModel(new String[][]{}, routeColumns);
        tblRouteData = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(tblRouteData);
        jScrollPane.setBounds(68, 57, 550, 250);
        bg.add(jScrollPane);


        try {
            DatabaseOperation db = new DatabaseOperation();
            String query = "select * from route";
            ResultSet rs = db.select(query);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewFrame.setLayout(null);
        viewFrame.setVisible(true);
        viewFrame.setResizable(false);
    }

    public static void main(String[] args) {
        new ShowRoute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = routeID.getText();

        if (e.getSource() == btnBack) {
            viewFrame.dispose();
            new AdminDashboard();
        }
        if (e.getSource() == btnDelete) {
            try {
                DatabaseOperation db = new DatabaseOperation();
                String query = "delete from route where routeID='" + id + "'";
                int rowsDeleted = db.executeDelete(query);
                if (rowsDeleted > 0) {
                    routeID.setText("");
                    JOptionPane.showMessageDialog(btnDelete, "Successfully deleted");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

