package org.gigatortilla.isbn.verifier;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;

public class ISBNwindow {
    private ISBN stateISBN = new ISBN();
    private Frame mainWindowFrame;
    private int windowWidth = 400;
    private int windowHeight = 300;

    private ActionListener sceneISBN10validate = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mainWindowFrame.removeAll();

            Label checkOutput = new Label("", Label.CENTER);
            checkOutput.setBounds(50, 150, 250, 25);
            
            TextField inputNumberField = new TextField(10);
            inputNumberField.setBounds(50, 110, 80, 25);

            // TODO: GUI representation of occured exceptions
            AbstractAction checkISBNAction = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    stateISBN.setISBN10(inputNumberField.getText());
                    stateISBN.setISBN10state();
                    if(stateISBN.getValidISBN10()) {
                        checkOutput.setText("This ISBN-10 is valid.");
                    } else {
                        checkOutput.setText("This number is not a valid ISBN-10.");
                    }
                }
            };
            inputNumberField.addActionListener(checkISBNAction);

            Button checkButton = new Button("Check");
            checkButton.setBounds(140, 110, 80, 30);
            checkButton.setBackground(new Color(240, 240, 240));
            checkButton.addActionListener(checkISBNAction);

            Button returnButton = new Button("Back");
            returnButton.setBounds(320, 250, 60, 30);
            returnButton.setBackground(new Color(240, 240, 240));

            Label hintLabel = new Label("Please input the ISBN-10 number to check:", Label.LEFT);
            hintLabel.setBounds(50, 80, 250, 25);

            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createStartupScreen();
                };
            });

            mainWindowFrame.add(returnButton);
            mainWindowFrame.add(checkButton);
            mainWindowFrame.add(hintLabel);
            mainWindowFrame.add(checkOutput);
            mainWindowFrame.add(inputNumberField);
        }
    };

    private ActionListener sceneISBN13validate = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mainWindowFrame.removeAll();

            Label hintLabel = new Label("Please input the ISBN-13 number to check:", Label.LEFT);
            hintLabel.setBounds(50, 80, 250, 25);

            Label checkOutputLabel = new Label("", Label.CENTER);
            checkOutputLabel.setBounds(50, 150, 250, 25);

            TextField inputNumberField = new TextField(13);
            inputNumberField.setBounds(50, 110, 104, 25);

            AbstractAction checkISBNAction = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    stateISBN.setISBN13(inputNumberField.getText());
                    stateISBN.setISBN13state();
                    if(stateISBN.getValidISBN13()) {
                        checkOutputLabel.setText("This ISBN-13 is valid.");
                    } else {
                        checkOutputLabel.setText("This number is not a valid ISBN-13!");
                    }
                }
            };
            inputNumberField.addActionListener(checkISBNAction);

            Button checkButton = new Button("Check");
            checkButton.setBounds(170, 110, 80, 30);
            checkButton.setBackground(new Color(240, 240, 240));
            checkButton.addActionListener(checkISBNAction);

            Button returnButton = new Button("Back");
            returnButton.setBounds(320, 250, 60, 30);
            returnButton.setBackground(new Color(240, 240, 240));
            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createStartupScreen();
                };
            });

            mainWindowFrame.add(returnButton);
            mainWindowFrame.add(hintLabel);
            mainWindowFrame.add(inputNumberField);
            mainWindowFrame.add(checkButton);
            mainWindowFrame.add(checkOutputLabel);
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
        welcomeMessage.setBounds(100, 70, 200, 30);
        welcomeMessage.setAlignment(Label.CENTER);

        TextField inField = new TextField();
        inField.setBounds(120, 100, 80, 30);

        Button submitButton = new Button("Submit");
        submitButton.setBounds(200, 100, 80, 30);
        submitButton.setBackground(new Color(240, 240, 240));

        Button buttonISBN10verify = new Button("ISBN-10");
        buttonISBN10verify.setBounds(120, 180, 70, 30);
        buttonISBN10verify.setBackground(new Color(240, 240, 240));

        Button buttonISBN13verify = new Button("ISBN-13");
        buttonISBN13verify.setBounds(200, 180, 70, 30);
        buttonISBN13verify.setBackground(new Color(240, 240, 240));

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inField.setText("Lorem ipsum");
            }
        });
        buttonISBN10verify.addActionListener(sceneISBN10validate);
        buttonISBN13verify.addActionListener(sceneISBN13validate);

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