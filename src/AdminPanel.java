import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    public AdminPanel(){
        this.setSize(300,300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Admin Panel");
        this.setLocationRelativeTo(null);
        JPanel buttonPanel = new JPanel();
        GridLayout buttonLayout = new GridLayout(3, 1);
        buttonPanel.setLayout(buttonLayout);
        JButton addButton = new JButton("Add An Entry");
        addButton.addActionListener(event ->{

        });
        JButton removeButton = new JButton("Delete Selected Entry(s)");
        removeButton.addActionListener(event ->{

        });
        JButton modifyButton = new JButton("Modify Selected Entry");
        modifyButton.addActionListener(event ->{

        });
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(modifyButton);
        this.add(buttonPanel);
        this.setVisible(true);
    }
}
