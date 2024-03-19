package org.gigatortilla.isbn.verifier;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ISBNwindow {
    private Frame mainWindowFrame;
    private int windowWidth = 400;
    private int windowHeight = 300;

    private ActionListener switchISBN10validate = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mainWindowFrame.removeAll();

            Button returnButton = new Button("Back");
            Label hintLabel = new Label("Please input the ISBN-10 number to check:", Label.LEFT);
            TextField inputNumberField = new TextField(10);

            returnButton.setBounds(320, 250, 60, 30);
            returnButton.setBackground(new Color(240, 240, 240));
            hintLabel.setBounds(50, 80, 250, 25);
            inputNumberField.setBounds(50, 110, 80, 25);

            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createStartupScreen();
                };
            });

            mainWindowFrame.add(returnButton);
            mainWindowFrame.add(hintLabel);
            mainWindowFrame.add(inputNumberField);
        }
    };

    private ActionListener switchISBN13validate = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mainWindowFrame.removeAll();

            Button returnButton = new Button("Back");
            Label hintLabel = new Label("Please input the ISBN-13 number to check:", Label.LEFT);
            TextField inputNumberField = new TextField(13);

            returnButton.setBounds(320, 250, 60, 30);
            returnButton.setBackground(new Color(240, 240, 240));
            hintLabel.setBounds(50, 80, 250, 25);
            inputNumberField.setBounds(50, 110, 104, 25);

            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createStartupScreen();
                };
            });

            mainWindowFrame.add(returnButton);
            mainWindowFrame.add(hintLabel);
            mainWindowFrame.add(inputNumberField);
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

        mainWindowFrame = new Frame("ISBN Verifier");
        mainWindowFrame.setIconImage(bufferedIcon);
        mainWindowFrame.setSize(windowWidth, windowHeight);
        mainWindowFrame.setBackground(new Color(192, 192, 192));
        mainWindowFrame.setLayout(null);

        // Close window with the red closing button
        mainWindowFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent wEvent) {
                mainWindowFrame.dispose();
            }
        });
    }

    public void createStartupScreen() {
        mainWindowFrame.removeAll();
        
        Label welcomeMessage = new Label("Welcome to the ISBN Verifier!");
        TextField inField = new TextField();
        Button submitButton = new Button("Submit");
        Button buttonISBN10verify = new Button("ISBN-10");
        Button buttonISBN13verify = new Button("ISBN-13");
        
        welcomeMessage.setBounds(100, 70, 200, 30);
        welcomeMessage.setAlignment(Label.CENTER);
        inField.setBounds(120, 100, 80, 30);
        submitButton.setBounds(200, 100, 80, 30);
        submitButton.setBackground(new Color(240, 240, 240));
        buttonISBN10verify.setBounds(120, 180, 70, 30);
        buttonISBN10verify.setBackground(new Color(240, 240, 240));
        buttonISBN13verify.setBounds(200, 180, 70, 30);
        buttonISBN13verify.setBackground(new Color(240, 240, 240));

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inField.setText("Lorem ipsum");
            }
        });
        buttonISBN10verify.addActionListener(switchISBN10validate);
        buttonISBN13verify.addActionListener(switchISBN13validate);

        mainWindowFrame.add(welcomeMessage);
        mainWindowFrame.add(inField);
        mainWindowFrame.add(submitButton);
        mainWindowFrame.add(buttonISBN10verify);
        mainWindowFrame.add(buttonISBN13verify);
        
        mainWindowFrame.setVisible(true);
    }
    
    public void close() {
        System.out.println("Window close method called.");
        mainWindowFrame.dispose();
    }
}