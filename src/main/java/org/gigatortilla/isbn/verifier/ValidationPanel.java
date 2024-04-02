package org.gigatortilla.isbn.verifier;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ValidationPanel extends JPanel {
    ValidationPanel(JPanel contentPane, boolean isISBN13) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(4, 1, 5, 5));
        setPreferredSize(new Dimension(400, 300));

        int version = (isISBN13) ? 13 : 10;
        JLabel enterMessage = new JLabel("Please enter the number to be checked for ISBN-" + version + " conformity:", JLabel.CENTER);
        JLabel outputMessage = new JLabel("", JLabel.CENTER);
        JTextField inputField = new JTextField(version);
        JButton checkButton = new JButton("Check");

        AbstractAction returnToMenu = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                contentPane.setVisible(false);
                contentPane.removeAll();
                contentPane.add(new MainMenu(contentPane));
                contentPane.setVisible(true);
            }
        };

        AbstractAction checkISBN = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                outputMessage.setText(generateMessage(inputField.getText(), isISBN13));
            }
        };

        inputField.addActionListener(checkISBN);
        checkButton.addActionListener(checkISBN);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(returnToMenu);
        backButton.setHorizontalAlignment(SwingConstants.RIGHT);
        backButton.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel backBtnPanel = new JPanel();
        backBtnPanel.add(backButton);
        backBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(checkButton);

        add(enterMessage);
        add(inputPanel);
        add(outputMessage);
        add(backBtnPanel);
    }

    private String generateMessage(String inputISBN, boolean isISBN13) {
        if(isISBN13) {
            try {
                if(ISBN_13.check(inputISBN)) {
                    return "This ISBN-13 number is valid: " + inputISBN;
                } else {
                    return "Your input " + inputISBN + " is not a valid ISBN-13 number.";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            try {
                if(ISBN_10.check(inputISBN)) {
                    return "This ISBN-10 number is valid: " + inputISBN;
                } else {
                    return "Your input " + inputISBN + " is not a valid ISBN-10 number.";
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return e.getMessage();
            }
            
        }
    }
}
