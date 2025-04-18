package ui;

import model.Question;
import service.ExamService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TakeTestUI extends JFrame {
    JTextArea qArea;
    JRadioButton aBtn, bBtn, cBtn, dBtn;
    JButton nextBtn, submitBtn;
    ButtonGroup optionGroup;

    ArrayList<Question> questions;
    int index = 0, score = 0;

    public TakeTestUI() {
        setTitle("📝 Test Window");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        qArea = new JTextArea();
        qArea.setFont(new Font("Arial", Font.PLAIN, 16));
        qArea.setEditable(false);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        aBtn = new JRadioButton();
        bBtn = new JRadioButton();
        cBtn = new JRadioButton();
        dBtn = new JRadioButton();

        optionGroup = new ButtonGroup();
        optionGroup.add(aBtn);
        optionGroup.add(bBtn);
        optionGroup.add(cBtn);
        optionGroup.add(dBtn);

        optionsPanel.add(aBtn);
        optionsPanel.add(bBtn);
        optionsPanel.add(cBtn);
        optionsPanel.add(dBtn);

        JPanel bottomPanel = new JPanel();
        nextBtn = new JButton("Next");
        submitBtn = new JButton("Submit");
        bottomPanel.add(nextBtn);
        bottomPanel.add(submitBtn);

        add(new JScrollPane(qArea), BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        questions = new ExamService().getAllQuestions();
        if (questions.isEmpty()) {
            qArea.setText("No questions uploaded yet. Contact Admin.");
            nextBtn.setEnabled(false);
            submitBtn.setEnabled(false);
            return;
        }

        showQuestion(index);

        nextBtn.addActionListener(e -> {
            checkAnswer();
            index++;
            if (index < questions.size()) {
                showQuestion(index);
            } else {
                nextBtn.setEnabled(false);
                submitBtn.setEnabled(true);
            }
        });

        submitBtn.addActionListener(e -> {
            checkAnswer();
            JOptionPane.showMessageDialog(this, "Test Finished!\nYour Score: " + score + " / " + questions.size());
            dispose();
        });

        setVisible(true);
    }

    void showQuestion(int idx) {
        Question q = questions.get(idx);
        qArea.setText("Q" + (idx + 1) + ": " + q.getQuestionText());
        aBtn.setText("A. " + q.getOptionA());
        bBtn.setText("B. " + q.getOptionB());
        cBtn.setText("C. " + q.getOptionC());
        dBtn.setText("D. " + q.getOptionD());
        optionGroup.clearSelection();
    }

    void checkAnswer() {
        Question q = questions.get(index);
        char selected = ' ';
        if (aBtn.isSelected()) selected = 'A';
        else if (bBtn.isSelected()) selected = 'B';
        else if (cBtn.isSelected()) selected = 'C';
        else if (dBtn.isSelected()) selected = 'D';

        if (selected == q.getCorrectOption()) {
            score++;
        }
    }
}