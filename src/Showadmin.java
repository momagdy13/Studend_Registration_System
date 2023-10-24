import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Showadmin {
    private JPanel admin;
    private JTable table;
    private JButton backButton;
    Connection connection = database_connection.connection();
    Statement statement = null;
    ResultSet rs = null;

    public Showadmin() {


        JFrame frame = new JFrame("Showadmin");
        frame.setContentPane(admin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        showRecord();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new home();
            }
        });


    }

    public void showRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM admin";
            rs = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

