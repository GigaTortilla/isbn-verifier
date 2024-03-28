package org.gigatortilla.isbn.verifier;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ValidationPanel extends JPanel {
    ValidationPanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(4, 1, 5, 5));

        JLabel enterMessage = new JLabel("Please enter the number to be checked for ISBN-10 conformity:", JLabel.CENTER);
        JLabel outputMessage = new JLabel("", JLabel.CENTER);
        JTextField inputField = new JTextField(10);
        JButton checkButton = new JButton("Check");
        JButton backButton = new JButton("Back");

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(checkButton);

        add(enterMessage);
        add(inputPanel);
        add(outputMessage);
        add(backButton);
    }
}
