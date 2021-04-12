import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class StudentView extends JFrame {
    static String QUERY = "";
    JTable table = new JTable();
    public StudentView() throws SQLException {
        this.setSize(900, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Student View");
        this.setLocationRelativeTo(null);
        //Creating Top Bar Flair
        JPanel topBarPanel = new JPanel();
        GridLayout gl = new GridLayout(1, 5);
        topBarPanel.setLayout(gl);
        JLabel inputQuery = new JLabel("Input Query: ");
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(false);
        comboBox.addItem("Sam_ID");
        comboBox.addItem("Name");
        comboBox.addItem("In_Game_Name");
        comboBox.addItem("Gender");
        comboBox.addItem("Age");
        comboBox.addItem("Email");
        JTextPane searchField = new JTextPane();
        JButton searchButton = new JButton("Search!");
        topBarPanel.add(inputQuery);
        topBarPanel.add(comboBox);
        topBarPanel.add(searchField);
        topBarPanel.add(searchButton);
        //Setting up the table environment
        //Creating an Actual table to display data.
        searchButton.addActionListener(e->{
            System.out.println("The Query is: "+"select "+comboBox.getSelectedItem()+" from student where = "+searchField.getText());
            QUERY = "select "+comboBox.getSelectedItem()+" from student where "+searchField.getText();
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shsu_gaming_db","root","hongvy123");
            String[] columnHeaders = {"Sam ID", "Name", "In Game Name", "Gender", "Age", "Email", "Discord Name"};
            String query = "";
            if (QUERY.equals("")) {
                query = "select * from student";
            } else {
                query = QUERY;
            }
            ArrayList<String[]> masterList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String[] tempList = new String[7];
                tempList[0] = rs.getString(1);
                tempList[1] = rs.getString(2);
                tempList[2] = rs.getString(3);
                tempList[3] = rs.getString(4);
                tempList[4] = rs.getString(5);
                tempList[5] = rs.getString(6);
                tempList[6] = rs.getString(7);
                masterList.add(tempList);
            }
            String[][] data = new String[masterList.size()][7];
            for (int i = 0; i < masterList.size() - 1; i++) {
                data[i][0] = masterList.get(i)[0];
                data[i][1] = masterList.get(i)[1];
                data[i][2] = masterList.get(i)[2];
                data[i][3] = masterList.get(i)[3];
                data[i][4] = masterList.get(i)[4];
                data[i][5] = masterList.get(i)[5];
                data[i][6] = masterList.get(i)[6];
            }
            return new JTable(data, columnHeaders);
        } catch (SQLException e) {
            System.err.println(e);
            return null;

        }
    }
}
