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
        delete.setSize(815,635);
        delete.setLayout(null);

        Font fon1=new Font("cambria",Font.BOLD,20);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("UserDelete.png")));
        bg.setBounds(0,0,800,600);
        delete.add(bg);

        userID = new JLabel("User ID : ");
        userID.setBounds(100, 70, 60, 25);
        delete.add(userID);

        GetUserID id = new GetUserID(user);

        getID = new JTextField(String.valueOf(id.getId()));
        getID .setBounds(227,210,346,40);
        getID .setBackground(Color.WHITE);
        getID .setForeground(Color.BLACK);
        getID .setBorder(null);
        getID .setHorizontalAlignment(JTextField.CENTER);
        getID.setFont(fon1);
        bg.add(getID );

        pass = new JPasswordField();
        pass.setBounds(227,365,346,40);
        pass.setBackground(Color.WHITE);
        pass.setForeground(Color.BLACK);
        pass.setBorder(null);
        pass.setHorizontalAlignment(JTextField.CENTER);
        pass.setFont(fon1);
        bg.add(pass);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(315,442,172,28);
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setBorder(null);
        btnDelete.addActionListener(this);
        btnDelete.setFont(fon1);
        bg.add(btnDelete);

        btnBack = new JButton("Back");
        btnBack.setBounds(27,19,85,32);
        btnBack.setBackground(Color.decode("#f3cd74"));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(null);
        btnBack.addActionListener(this);
        bg.add(btnBack);


        delete.setVisible(true);
    }

    public static void main(String[] args) {
        new UserDelete("Dpaace");
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
