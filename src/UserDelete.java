import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDelete implements ActionListener {
    String user;
    JFrame delete;
    JLabel userID;
    JTextField getID;
    JPasswordField pass;
    JButton btnDelete, btnBack;

    public UserDelete(String user){
        this.user = user;

        delete = new JFrame("Delete Account");

        userID = new JLabel("User ID : ");
        userID.setBounds(100, 70, 60, 25);
        delete.add(userID);

        GetUserID id = new GetUserID(user);

        getID = new JTextField(String.valueOf(id.getId()));
        getID.setBounds(200, 70, 135, 25);
        getID.setBackground(Color.WHITE);
        getID.setForeground(Color.BLACK);
        getID.setBorder(null);
        getID.setHorizontalAlignment(JTextField.CENTER);
        delete.add(getID);

        pass = new JPasswordField();
        pass.setBounds(150, 120, 135, 25);
        pass.setBackground(Color.WHITE);
        pass.setForeground(Color.BLACK);
        pass.setBorder(null);
        pass.setHorizontalAlignment(JTextField.CENTER);
        delete.add(pass);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(120, 160,100,25);
        btnDelete.addActionListener(this);
        delete.add(btnDelete);

        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(100, 200,200,25);
        btnBack.addActionListener(this);
        delete.add(btnBack);

        delete.setLocation(300,100);
        delete.setSize(500,500);
        delete.setLayout(null);
        delete.setVisible(true);
        delete.setResizable(false);
    }

    public static void main(String[] args) {
        new UserDelete("a");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack){
            delete.dispose();
            new UserDashboard(user);
        }else {
            if (pass.getText().length()==0){
                JOptionPane.showMessageDialog(delete, "Please enter the password");
            }else {
                if (e.getSource()==btnDelete){
                    if (JOptionPane.showConfirmDialog(btnDelete, "Are you sure, you want to delete your account?" +
                            "Note that it can't be recovered again!")==0){
                        String id = getID.getText();
                        String passw = pass.getText();
                        DatabaseOperation db = new DatabaseOperation();
                        String query = "delete from register where id = '"+id+"' and password = '"+passw+"'";
                        int ans = db.executeDelete(query);
                        if (ans > 0) {
                            getID.setText("");
                            pass.setText("");
                            JOptionPane.showMessageDialog(btnDelete, "Your Account Has Been Deleted Successfully");
                            delete.dispose();
                            new LoginForm();
                        } else {
                            JOptionPane.showMessageDialog(btnDelete,"Enter the correct password");
                        }
                    }else {
                        JOptionPane.showMessageDialog(btnDelete,"Account Not Deleted");
                    }
                }
            }
        }


    }
}