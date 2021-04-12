import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ProficiencyView extends JFrame {
    static String QUERY = "";
    JTable table = new JTable();
    public ProficiencyView() throws SQLException {
        this.setSize(900, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Proficiency View");
        this.setLocationRelativeTo(null);
        //Creating Top Bar Flair
        JPanel topBarPanel = new JPanel();
        GridLayout gl = new GridLayout(1, 5);
        topBarPanel.setLayout(gl);
        JLabel inputQuery = new JLabel("Input Query: ");
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(false);
        comboBox.addItem("Sam_ID");
        comboBox.addItem("Game_ID");
        comboBox.addItem("Skill");
        comboBox.addItem("Hours_Played");
        JTextPane searchField = new JTextPane();
        JButton searchButton = new JButton("Search!");
        topBarPanel.add(inputQuery);
        topBarPanel.add(comboBox);
        topBarPanel.add(searchField);
        topBarPanel.add(searchButton);
        //Setting up the table environment
        //First Establishing a connection to MySQL
        //Creating an Actual table to display data.
        searchButton.addActionListener(e->{
            System.out.println("The Query is: "+"select "+comboBox.getSelectedItem()+" from proficiency where = "+searchField.getText());
            QUERY = "select "+comboBox.getSelectedItem()+" from shsu_gaming_db.proficiency where "+searchField.getText();
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
            String[] columnHeaders = {"Sam ID", "Game ID", "Skill", "Hours Played"};
            String query = "";
            if (QUERY.equals("")) {
                query = "select * from proficiency";
            } else {
                query = QUERY;
            }
            ArrayList<String[]> masterList = new ArrayList<>();
            Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String[] tempList = new String[4];
                    tempList[0] = rs.getString(1);
                    tempList[1] = rs.getString(2);
                    tempList[2] = rs.getString(3);
                    tempList[3] = rs.getString(4);
                    masterList.add(tempList);
                }
            String[][] data = new String[masterList.size()][4];
            for (int i = 0; i < masterList.size() - 1; i++) {
                data[i][0] = masterList.get(i)[0];
                data[i][1] = masterList.get(i)[1];
                data[i][2] = masterList.get(i)[2];
                data[i][3] = masterList.get(i)[3];
            }
            return new JTable(data, columnHeaders);
        } catch (SQLException e) {
            System.err.println(e);
            return null;

        }
    }
}
