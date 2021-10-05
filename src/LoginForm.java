import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginForm implements ActionListener{
        JFrame login_frame;
        JTextField txt_Username;
        JPasswordField txt_Password;
        JCheckBox adminCheck;
        JButton btn_login, btn_register;

        public LoginForm(){
                // Making Login Frame
                login_frame = new JFrame("Login Page");
                login_frame.setSize(615,435);
                login_frame.setLocation(400,150);
                login_frame.setLayout(null);

                // Adding Background Image
                JLabel bg = new JLabel(new ImageIcon(getClass().getResource("login.png")));
                bg.setBounds(0, 0, 600, 400);
                login_frame.add(bg);

                // Username and Password
                txt_Username = new JTextField();
                txt_Username.setBounds(84, 162, 180, 25);
                txt_Username.setBorder(null);
                txt_Username.setHorizontalAlignment(JPasswordField.CENTER);
                bg.add(txt_Username);


                txt_Password = new JPasswordField();
                txt_Password.setBounds(84, 209, 180, 25);
                txt_Password.setBorder(null);
                txt_Password.setHorizontalAlignment(JPasswordField.CENTER);
                bg.add(txt_Password);

                adminCheck = new JCheckBox("Login as Admin");
                adminCheck.setBounds(165,250,110,20);
                adminCheck.setBackground(Color.WHITE);
                adminCheck.setForeground(Color.BLACK);
                adminCheck.setBorder(null);
                bg.add(adminCheck);

                btn_login = new JButton("Login");
                btn_login.setBounds(185,280,80,25);
                btn_login.setBackground(Color.ORANGE);
                btn_login.setForeground(Color.WHITE);
                btn_login.setBorder(null);
                btn_login.addActionListener(this);
                bg.add(btn_login);

                btn_register = new JButton("Sign Up");
                btn_register.setBounds(183, 323, 65, 20);
                btn_register.setBackground(Color.WHITE);
                btn_register.setForeground(Color.BLACK);
                btn_register.setBorder(null);
                btn_register.addActionListener(this);
                bg.add(btn_register);

                Font font = new Font("Arial", Font.PLAIN, 11);

                JLabel lbl1 = new JLabel("Don't have account?");
                lbl1.setBounds(80, 320, 120, 30);
                lbl1.setForeground(Color.black);
                lbl1.setFont(font);
                bg.add(lbl1);

                JLabel lblSignup = new JLabel("Sign Up");
                lblSignup.setBounds(183, 320, 65, 30);
                lblSignup.setForeground(Color.orange);
                lblSignup.setFont(font);
                bg.add(lblSignup);

                login_frame.setVisible(true);
                login_frame.setResizable(false);

        }

        public static void main(String[] args) {
                new LoginForm();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource()==btn_register){
                        login_frame.dispose();
                        new RegistrationForm();
                }
                if (e.getSource()==btn_login){
                        // This is for admin
                        // When clicked admin will go to dashboard
                        if (adminCheck.isSelected()){
                                String adminName = txt_Username.getText();
                                String adminPassword = txt_Password.getText();
                                String adminquery = "select * from adminData where admin_name = '"+adminName+"' and admin_password = '"+adminPassword+"'";
                                DatabaseOperation adminDB = new DatabaseOperation();
                                ResultSet adminRS = adminDB.select(adminquery);
                                try {
                                        if (adminRS.next()){
                                                JOptionPane.showMessageDialog(login_frame,"Login Successful as Admin");
                                                login_frame.dispose();
                                                new AdminDashboard();
                                        }else if (adminName.length()==0 || adminPassword.length()==0){
                                                JOptionPane.showMessageDialog(login_frame, "Please fill both the details");
                                        }
                                        else {
                                                JOptionPane.showMessageDialog(login_frame,"Invalid Login Credentials");
                                        }
                                } catch (SQLException ex) {
                                        ex.printStackTrace();
                                }
                        }else {
                                // This is for normal users
                                // When clicked users will go to menu
                                String name = txt_Username.getText();
                                String pass = txt_Password.getText();
                                String query = "select * from register where name='"+name+"' and password='"+pass+"' and con_password='"+pass+"'";
                                DatabaseOperation db = new DatabaseOperation();
                                ResultSet rs = db.select(query);
                                try {
                                        if (rs.next()){
                                                JOptionPane.showMessageDialog(login_frame,"Login Successful");
                                                login_frame.dispose();
                                                new UserDashboard(name);
                                        }else if (name.length()==0 || pass.length()==0){
                                                JOptionPane.showMessageDialog(btn_login, "Please fill both the details");
                                        }
                                        else {
                                                JOptionPane.showMessageDialog(login_frame,"Invalid Login Credentials");
                                        }
                                } catch (SQLException ex) {
                                        ex.printStackTrace();
                                }
                        }
                }
        }
}
