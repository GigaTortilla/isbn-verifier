package org.gigatortilla.isbn.verifier;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -7321417026904326962L;
    private JPanel contentPane;
    private JPanel mainMenu;
    private JPanel validationPanel;

    MainWindow() {
        setTitle("ISBN Verifier");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setMinimumSize(new Dimension(400, 300));
        setContentPane(contentPane);

        // Create Main Menu
        mainMenu = new JPanel();
        mainMenu.setBorder(new EmptyBorder(40, 50, 40, 50));
        mainMenu.setLayout(new GridLayout(2, 1));

        JLabel welcomeMessage = new JLabel("Welcome to the ISBN-1X Verifier!", JLabel.CENTER);
        JButton validateButton10 = new JButton("Verify ISBN-10");
        validateButton10.addActionListener(switchToValidate10);
        JButton validateButton13 = new JButton("Verify ISBN-13");
        JPanel btnPanel = new JPanel();
        FlowLayout btnPanelLayout = (FlowLayout) btnPanel.getLayout();
        btnPanelLayout.setHgap(20);
        btnPanel.add(validateButton10);
        btnPanel.add(validateButton13);

        mainMenu.add(welcomeMessage);
        mainMenu.add(btnPanel);

        // Create the Validation Window
        validationPanel = new ValidationPanel();

        // Add the main menu to the content pane
        contentPane.add(mainMenu);
    }

    AbstractAction switchToValidate10 = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            contentPane.setVisible(false);
            contentPane.remove(mainMenu);
            contentPane.add(validationPanel);
            contentPane.setVisible(true);
        }
    };
}