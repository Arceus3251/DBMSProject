import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class StudentView extends JFrame {
    static String QUERY = "";
    JTable table = new JTable();

    public StudentView() throws SQLException {
        this.setSize(900, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Student View");
        this.setLocationRelativeTo(null);
//        //Creating Top Bar Flair
//        JPanel topBarPanel = new JPanel();
//        GridLayout gl = new GridLayout(1, 5);
//        topBarPanel.setLayout(gl);
//        JLabel inputQuery = new JLabel("Input Query: ");
//        JComboBox<String> comboBox = new JComboBox<>();
//        comboBox.setEditable(false);
//        comboBox.addItem("Sam_ID");
//        comboBox.addItem("Name");
//        comboBox.addItem("In_Game_Name");
//        comboBox.addItem("Gender");
//        comboBox.addItem("Age");
//        comboBox.addItem("Email");
//        JTextPane searchField = new JTextPane();
//        JButton searchButton = new JButton("Search!");
//        topBarPanel.add(inputQuery);
//        topBarPanel.add(comboBox);
//        topBarPanel.add(searchField);
//        topBarPanel.add(searchButton);
//        //Setting up the table environment
//        //Creating an Actual table to display data.
//        searchButton.addActionListener(e->{
//            System.out.println("The Query is: "+"select "+comboBox.getSelectedItem()+" from student where = "+searchField.getText());
//            QUERY = "select "+comboBox.getSelectedItem()+" from student where "+searchField.getText();
//            try {
//                table = getTable();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            table.updateUI();
//        });

        JPanel topBarPanel = new JPanel();
        GridLayout topLayout = new GridLayout(1, 3);
        topBarPanel.setLayout(topLayout);
        JLabel searchLabel = new JLabel("Search Query:");
        JTextPane searchField = new JTextPane();
        JButton searchButton = new JButton("Search!");
        searchButton.addActionListener(e -> {
            QUERY = searchField.getText();
            try {
                table = getTable();
            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }
            table.updateUI();
        });
        table = getTable();
        //Adds everything to frame
        this.add(BorderLayout.NORTH, topBarPanel);
        this.add(new JScrollPane(table));
        this.setVisible(true);
    }

    //Gathers the data to be viewed in a JTable
    public static JTable getTable() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shsu_gaming_db", "root", "hongvy123");
            String query = "";
            if (QUERY.equals("")) {
                query = "select * from student";
            } else {
                query = QUERY;

            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new JTable(new DefaultTableModel(data, columnNames));

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
