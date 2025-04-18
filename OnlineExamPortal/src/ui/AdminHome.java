package ui;

import javax.swing.*;
import java.awt.*;

public class AdminHome extends JFrame {
    public AdminHome() {
        setTitle("🛠 Admin Panel - Exam Portal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton uploadQBtn = new JButton("📤 Upload New Question");
        uploadQBtn.setFont(new Font("Arial", Font.BOLD, 18));
        JButton viewResultsBtn = new JButton("📊 View All Results");
        viewResultsBtn.setFont(new Font("Arial", Font.BOLD, 18));
        viewResultsBtn.addActionListener(e -> new ViewResultsUI());
        add(viewResultsBtn);

        uploadQBtn.addActionListener(e -> new UploadQuestionUI());

        add(uploadQBtn);
        setVisible(true);
    }
}
