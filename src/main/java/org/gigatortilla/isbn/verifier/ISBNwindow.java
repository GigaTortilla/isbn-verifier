package org.gigatortilla.isbn.verifier;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ISBNwindow {
    ActionListener switchISBN10validate = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // TODO: Switch to ISBN-10 input
        }
    };

    ActionListener switchISBN13validate = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // TODO: Switch to ISBN-13 input
        }
    };

    ISBNwindow() {
        File iconFile = new File(Paths.get(".\\src\\main\\resources\\coding.png").toAbsolutePath().toString());
        iconFile.setReadOnly();
        BufferedImage bufferedIcon = new BufferedImage(48, 48, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            bufferedIcon = ImageIO.read(iconFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Frame f = new Frame("ISBN Verifier");
        Label welcomeMessage = new Label("Welcome to the ISBN Verifier!");
        TextField inField = new TextField();
        Button submitButton = new Button("Submit");

        // Close window with the red closing button
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
        f.setIconImage(bufferedIcon);
        f.setBackground(new Color(192, 192, 192));
        f.setLayout(null);
        f.setVisible(true);
    }
    
    public void close() {
        System.out.println("Window close method called.");
    }
}