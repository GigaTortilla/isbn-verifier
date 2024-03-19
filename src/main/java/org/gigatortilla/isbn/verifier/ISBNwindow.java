package org.gigatortilla.isbn.verifier;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class ISBNwindow implements ActionListener{
    TextField inField;

    ISBNwindow() {
        Frame f = new Frame("ISBN Verifier");
        Label welcomeMessage = new Label("Welcome to the ISBN Verifier!");
        inField = new TextField();
        Button submitButton = new Button("Submit");

        welcomeMessage.setBounds(20, 70, 160, 30);
        inField.setBounds(20, 100, 80, 30);
        submitButton.setBounds(100, 100, 80, 30);

        submitButton.addActionListener(this);

        f.add(welcomeMessage);
        f.add(inField);
        f.add(submitButton);

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        inField.setText("Welcome");
    }
}
