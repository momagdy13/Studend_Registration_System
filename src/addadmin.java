import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class addadmin {
    private JPanel admin;
    private JTextField name;
    private JButton submintButton;
    private JButton backButton;
    private JPasswordField pass;
    private JCheckBox showPasswordCheckBox;
    private JTextField mail;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public addadmin() {
        JFrame frame = new JFrame("addadmin");
        frame.setContentPane(admin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(500,700);






        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new home();

            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()){
                    pass.setEchoChar((char) 0);
                } else {


                    pass.setEchoChar('•');
                }

            }
        });
        submintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String NAME = name.getText();
                    String Email = mail.getText();
                    int Pass = Integer.parseInt(pass.getText());
                    String sql = "INSERT INTO admin (mail ,password,name) VALUES('"+Email+"','"+Pass+"','"+NAME+"')";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Insert Data Successfully");
                    name.setText("");
                    mail.setText("");
                    pass.setText("");

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }

            }
        });
    }

}
