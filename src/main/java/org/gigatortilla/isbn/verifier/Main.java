package org.gigatortilla.isbn.verifier;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println(Paths.get("./src/main/resources/coding.png").toAbsolutePath().toString());
        ISBNwindow window = new ISBNwindow();
    }
}