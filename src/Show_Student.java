import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Show_Student {
    private JPanel show;
    private JTable table;
    private JButton backButton;
    Connection connection = database_connection.connection();
    Statement statement = null;
    ResultSet rs = null;


    public Show_Student() {
        JFrame frame = new JFrame("Show_Student");
        frame.setContentPane(show);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(800,600);
        showRecord();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new home();
                frame.dispose();
            }
        });
    }

    public void showRecord(){
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM students";
            rs = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }


}
