package ChatApplication;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registration {

    private final JFrame registrationFrame = new JFrame("Registration");
    private final JPanel registrationPanel = new JPanel();
    
    public Registration() {
        initComponents();
    }

    private void initComponents() {
        // Get an instance of authentication class
        Authentication authentication = Authentication.getInstance();

        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setSize(850, 700);

        registrationFrame.add(registrationPanel);
        registrationPanel.setLayout(null);
        registrationPanel.setBackground(new java.awt.Color(60, 63, 65));
        Color textColor = new java.awt.Color(187, 187, 187);

        // Create all components for registration window
        JLabel titleLabel = new JLabel("Rekisteröityminen");
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 35));
        titleLabel.setBounds(275, 20, 400, 100);
        titleLabel.setForeground(textColor);

        JLabel usernameLabel = new JLabel("Käyttäjänimi");
        usernameLabel.setBounds(275, 190, 100, 25);
        usernameLabel.setForeground(textColor);
        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(275, 220, 300, 45);
        usernameField.setFont(new java.awt.Font("Segoe UI", 1, 16));

        JLabel passwordLabel = new JLabel("Salasana");
        passwordLabel.setBounds(275, 265, 100, 25);
        passwordLabel.setForeground(textColor);
        passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));

        // TODO Add another password field to verify password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(275, 290, 300, 45);
        passwordField.setFont(new java.awt.Font("Segoe UI", 1, 16));

        JLabel emailLabel = new JLabel("Sähköposti");
        emailLabel.setBounds(275, 335, 100, 25);
        emailLabel.setForeground(textColor);
        emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));

        JTextField emailField = new JTextField();
        emailField.setBounds(275, 360, 300, 45);
        emailField.setFont(new java.awt.Font("Segoe UI", 1, 16));

        JButton registerButton = new JButton();
        registerButton.setBackground(new java.awt.Color(79, 119, 240));
        registerButton.setForeground(textColor);
        registerButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        registerButton.setText("Rekisteröidy");
        registerButton.setBounds(275, 440, 300, 40);

        JButton loginButton = new JButton();
        loginButton.setBackground(new java.awt.Color(79, 119, 240));
        loginButton.setForeground(textColor);
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        loginButton.setText("Jo käyttäjä? Kirjaudu");
        loginButton.setBounds(275, 500, 300, 40);
        
        // Add components to JPanel
        registrationPanel.add(titleLabel);
        registrationPanel.add(usernameLabel);
        registrationPanel.add(usernameField);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(emailLabel);
        registrationPanel.add(emailField);
        registrationPanel.add(loginButton);
        registrationPanel.add(registerButton);

        // Add functionality to register button
        registerButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            String user = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String nickname = user;
            
            if (password.isEmpty() || user.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Käyttäjänimi/salasana ei saa olla tyhjä", "Kirjautumisvirhe", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!authentication.addUser(user, password, email, nickname)) {
                    JOptionPane.showMessageDialog(null, "Käyttäjänimi on jo rekisteröity", "Kirjautumisvirhe", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Rekisteröityminen onnistui", "Rekisteröityminen", JOptionPane.INFORMATION_MESSAGE);
                    registrationFrame.setVisible(false);
                    Login login = new Login();
                    login.setVisible(true);
                }
            }
            // Focus to username field
            usernameField.requestFocus();
        });
        
        // Add functionality to login button
        loginButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            setVisible(false);
            Login login = new Login();
            login.setVisible(true);
        });
    }
    // Set visiblity of registration window
    void setVisible(boolean visible) {
        registrationFrame.setVisible(visible);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Registration registration = new Registration();
            registration.setVisible(true);
        });
    }
}
