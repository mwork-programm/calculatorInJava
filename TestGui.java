package com.mycompany.testgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestGui extends JFrame implements ActionListener {

    private final JTextField textField1;
    private final JButton[] numberButtons = new JButton[10];
    private final JButton[] operationsButtons = new JButton[6];
    private final JPanel panelButtons;
    private final JPanel topPanel;
    private final JTextArea operationsHistory;
    private final String[] ops = {"C", "/", "*", "-", "+", "="};

    private double actualNumber = 0;
    private double previousNumber = 0;
    private String operation = "";
    private boolean newNumber = true;

    public TestGui() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        topPanel = new JPanel(new BorderLayout());
        textField1 = new JTextField();
        panelButtons = new JPanel();
        operationsHistory = new JTextArea();

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont((new Font("Arial", Font.BOLD, 24)));
            numberButtons[i].addActionListener(this);
        }

        panelButtons.setLayout(new GridLayout(4, 4, 5, 5));

        for (int i = 0; i < operationsButtons.length; i++) {
            operationsButtons[i] = new JButton(ops[i]);
            operationsButtons[i].setFont((new Font("Arial", Font.BOLD, 24)));
            operationsButtons[i].addActionListener(this);

        }

        textField1.setPreferredSize(new Dimension(100, 100));
        topPanel.add(textField1, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        textField1.setEditable(false);

        add(panelButtons);

        //Row 1
        panelButtons.add(operationsButtons[0]);
        panelButtons.add(operationsButtons[1]);
        panelButtons.add(operationsButtons[2]);
        panelButtons.add(operationsButtons[3]);

        //Row 2
        panelButtons.add(numberButtons[7]);
        panelButtons.add(numberButtons[8]);
        panelButtons.add(numberButtons[9]);
        panelButtons.add(operationsButtons[4]);

        //Row 3
        panelButtons.add(numberButtons[4]);
        panelButtons.add(numberButtons[5]);
        panelButtons.add(numberButtons[6]);
        panelButtons.add(operationsButtons[5]);

        //Row 4
        panelButtons.add(numberButtons[1]);
        panelButtons.add(numberButtons[2]);
        panelButtons.add(numberButtons[3]);
        panelButtons.add(numberButtons[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            pressNumber(command);
            return;
        }

        switch (command) {
            case "+" ->
                pressOperation("+");
            case "-" ->
                pressOperation("-");
            case "*" ->
                pressOperation("*");
            case "/" ->
                pressOperation("/");
            case "=" ->
                calculateResult();
            case "C" ->
                clean();
        }
    }

    private void pressNumber(String num) {
        if (!newNumber) {
            textField1.setText(textField1.getText() + num);
        } else {
            textField1.setText(num);
            newNumber = false;
        }
    }

    private void pressOperation(String op) {
        previousNumber = Double.parseDouble(textField1.getText());
        operation = op;
        newNumber = true;
    }

    private void calculateResult() {
        actualNumber = Double.parseDouble(textField1.getText());
        double result = 0;

        switch (operation) {
            case "+" ->
                result = previousNumber + actualNumber;
            case "-" ->
                result = previousNumber - actualNumber;
            case "*" ->
                result = previousNumber * actualNumber;
            case "/" ->
                result = previousNumber / actualNumber;
        }

        textField1.setText(String.valueOf(result));
        newNumber = true;
    }

    private void clean() {
        textField1.setText("0");
        actualNumber = 0;
        previousNumber = 0;
        operation = "";
        newNumber = true;
    }

    public static void main(String[] args) {
        TestGui gui1 = new TestGui();

        gui1.setBounds(0, 0, 335, 540);
        gui1.setVisible(true);
        gui1.setLocationRelativeTo(null);
        gui1.setResizable(true);
    }
}
