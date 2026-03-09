//java program using jdbc to connect to company_db database and display all records from the employees table using standard statement object

package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Record extends JFrame {
    private JTable table;

    public Record() {
        // Frame setup
        setTitle("Employee Records");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create table
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load data from database
        loadData();

        // Center on screen
        setLocationRelativeTo(null);
    }

    private void loadData() {
        String url = "jdbc:mysql://localhost:3306/company_db";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {

            // Get metadata for column names
            int columnCount = rs.getMetaData().getColumnCount();
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rs.getMetaData().getColumnName(i));
            }

            // Get data rows
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            // Set table model
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Record().setVisible(true);
        });
    }
}