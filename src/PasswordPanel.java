import javax.swing.*;
import java.awt.*;

public class PasswordPanel extends JFrame {
    int incorrect = 0;
    public PasswordPanel(){
        this.setSize(300,100);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        JLabel promptLabel = new JLabel("Please enter the Administrator's password.");
        JPasswordField passField = new JPasswordField();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event ->{
            StringBuilder password = new StringBuilder();
            for(char a: passField.getPassword()) {
                password.append(a);
            }
            if(password.toString().equals("HeccinGamer")){
                this.dispose();
                new AdminPanel();
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Password", "Invalid Password!", JOptionPane.WARNING_MESSAGE);
                incorrect++;
                if(incorrect == 3){
                    JOptionPane.showMessageDialog(null, "Too many failed attempts, this incident will be reported.", "Invalid Password!", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Someone tried to log in as an administrator!");
                    this.dispose();
                }
            }
        });
        JPanel fieldAndButton = new JPanel();
        GridLayout gl = new GridLayout(1,2);
        fieldAndButton.setLayout(gl);
        fieldAndButton.add(passField);
        fieldAndButton.add(submitButton);
        this.add(fieldAndButton);
        this.add(BorderLayout.NORTH, promptLabel);
        this.getRootPane().setDefaultButton(submitButton);
    }
}
