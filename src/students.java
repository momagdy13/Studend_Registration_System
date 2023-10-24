import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class students {
    private JPanel student;
    private JTextField first;
    private JButton updateButton;
    private JButton backButton;
    private JButton deleteButton;
    private JTextField last;
    private JTextField phone;
    private JTextField city;
    private JTextField classs;
    private JTextField sex;
    private JButton searchButton;
    private JTextField id;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public students() {

        JFrame frame = new JFrame("students");
        frame.setContentPane(student);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    int ID = Integer.parseInt(id.getText());
                    String firstName = first.getText();
                    String lastName = last.getText();
                    String Phone = phone.getText();
                    String City = city.getText();
                    int Class = Integer.parseInt(classs.getText());
                    String Sex = sex.getText();
                    String sql = "UPDATE students SET first_name = '" + firstName +"'  ,last_name=  '" + lastName + "', Phone=   '" + Phone + "', City= '" + City + "' , class =  '" + Class + "', Sex ='" + Sex + "' WHERE ID ='"+ID+"' ";
                    statement.executeUpdate(sql);


                    JOptionPane.showMessageDialog(null, "Data is successfully Updated", "Successful", JOptionPane.INFORMATION_MESSAGE);

                    first.setText("");
                    last.setText("");
                    phone.setText("");
                    city.setText("");
                    classs.setText("");
                    sex.setText("");
                    id.setText("");


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields", "Try Again", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new home();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String student_id = id.getText();
                    String sql = "SELECT * FROM students WHERE ID = '" + student_id + "'";
                    ResultSet rs = statement.executeQuery(sql);

                    if (rs.next()) {
                        first.setText(rs.getString("first_name"));
                        last.setText(rs.getString("last_name"));
                        phone.setText(rs.getString("Phone"));
                        city.setText(rs.getString("City"));
                        classs.setText(rs.getString("class"));
                        sex.setText(rs.getString("Sex"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }


            }

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    statement = connection.createStatement();
                    String student_id = id.getText();
                    String sql = "DELETE FROM students WHERE ID = '" + student_id + "'";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data is successfully Deleted");
                    first.setText("");
                    last.setText("");
                    phone.setText("");
                    city.setText("");
                    classs.setText("");
                    sex.setText("");





                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }
            }
        });
    }


}
