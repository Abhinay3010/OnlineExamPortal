package ui;

import model.Question;
import service.ExamService;

import javax.swing.*;
import java.awt.*;

public class UploadQuestionUI extends JFrame {
    JTextArea questionArea;
    JTextField aField, bField, cField, dField, correctField;
    JButton submitButton;

    public UploadQuestionUI() {
        setTitle("➕ Add MCQ Question");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridLayout(7, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        questionArea = new JTextArea();
        aField = new JTextField();
        bField = new JTextField();
        cField = new JTextField();
        dField = new JTextField();
        correctField = new JTextField();

        submitButton = new JButton("Upload");

        add(new JLabel("Question:")); add(questionArea);
        add(new JLabel("Option A:")); add(aField);
        add(new JLabel("Option B:")); add(bField);
        add(new JLabel("Option C:")); add(cField);
        add(new JLabel("Option D:")); add(dField);
        add(new JLabel("Correct Option (A/B/C/D):")); add(correctField);
        add(new JLabel()); add(submitButton);

        submitButton.addActionListener(e -> {
            Question q = new Question(
                0,
                questionArea.getText().trim(),
                aField.getText().trim(),
                bField.getText().trim(),
                cField.getText().trim(),
                dField.getText().trim(),
                correctField.getText().trim().toUpperCase().charAt(0)
            );

            boolean result = new ExamService().uploadQuestion(q);
            JOptionPane.showMessageDialog(this, result ? "Question Added!" : "Upload Failed");
            dispose();
        });

        setVisible(true);
    }
}
