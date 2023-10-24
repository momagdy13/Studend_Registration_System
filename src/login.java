import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login {
    private JPanel admin;
    private JTextField email;
    private JPasswordField pass;
    private JButton cancelButton;
    private JButton loginButton;
    private JCheckBox showPasswordCheckBox;




    public login() {

        JFrame frame = new JFrame("admin");
        frame.setContentPane(admin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (email.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out Email", "MESSAGE", JOptionPane.ERROR_MESSAGE);

                } else if (pass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out password", "MESSAGE", JOptionPane.ERROR_MESSAGE);

                } else {
                    PreparedStatement ps;
                    Connection connection = database_connection.connection();
                    try {
                        ps = connection.prepareStatement("SELECT * FROM admin WHERE mail = ? AND password = ?");
                        ps.setString(1, email.getText());
                        ps.setString(2, pass.getText());
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            frame.dispose();
                             new home();

                        } else {
                            JOptionPane.showMessageDialog(null, "Email or Password invalid", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }


                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }


                }
            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    pass.setEchoChar((char) 0);
                } else {


                    pass.setEchoChar('•');
                }
            }
        });
    }

    public static void main(String[] args) {

        new login();
    }


}
