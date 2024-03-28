package org.gigatortilla.isbn.verifier;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ValidationPanel extends JPanel {
    ValidationPanel(JPanel contentPane) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(4, 1, 5, 5));

        AbstractAction returnToMenu = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                contentPane.setVisible(false);
                contentPane.removeAll();
                contentPane.add(new MainMenu(contentPane));
                contentPane.setVisible(true);
            }
        };

        JLabel enterMessage = new JLabel("Please enter the number to be checked for ISBN-10 conformity:", JLabel.CENTER);
        JLabel outputMessage = new JLabel("", JLabel.CENTER);
        JTextField inputField = new JTextField(10);
        JButton checkButton = new JButton("Check");
        JButton backButton = new JButton("Back");
        backButton.addActionListener(returnToMenu);

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(checkButton);

        add(enterMessage);
        add(inputPanel);
        add(outputMessage);
        add(backButton);
    }
}
