package ui;

import service.ResultService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewMyResultsUI extends JFrame {
    JTextArea resultArea;

    public ViewMyResultsUI(String studentUsername) {
        setTitle("📈 My Previous Results");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        ArrayList<String> results = new ResultService().getResultsByStudent(studentUsername);
        if (results.isEmpty()) {
            resultArea.setText("No results found.");
        } else {
            StringBuilder sb = new StringBuilder("Your Result History:\n\n");
            for (String r : results) sb.append(r).append("\n");
            resultArea.setText(sb.toString());
        }

        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        setVisible(true);
    }
}