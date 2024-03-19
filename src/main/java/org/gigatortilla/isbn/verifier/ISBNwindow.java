package org.gigatortilla.isbn.verifier;

import java.awt.*; 

public class ISBNwindow {
    ISBNwindow() {
        Frame f = new Frame("ISBN Verifier");
        Label welcomeMessage = new Label("Welcome to the ISBN Verifier!");
        TextField inField = new TextField();
        Button submiButton = new Button("Submit");

        welcomeMessage.setBounds(20, 80, 80, 30);
        inField.setBounds(20, 100, 80, 30);
        submiButton.setBounds(100, 100, 80, 30);

        f.add(welcomeMessage);
        f.add(inField);
        f.add(submiButton);

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
    }
}
