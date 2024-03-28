package org.gigatortilla.isbn.verifier;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -7321417026904326962L;
    private JPanel contentPane;

    MainWindow() {
        setTitle("ISBN Verifier");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        setMinimumSize(new Dimension(410, 310));
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setMinimumSize(new Dimension(400, 300));
        setContentPane(contentPane);

        // Create Main Menu
        JPanel mainMenu = new MainMenu(contentPane);

        // Add the main menu to the content pane
        contentPane.add(mainMenu);
    }
}