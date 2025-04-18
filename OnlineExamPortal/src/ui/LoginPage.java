package ui;

import model.User;
import service.AuthService;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginPage() {
        setTitle("🎓 Online Exam Portal - Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 20, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Username:")); add(usernameField);
        add(new JLabel("Password:")); add(passwordField);
        add(new JLabel("")); add(loginButton);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        User u = new AuthService().login(user, pass);
        if (u == null) {
            JOptionPane.showMessageDialog(this, "Invalid login!");
            return;
        }

        dispose();
        if (u.getRole().equalsIgnoreCase("admin")) {
            new AdminHome();
        } else {
            new StudentHome(u.getUsername());
        }
    }
}