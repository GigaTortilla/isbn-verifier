package org.gigatortilla.isbn.verifier;

import java.awt.*;
import java.awt.event.*; 

public class ISBNwindow {
    ISBNwindow() {
        Frame f = new Frame("ISBN Verifier");
        Label welcomeMessage = new Label("Welcome to the ISBN Verifier!");
        TextField inField = new TextField();
        Button submitButton = new Button("Submit");

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent wEvent) {
                f.dispose();
            }
        });

        welcomeMessage.setBounds(100, 70, 200, 30);
        welcomeMessage.setAlignment(Label.CENTER);
        inField.setBounds(120, 100, 80, 30);
        submitButton.setBounds(200, 100, 80, 30);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inField.setText("Welcome");
            }
        });

        f.add(welcomeMessage);
        f.add(inField);
        f.add(submitButton);

        f.setSize(400, 300);
        f.setIconImage(null);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    public void close() {
        System.out.println("Window close method called.");
    }
}