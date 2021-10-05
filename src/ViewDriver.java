import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class ViewDriver implements ActionListener {
    JFrame viewFrame;
    JTextField driverID;
    JButton btnBack, btnDelete;
    String[] driverColumns = {"Driver ID", "Full Name", "Email", "Date of Birth", "Gender", "Address", "Job Experience"};
    DefaultTableModel model;
    JTable tblDriverData;

    public ViewDriver() {
        viewFrame = new JFrame("Driver Details");
        Font f = new Font("arial", Font.BOLD, 18);
        viewFrame.setBounds(400,200,700, 435);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("ViewDriver.png")));
        bg.setBounds(0,0,685,400);
        viewFrame.add(bg);

        btnBack = new JButton("Back");
        btnBack.setBounds(24, 362, 55, 23);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);

        driverID = new JTextField();
        driverID.setBounds(375, 324, 150, 23);
        driverID.setBackground(Color.WHITE);
        driverID.setForeground(Color.BLACK);
        driverID.setBorder(null);
        driverID.setHorizontalAlignment(JTextField.CENTER);
        bg.add(driverID);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(541, 328, 60, 18);
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setBorder(null);
        btnDelete.addActionListener(this);
        bg.add(btnDelete);

        model = new DefaultTableModel(new String[][]{}, driverColumns);
        tblDriverData = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(tblDriverData);
        jScrollPane.setBounds(68, 57, 550, 250);
        bg.add(jScrollPane);


        try {
            DatabaseOperation db = new DatabaseOperation();
            String query = "select * from addDriver";
            ResultSet rs = db.select(query);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewFrame.setLayout(null);
        viewFrame.setVisible(true);
        viewFrame.setResizable(false);
    }

    public static void main(String[] args) {
        new ViewDriver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = driverID.getText();

        if (e.getSource() == btnBack) {
            viewFrame.dispose();
            new AdminDashboard();
        }
        if (e.getSource() == btnDelete) {
            try {
                DatabaseOperation db = new DatabaseOperation();
                String query = "delete from addDriver where driverID='" + id + "'";
                int rowsDeleted = db.executeDelete(query);
                if (rowsDeleted > 0) {
                    driverID.setText("");
                    JOptionPane.showMessageDialog(btnDelete, "Successfully deleted");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
