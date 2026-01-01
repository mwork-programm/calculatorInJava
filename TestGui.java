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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class TestGui extends JFrame implements ActionListener {

    private final JTextField display;
    private final JButton[] numberButtons = new JButton[10];
    private final JButton[] operationsButtons = new JButton[6];
    private final JPanel panelButtons;
    private final JPanel topPanel;
    private final String[] ops = {"C", "/", "*", "-", "+", "="};

    private double previousNumber = 0;
    private double actualNumber = 0;
    private double result = 0;
    private String operation = "";
    private boolean newNumber = true;

    public TestGui() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        topPanel = new JPanel(new BorderLayout());
        display = new JTextField();
        panelButtons = new JPanel();

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

        display.setPreferredSize(new Dimension(100, 100));
        topPanel.add(display, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("SansSerif", Font.BOLD, 32));

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
            display.setText(display.getText() + num);
        } else {
            display.setText(num);
            newNumber = false;
        }
    }

    private void pressOperation(String op) {
        previousNumber = Double.parseDouble(display.getText());
        operation = op;
        newNumber = true;
    }

    private void calculateResult() {
        if (operation == null || operation.isEmpty()) {
            return;
        }

        String text = display.getText();

        double actualNumber = Double.parseDouble(text);
        result = 0;

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

        if (result == Math.floor(result)) {
            display.setText(String.valueOf((int) result));
        } else {
            display.setText(String.valueOf(result));
        }

        previousNumber = result;
        newNumber = true;
        operation = null;
    }

    private void clean() {
        display.setText("0");
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
