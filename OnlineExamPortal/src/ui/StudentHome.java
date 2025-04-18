package ui;

import javax.swing.*;
import java.awt.*;

public class StudentHome extends JFrame {
    public StudentHome(String studentName) {
        setTitle("📚 Student Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel welcome = new JLabel("Welcome, " + studentName + "!");
        welcome.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startTestBtn = new JButton("📝 Take Test");
        startTestBtn.setFont(new Font("Arial", Font.BOLD, 18));
        startTestBtn.addActionListener(e -> new TakeTestUI());
        JButton myResultsBtn = new JButton("📈 View My Results");
        myResultsBtn.setFont(new Font("Arial", Font.BOLD, 18));
        myResultsBtn.addActionListener(e -> new ViewMyResultsUI(studentName));
        add(myResultsBtn);
        add(welcome);
        add(startTestBtn);

        setVisible(true);
    }
}