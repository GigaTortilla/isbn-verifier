package org.gigatortilla.isbn.verifier;

public class Main {
    public static void main(String[] args) {
        ISBNwindow window = new ISBNwindow();
        window.createStartupScreen();

        MainWindow frame = new MainWindow();
        frame.setVisible(true);
    }
}