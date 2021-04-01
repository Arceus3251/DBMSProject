import javax.swing.*;
import java.awt.*;

public class ProficiencyView extends JFrame {
    public ProficiencyView(){
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
        this.add(BorderLayout.NORTH, topPanel);
        this.setVisible(true);
    }
}
