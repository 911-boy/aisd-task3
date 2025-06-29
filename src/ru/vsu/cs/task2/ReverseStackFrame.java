package ru.vsu.cs.task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Stack;
import ru.vsu.cs.course1.SimpleLinkedListStack;
import ru.vsu.cs.course1.SimpleStack;

public class ReverseStackFrame extends JFrame {
    private JTextArea inputArea = new JTextArea(10, 30);
    private JTextArea outputArea = new JTextArea(10, 30);
    private JButton loadButton = new JButton("Загрузить из файла");
    private JButton reverseCustomButton = new JButton("Реверсировать (свой стек)");
    private JButton reverseStdButton = new JButton("Реверсировать (стандартный стек)");
    private JFileChooser fileChooser = new JFileChooser();

    public ReverseStackFrame() {
        super("Реверс стека (только стеки)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(loadButton);
        add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(inputArea));
        centerPanel.add(new JScrollPane(outputArea));
        add(centerPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(reverseCustomButton);
        bottomPanel.add(reverseStdButton);
        add(bottomPanel, BorderLayout.SOUTH);
        inputArea.setBorder(BorderFactory.createTitledBorder("Исходные строки"));
        outputArea.setBorder(BorderFactory.createTitledBorder("Результат"));
        outputArea.setEditable(false);
        // Слушатели
        loadButton.addActionListener(this::onLoadFile);
        reverseCustomButton.addActionListener(e -> reverseWithCustomStack());
        reverseStdButton.addActionListener(e -> reverseWithStdStack());
        pack();
        setLocationRelativeTo(null);
    }

    private void onLoadFile(ActionEvent e) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                inputArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    inputArea.append(line + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка чтения файла: " + ex.getMessage());
            }
        }
    }

    private void reverseWithCustomStack() { // для собственного стека.
        String[] lines = inputArea.getText().split("\n");
        SimpleStack<String> stack = new SimpleLinkedListStack<>();
        for (String line : lines) {
            stack.push(line);
        }
        SimpleStack<String> reversed = new SimpleLinkedListStack<>();
        while (!stack.empty()) {
            try {
                reversed.push(stack.pop());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!reversed.empty()) {
            try {
                sb.append(reversed.pop()).append("\n");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
                return;
            }
        }
        outputArea.setText(sb.toString());
    }
    // Кладем строки в стек (в исходном порядке) 123
    //
    //Достаем их из стека (они выходят в обратном порядке) 321
    //
    //Кладем в новый стек (теперь они будут выходить в исходном порядке 123

    private void reverseWithStdStack() { // для стандартного стека.
        String[] lines = inputArea.getText().split("\n");
        Stack<String> stack = new Stack<>();
        for (String line : lines) {
            stack.push(line);
        }
        Stack<String> reversed = new Stack<>();
        while (!stack.isEmpty()) {
            reversed.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!reversed.isEmpty()) {
            sb.append(reversed.pop()).append("\n");
        }
        outputArea.setText(sb.toString());
    }
} 