import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ProficiencyView extends JFrame {
    public ProficiencyView() throws SQLException {
        this.setSize(900, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Proficiency View");
        this.setLocationRelativeTo(null);
        //Creating Top Bar Flair
        JTextField searchField = new JTextField("Search Proficiencies...");
        JButton searchButton = new JButton("Search!");
        JPanel topPanel = new JPanel();
        topPanel.add(BorderLayout.WEST, searchField);
        topPanel.add(BorderLayout.EAST, searchButton);
        //Setting up the table environment
        //First Establishing a connection to MySQL
        Connection conn = null;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost/test?"+"user=root&password=root");
        }
        catch(SQLException ex){
            System.err.println("SQLException: "+ex.getMessage());
            System.err.println("SQLState: "+ex.getSQLState());
            System.err.println("VenderError:"+ ex.getErrorCode());
        }
        //Creating an Actual table to display data.
        String[] ColumnHeaders = {"Sam ID", "Game ID", "Skill", "Hours Played"};
        JTable table = new JTable(getTable(conn), ColumnHeaders);
        //Adds everything to frame
        this.add(BorderLayout.NORTH, topPanel);
        this.setVisible(true);
    }
    //Gathers the data to be viewed in a JTable
    public static String[][] getTable(Connection con) throws SQLException {
        String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";
        ArrayList<String[]> masterList = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String[] tempList = new String[4];
                tempList[0] = rs.getString("SamID");
                tempList[1] = rs.getString("GameID");
                tempList[2] = rs.getString("Skill");
                tempList[3] = rs.getString("HoursPlayed");
                masterList.add(tempList);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        String[][] data = new String[masterList.size()][4];
        for(int i = 0;i<masterList.size()-1;i++){
            data[i] = masterList.get(i);
        }
        return data;
    }
}
