import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class GameView extends JFrame {
    JTable table = new JTable();
    public GameView() throws SQLException {
        this.setSize(900, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Game View");
        this.setLocationRelativeTo(null);

        JPanel topBarPanel = new JPanel();
        GridLayout topLayout = new GridLayout(1, 3);
        topBarPanel.setLayout(topLayout);
        JLabel searchLabel = new JLabel("Search Query:");
        JTextPane searchField = new JTextPane();
        JButton searchButton = new JButton("Search!");
        searchButton.addActionListener(e -> {
            try {
                table = getTable(searchField.getText());
            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }
            JDialog tableView = new JDialog();
            tableView.setSize(900, 300);
            tableView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            tableView.add(new JScrollPane(table));
            tableView.setVisible(true);
        });
        topBarPanel.add(searchLabel);
        topBarPanel.add(searchField);
        topBarPanel.add(searchButton);
        table = getTable("");
        //Adds everything to frame
        this.add(BorderLayout.NORTH, topBarPanel);
        this.add(new JScrollPane(table));
        this.setVisible(true);
    }
    //Gathers the data to be viewed in a JTable
    public static JTable getTable(String query) throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shsu_gaming_db", "root", "hongvy123");
            if(query.equals("")) {
                query = "select * from game";
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new JTable(new DefaultTableModel(data, columnNames));

        } catch (SQLException ignore) {
            return null;
        }
    }
}
