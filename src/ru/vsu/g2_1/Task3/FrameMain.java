package ru.vsu.g2_1.Task3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.util.*;

public class FrameMain extends JFrame{
    private JPanel panelMain;
    private JButton addElement;
    private JButton removeElement;
    private JTable inputQ;
    private DefaultTableModel inputModel;
    private JButton sortButton;
    private JTable outputQ;
    private Queue<Integer> q = new LinkedList<>();
    private MyStack<String> myStack = new MyStack<>();
    private java.util.Stack<String> stdStack = new java.util.Stack<>();
    private JComboBox<String> stackTypeCombo;
    private JButton loadFromFileButton;

    public FrameMain() {
        this.setTitle("Stack Reverse Demo");
        if (panelMain == null) {
            panelMain = new JPanel();
            panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        }
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initTablesModels();

    
        if (stackTypeCombo == null) {
            stackTypeCombo = new JComboBox<>(new String[]{"Свой стек", "Стандартный стек"});
            panelMain.add(stackTypeCombo);
        }
        if (loadFromFileButton == null) {
            loadFromFileButton = new JButton("Загрузить из файла");
            panelMain.add(loadFromFileButton);
        }

        // Пример данных
        myStack.push("один");
        myStack.push("два");
        myStack.push("три");
        myStack.push("четыре");
        stdStack.push("один");
        stdStack.push("два");
        stdStack.push("три");
        stdStack.push("четыре");

        updateInputTable();

        createActionsAddRemove();
        createListenerSortButton();
        createLoadFromFileAction();

        this.pack();
        this.setSize(600, 350);
    }

    private Queue<Integer> readQueueFromJTable(JTable table) {

        Queue<Integer> queue = new LinkedList<>();
        TableModel model = table.getModel();

        if (model.getRowCount() == 0) {
            return queue;
        }

        for (int col = 0; col < model.getColumnCount(); col++) {
            try {
                Object value = model.getValueAt(0, col);
                int num = (value != null && !value.toString().isEmpty()) ? Integer.parseInt(value.toString().trim()) : 0;
                queue.add(num);
            } catch (NumberFormatException e) {
                queue.add(0);
                System.err.println("Некорректное значение в столбце " + col + ". Заменено на 0.");
            }
        }

        return queue;
    }

    private void writeQueueToJTable(Queue<Integer> queue) {
        DefaultTableModel model = (DefaultTableModel) outputQ.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);

        Object[] data = queue.toArray();

        for (int i = 0; i < data.length; i++) {
            model.addColumn("");
        }

        model.addRow(data);


        for (int i = 0; i < outputQ.getColumnCount(); i++) {
            TableColumn column = outputQ.getColumnModel().getColumn(i);
            column.setPreferredWidth(50);
            column.setMinWidth(50);
            column.setMaxWidth(50);
        }

        outputQ.setRowHeight(50);

        outputQ.revalidate();
        outputQ.repaint();
    }


    private void createListenerSortButton(){
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
        inputQ.setRowHeight(50);

        inputQ.setCellSelectionEnabled(true);
        inputQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        outputQ.setModel(outputModel);
        outputQ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        outputQ.setRowHeight(50);


        outputQ.setCellSelectionEnabled(true);
        outputQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void updateInputTable() {
        Object[] data;
        if (stackTypeCombo != null && stackTypeCombo.getSelectedIndex() == 1) {
            data = stdStack.toArray();
        } else {
            data = myStack.toArray();
        }
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

    private void createActionsAddRemove() {
        addElement.addActionListener(e -> {

//            Queue<Integer> newQueue = new LinkedList<>();
//            newQueue.add(0);
//
//            while (!q.isEmpty()) {
//                newQueue.add(q.remove());
//            }
//
//            q = newQueue;

            ((LinkedList<Integer>)q).addFirst(0);
            updateInputTable();

        });

        removeElement.addActionListener(e -> {
            try {
                q.remove();
                updateInputTable();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Очередь пуста!",
                        "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });

        inputModel.addTableModelListener(e -> {
            int row = 0;
            int col = e.getColumn();
            if (col >= 0 && col < q.size()) {
                try {
                    Object value = inputModel.getValueAt(row, col);
                    int newValue = value != null ? Integer.parseInt(value.toString()) : 0;

                    ArrayList<Integer> tempList = new ArrayList<>(q);
                    tempList.set(col, newValue);

                    q.clear();
                    q.addAll(tempList);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Введите целое число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    updateInputTable();
                }
            }
        });
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
}


