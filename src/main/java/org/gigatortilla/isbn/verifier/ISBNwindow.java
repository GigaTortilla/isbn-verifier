package org.gigatortilla.isbn.verifier;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class ISBNwindow {
    TextField inField;

    ISBNwindow() {
        Frame f = new Frame("ISBN Verifier");
        Label welcomeMessage = new Label("Welcome to the ISBN Verifier!");
        inField = new TextField();
        Button submitButton = new Button("Submit");

        welcomeMessage.setBounds(20, 70, 160, 30);
        inField.setBounds(20, 100, 80, 30);
        submitButton.setBounds(100, 100, 80, 30);

        OuterActionListener o = new OuterActionListener(this);
        submitButton.addActionListener(o);

        f.add(welcomeMessage);
        f.add(inField);
        f.add(submitButton);

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
    }

}

class OuterActionListener implements ActionListener{
    ISBNwindow i;

    OuterActionListener(ISBNwindow i) {
        this.i = i;
    }

    public void actionPerformed(ActionEvent e) {
        i.inField.setText("Welcome");
    }
}