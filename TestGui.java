package com.mycompany.testgui;

import javax.swing.*;
import java.awt.event.*;

public class TestGui extends JFrame implements ActionListener {
    
    private JTextField textField1;
    
    public TestGui() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textField1 = new JTextField();
        
        add(textField1);

        textField1.setBounds(0, 50, 335, 50);
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    public static void main(String[] args) {
        TestGui gui1 = new TestGui();
        
        gui1.setBounds(0, 0, 335, 540);
        gui1.setVisible(true);
        gui1.setLocationRelativeTo(null);
        gui1.setResizable(true);
    }
}
