import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class addStudent {
    private JPanel student;
    private JTextField classs;
    private JTextField city;
    private JTextField phone;
    private JTextField first;
    private JTextField last;
    private JComboBox sex;
    private JButton submintButton;
    private JButton backButton;
    Connection connection = database_connection.connection();
    Statement statement = null;


    public addStudent() {
        JFrame frame = new JFrame("Add Student");
        frame.setContentPane(student);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new home();

            }
        });
        submintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    statement = connection.createStatement();
                    String firstName = first.getText();
                    String lastName = last.getText();
                    String Phone = phone.getText();
                    String City = city.getText();
                    int Class = Integer.parseInt(classs.getText());
                    String Sex = (String) sex.getSelectedItem();
                    String sql = "INSERT INTO students (first_name,last_name, Phone, City, class, Sex) VALUES " +
                            "('" + firstName + "','" + lastName + "','" + Phone + "','" + City + "' , '" + Class + "' , '" + Sex + "')";
                    statement.executeUpdate(sql);


                    JOptionPane.showMessageDialog(null, "Data is successfully inserted", "Successful", JOptionPane.INFORMATION_MESSAGE);

                    first.setText("");
                    last.setText("");
                    phone.setText("");
                    city.setText("");
                    classs.setText("");


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields", "Try Again", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
    }


}
