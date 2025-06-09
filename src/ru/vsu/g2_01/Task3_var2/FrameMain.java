package ru.vsu.g2_01.Task3_var2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.*;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JButton addElement;
    private JButton removeElement;
    private JTable inputQ;
    private DefaultTableModel inputModel;
    private JButton sortButton;
    private JTable outputQ;
    private MyStack<String> myStack = new MyStack<>();
    private java.util.Stack<String> stdStack = new java.util.Stack<>();
    private JComboBox<String> stackTypeCombo;
    private JButton loadFromFileButton;

    public FrameMain() {
        this.setTitle("Stack Reverse Demo");
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

    
        stackTypeCombo = new JComboBox<>(new String[]{"Свой стек", "Стандартный стек"});
        loadFromFileButton = new JButton("Загрузить из файла");
        addElement = new JButton("+");
        removeElement = new JButton("-");
        sortButton = new JButton("Перевернуть");
        inputQ = new JTable();
        outputQ = new JTable();

        
        JPanel topPanel = new JPanel();
        topPanel.add(stackTypeCombo);
        topPanel.add(loadFromFileButton);
        topPanel.add(addElement);
        topPanel.add(removeElement);
        topPanel.add(sortButton);
        panelMain.add(topPanel);
        panelMain.add(new JLabel("Исходный стек:"));
        panelMain.add(new JScrollPane(inputQ));
        panelMain.add(new JLabel("Результат:"));
        panelMain.add(new JScrollPane(outputQ));

        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initTablesModels();
        fillExampleData();
        updateInputTable();
        createActionsAddRemove();
        createListenerSortButton();
        createLoadFromFileAction();

        this.pack();
        this.setSize(700, 400);
    }

    private void fillExampleData() {
        myStack = new MyStack<>();
        stdStack = new java.util.Stack<>();
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        myStack.push("4");
        stdStack.push("1");
        stdStack.push("2");
        stdStack.push("3");
        stdStack.push("4");
    }

    private void initTablesModels() {
        inputModel = new DefaultTableModel() {
            @Override
            public String getColumnName(int column) {
                return "";
            }
        };
        DefaultTableModel outputModel = new DefaultTableModel() {
            @Override
            public String getColumnName(int column) {
                return "";
            }
        };
        inputQ.setModel(inputModel);
        inputQ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        inputQ.setRowHeight(40);
        inputQ.setCellSelectionEnabled(true);
        inputQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        outputQ.setModel(outputModel);
        outputQ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        outputQ.setRowHeight(40);
        outputQ.setCellSelectionEnabled(true);
        outputQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void updateInputTable() {
        Object[] data = (stackTypeCombo.getSelectedIndex() == 1) ? stdStack.toArray() : myStack.toArray();
        inputModel.setRowCount(0);
        inputModel.setColumnCount(0);
        for (Object o : data) {
            inputModel.addColumn("");
        }
        inputModel.addRow(data);
        for (int i = 0; i < inputQ.getColumnCount(); i++) {
            TableColumn column = inputQ.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
            column.setMinWidth(100);
            column.setMaxWidth(100);
        }
    }

    private void writeStackToJTable(MyStack<String> stack) {
        Object[] data = stack.toArray();
        DefaultTableModel model = (DefaultTableModel) outputQ.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for (Object o : data) {
            model.addColumn("");
        }
        model.addRow(data);
        for (int i = 0; i < outputQ.getColumnCount(); i++) {
            TableColumn column = outputQ.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
            column.setMinWidth(100);
            column.setMaxWidth(100);
        }
    }

    private void writeStackToJTable(java.util.Stack<String> stack) {
        Object[] data = stack.toArray();
        DefaultTableModel model = (DefaultTableModel) outputQ.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for (Object o : data) {
            model.addColumn("");
        }
        model.addRow(data);
        for (int i = 0; i < outputQ.getColumnCount(); i++) {
            TableColumn column = outputQ.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
            column.setMinWidth(100);
            column.setMaxWidth(100);
        }
    }

    private void createListenerSortButton() {
        sortButton.addActionListener(e -> {
            if (stackTypeCombo.getSelectedIndex() == 0) {
                Solution.reverseStackWithMyStack(myStack);
                writeStackToJTable(myStack);
            } else {
                Solution.reverseStackWithStdStack(stdStack);
                writeStackToJTable(stdStack);
            }
        });
    }

    private void createLoadFromFileAction() {
        loadFromFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(panelMain);
            if (result == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileChooser.getSelectedFile();
                try (java.util.Scanner scanner = new java.util.Scanner(file, "UTF-8")) {
                    myStack = new MyStack<>();
                    stdStack = new java.util.Stack<>();
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        myStack.push(line);
                        stdStack.push(line);
                    }
                    updateInputTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelMain, "Ошибка чтения файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void createActionsAddRemove() {
        addElement.addActionListener(e -> {
            String value = JOptionPane.showInputDialog(panelMain, "Введите строку для добавления в стек:");
            if (value != null) {
                if (stackTypeCombo.getSelectedIndex() == 1) {
                    stdStack.push(value);
                } else {
                    myStack.push(value);
                }
                updateInputTable();
            }
        });
        removeElement.addActionListener(e -> {
            try {
                if (stackTypeCombo.getSelectedIndex() == 1) {
                    stdStack.pop();
                } else {
                    myStack.pop();
                }
                updateInputTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panelMain, "Стек пуст!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
} 