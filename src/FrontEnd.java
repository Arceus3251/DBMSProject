import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrontEnd {
    public static void main(String[] args){
        //Initializing the Main Frame
        JFrame mainFrame = new JFrame("SHSU ESports Repository");
        mainFrame.setSize(900,300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Creating the menu bar
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        fileMenu.add(exitButton);
        mb.add(fileMenu);
        JMenu viewMenu = new JMenu("View");
        JMenuItem adminButton = new JMenuItem("Admin");
        adminButton.addActionListener(e-> new PasswordPanel());
        viewMenu.add(adminButton);
        mb.add(viewMenu);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpButton = new JMenuItem("Help");
        helpButton.addActionListener(e-> JOptionPane.showMessageDialog(null, "No.", "Help", JOptionPane.INFORMATION_MESSAGE));
        JMenuItem aboutButton = new JMenuItem("About");
        aboutButton.addActionListener(e-> JOptionPane.showMessageDialog(null, "SHSU ESports Repository created by:\n" +
                "Waleed Afroze, Josh Staples, Adam Seltzer, and Preston Truong\n" +
                "\n" +
                "Programmed for DBMS COSC3318", "Credits", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(helpButton);
        helpMenu.add(aboutButton);
        mb.add(helpMenu);
        JLabel welcomeLabel = new JLabel("Welcome to the SHSU ESports Repository!");
        //Commented out to be used as a template, changing this
        /*Creating the table for Data to be viewed
        String[] columnNames = {"Test1", "Test2", "Test3"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        JTable dataTable = new JTable();
        dataTable.setModel(model);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataTable.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(dataTable);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
         */

        //Creating a Panel for selection Buttons
        JPanel selectionPanel = new JPanel();
        GridLayout selectLayout = new GridLayout(3,1);
        selectionPanel.setLayout(selectLayout);
        JButton studentViewButton = new JButton("Student Viewer");
        JButton proficiencyViewer = new JButton("Proficiency Viewer");
        JButton gameViewer = new JButton("Game Viewer");
        studentViewButton.addActionListener(e->new StudentView());
        proficiencyViewer.addActionListener(e->new ProficiencyView());
        gameViewer.addActionListener(e->new GameView());
        selectionPanel.add(studentViewButton);
        selectionPanel.add(proficiencyViewer);
        selectionPanel.add(gameViewer);

        //Adding components to the main Display Frame
        //mainFrame.add(scroll);
        mainFrame.add(selectionPanel);
        mainFrame.add(BorderLayout.NORTH, mb);
        mainFrame.add(BorderLayout.AFTER_LAST_LINE, welcomeLabel);
        mainFrame.setVisible(true);
    }
}
