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
        comboBox.addItem("Sam ID");
        comboBox.addItem("Name");
        comboBox.addItem("In Game Name");
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
        //First Establishing a connection to MySQL
        Connection conn = null;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/shsu_gaming_db"+"user=root&password=hongvy123");
        }
        catch(SQLException ex){
            System.err.println("SQLException: "+ex.getMessage());
            System.err.println("SQLState: "+ex.getSQLState());
            System.err.println("VenderError:"+ ex.getErrorCode());
        }
        //Creating an Actual table to display data.
        Connection finalConn = conn;
        searchButton.addActionListener(e->{
            System.out.println("The Query is: "+"select "+comboBox.getSelectedItem()+" from student where = "+searchField.getText());
            QUERY = "select "+comboBox.getSelectedItem()+" from student where = "+searchField.getText();
            try {
                table = getTable(finalConn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            table.updateUI();
        });
        table = getTable(conn);
        //Adds everything to frame
        this.add(BorderLayout.NORTH, topBarPanel);
        this.add(new JScrollPane(table));
        this.setVisible(true);
    }
    //Gathers the data to be viewed in a JTable
    public static JTable getTable(Connection con) throws SQLException {
        String[] columnHeaders = {"Sam ID", "Name", "In Game name", "Gender", "Age", "Email"};
        String query = "";
        if(QUERY.equals("")) {
            query = "select * from student";
        }
        else{
            query = QUERY;
        }
        ArrayList<String[]> masterList = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String[] tempList = new String[6];
                tempList[0] = rs.getString("SamID");
                tempList[1] = rs.getString("Name");
                tempList[2] = rs.getString("In Game Name");
                tempList[3] = rs.getString("Gender");
                tempList[4] = rs.getString("Age");
                tempList[5] = rs.getString("Email");
                masterList.add(tempList);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        String[][] data = new String[masterList.size()][6];
        for(int i = 0;i<masterList.size()-1;i++){
            data[i] = masterList.get(i);
        }
        return new JTable(data, columnHeaders);
    }
}
