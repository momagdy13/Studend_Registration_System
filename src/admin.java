import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class admin {


    private JPanel admin;
    private JTextField name;
    private JTextField mail;
    private JPasswordField pass;
    private JCheckBox showPasswordCheckBox;
    private JButton updateButton;
    private JButton backButton;
    private JTextField id;
    private JButton serch;
    private JButton delete;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public admin() {
        JFrame frame = new JFrame("admin");
        frame.setContentPane(admin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new home();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Id = id.getText();
                    String Name = name.getText();
                    String Mail = mail.getText();
                    String Pass = pass.getText();
                    String sql = "UPDATE admin SET mail ='"+Mail+"', password = '"+Pass+"',name = '"+Name+"' WHERE ID = '"+Id+"'";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null , "Data Updated Successfully");
                    id.setText("");
                    mail.setText("");
                    name.setText("");
                    pass.setText("");

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }

            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()){

                    pass.setEchoChar((char) 0);

                }else {
                    pass.setEchoChar('•');
                }
            }
        });
        serch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String admin_id = id.getText();
                    String sql = "SELECT * FROM admin WHERE ID = '" + admin_id + "'";
                    ResultSet rs = statement.executeQuery(sql);

                    if (rs.next()){
                        mail.setText(rs.getString("mail"));
                        pass.setText(rs.getString("password"));
                        name.setText(rs.getString("name"));


                    }else {
                        JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }


                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    statement = connection.createStatement();
                    String student_id = id.getText();
                    String sql = "DELETE FROM admin WHERE ID = '" + student_id + "'";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data is successfully Deleted");
                    id.setText("");
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
