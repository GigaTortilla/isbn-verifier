package org.gigatortilla.isbn.verifier;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ValidationPanel extends JPanel {
    ValidationPanel(JPanel contentPane, boolean isISBN13) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(4, 1, 5, 5));
        setPreferredSize(new Dimension(400, 300));

        int version = (isISBN13) ? 13 : 10;

        AbstractAction returnToMenu = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                contentPane.setVisible(false);
                contentPane.removeAll();
                contentPane.add(new MainMenu(contentPane));
                contentPane.setVisible(true);
            }
        };

        JLabel enterMessage = new JLabel("Please enter the number to be checked for ISBN-" + version + " conformity:", JLabel.CENTER);
        JLabel outputMessage = new JLabel("", JLabel.CENTER);
        JTextField inputField = new JTextField(version);
        JButton checkButton = new JButton("Check");
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
}
