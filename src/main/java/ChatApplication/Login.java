package ChatApplication;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Login extends JFrame {

    private final JFrame loginFrame = new JFrame("Login");
    private final JPanel loginPanel = new JPanel();

    public Login() {
        initComponents();
    }

    private void initComponents() {
        Authentication authentication = Authentication.getInstance();

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(850, 700);

        loginFrame.add(loginPanel);
        loginPanel.setLayout(null);
        loginPanel.setBackground(new java.awt.Color(60, 63, 65));
        Color textColor = new java.awt.Color(187, 187, 187);

        // Create all components for login window
        JLabel titleLabel = new JLabel("Kirjautuminen");
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 44));
        titleLabel.setBounds(275, 20, 300, 100);
        titleLabel.setForeground(textColor);

        JLabel usernameLabel = new JLabel("Käyttäjänimi");
        usernameLabel.setBounds(275, 190, 100, 25);
        usernameLabel.setForeground(textColor);
        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(275, 220, 300, 45);

        JLabel passwordLabel = new JLabel("Salasana");
        passwordLabel.setBounds(275, 265, 100, 25);
        passwordLabel.setForeground(textColor);
        passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(275, 290, 300, 45);

        JCheckBox passwordCheckBox = new JCheckBox("Näytä salasana");
        passwordCheckBox.setBounds(272, 340, 150, 20);
        passwordCheckBox.setBackground(new java.awt.Color(60, 63, 65));
        passwordCheckBox.setForeground(textColor);

        JButton loginButton = new JButton();
        loginButton.setBackground(new java.awt.Color(79, 119, 240));
        loginButton.setForeground(textColor);
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginButton.setText("Kirjaudu");
        loginButton.setBounds(275, 380, 300, 40);

        JButton registerButton = new JButton();
        registerButton.setBackground(new java.awt.Color(79, 119, 240));
        registerButton.setForeground(textColor);
        registerButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registerButton.setText("Uusi käyttäjä? Rekisteröidy");
        registerButton.setBounds(275, 440, 300, 40);

        // Add components to JPanel
        loginPanel.add(titleLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(passwordCheckBox);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        // Set login button functionality
        loginButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            String user = usernameField.getText();
            String password = passwordField.getText();
            
            if (password.isEmpty() || user.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Käyttäjätunnus/salasana ei saa olla tyhjä", "Kirjautumisvirhe", JOptionPane.ERROR_MESSAGE);
            } else {
                // Check if username and password are correct
                if (authentication.authenticateUser(user, password)) {
                    authentication.setLoggedUser(user);
                    System.out.println("Käyttäjä " + authentication.getLoggedUser() + " kirjautui sisään.");
                } else {
                    JOptionPane.showMessageDialog(null, "Väärä käyttäjätunnus tai salasana", "Kirjautumisvirhe", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Focus to username field
            usernameField.requestFocus();
        });
        
        // Set register button functionality
        registerButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            setVisible(false);
            Registration registration = new Registration();
            registration.setVisible(true);
        });
        
        // Make password visible when checkbox is clicked
        passwordCheckBox.addActionListener((ActionEvent e) -> {
            if (passwordCheckBox.isSelected()) {
                passwordField.setEchoChar((char)0);
            } else {
                passwordField.setEchoChar('*');
            }
        });
    }
    
    // Set visibility of login window
    @Override
    public void setVisible(boolean visible) {
        loginFrame.setVisible(visible);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
