import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class ShowDriver implements ActionListener {
    JFrame viewFrame;
    JTextField driverID;
    JTextArea report;
    JButton btnBack, btnReport;
    String[] driverColumns = {"Driver ID", "Full Name", "Email", "Date of Birth", "Gender", "Address", "Job Experience"};
    DefaultTableModel model;
    JTable tblDriverData;
    String user;

    public ShowDriver(String user) {
        this.user = user;
        viewFrame = new JFrame("Driver Details");
        Font fon1=new Font("cambria",Font.BOLD,20);
        viewFrame.setSize(700, 535);


        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("ReportDriver.png")));
        bg.setBounds(0,0,685,500);
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

        btnReport = new JButton("Report");
        btnReport.setBounds(541, 328, 60, 18);
        btnReport.setBackground(Color.WHITE);
        btnReport.setForeground(Color.BLACK);
        btnReport.setBorder(null);
        btnReport.addActionListener(this);
        bg.add(btnReport);

        report = new JTextArea();
        report.setBounds(130, 360, 400, 100);
        report.setBackground(Color.WHITE);
        report.setForeground(Color.BLACK);
        report.setFont(fon1);
        report.setBorder(null);
        bg.add(report);


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
    }

    public static void main(String[] args) {
        new ShowDriver("Dpaace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = driverID.getText();
        String rpt = report.getText();

        GetUserID userid = new GetUserID(user);

        if (e.getSource() == btnBack) {
            viewFrame.dispose();
            new UserDashboard(user);
        }
        if (e.getSource() == btnReport) {
            if (id.length()==0 || rpt.length()==0){
                JOptionPane.showMessageDialog(btnReport, "Please fill all the fields");
            }else{
                DatabaseOperation db = new DatabaseOperation();
                String query = "insert into reportDriver () values ('"+userid.getId()+"', '"+id+"', '"+rpt+"')";
                int ans = db.insert(query);
                if (ans > 0) {
                    driverID.setText("");
                    report.setText("");
                    JOptionPane.showMessageDialog(btnReport, "Driver Reported Successfully");
                }
            }
        }
    }
}