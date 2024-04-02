package org.gigatortilla.isbn.verifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISBN_10 extends ISBN {
    ISBN_10() {
        super();
    }

    ISBN_10(String isbn) {
        super();
        this.isbn = isbn.substring(0, (isbn.length() < 10) ? isbn.length() : 10);
    }

    ISBN_10(String isbn, boolean isValid) {
        super();
        this.isbn = isbn.substring(0, (isbn.length() < 10) ? isbn.length() : 10);
        this.isValid = isValid;
    }

    /**
     * Checks whether or not the input string is a valid ISBN-10 number.
     * @param inputISBN the input {@code String} to check
     * @return {@code true} if the input {@code String} is valid, 
     *         {@code false} if the input {@code String} is not a valid ISBN-13 number
     * @throws IllegalArgumentException if the input string is less than 10 characters long, if it contains a non-numeric character 
     *                                  which is different from {@code 'X'} or {@code 'x'}, or if the X is in the wrong position
     */
    public static boolean check(String inputISBN) {
        if(inputISBN.length() < 10) {
            throw new IllegalArgumentException("The string " + inputISBN + " is less than 10 characters long!");
        }
        
        Pattern invalidCharsPattern = Pattern.compile("[^0-9Xx]");
        Matcher invalidCharsMatcher = invalidCharsPattern.matcher(inputISBN);
        if(invalidCharsMatcher.find()) {
            try {
                throw new IllegalArgumentException("An invalid character was found in position: " + invalidCharsMatcher.start());
            } catch (IllegalStateException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                throw new IllegalArgumentException("An invalid character was found! The position could not be retrieved!");
            }
        }

        // No need to catch IndexOutOfBoundsException since at that point the string not be less than 10 characters long
        if(inputISBN.substring(0, 10).contains("X") 
            || inputISBN.substring(0, 10).contains("x")) {
            int posUpperX = inputISBN.indexOf('X');
            int posLowerX = inputISBN.indexOf('x');
            if((posLowerX != 9 || posUpperX != -1) == (posLowerX != -1 || posUpperX != 9)) {
                throw new IllegalArgumentException("The letter X was found in a position other than the last!\nX: " 
                                                    + inputISBN.indexOf('X') 
                                                    + "\tx: " 
                                                    + inputISBN.indexOf('x'));
            }
        }

        int buffer = 0;
        for (int i = 0; i < inputISBN.substring(0, 10).length(); i++) {
            if(Character.isDigit(inputISBN.charAt(i))) {
                buffer += (inputISBN.charAt(i) - '0') * (i + 1);
            } else if(inputISBN.charAt(i) == 'X' || inputISBN.charAt(i) == 'x') {
                buffer += 100;
            }
        }
        
        return buffer % 11 == 0;
    }

    /** 
     * Calculates the check digit for ISBN-10 numbers.
     * @param       inputString     the number to calculate the check digit for
     * @return      the check digit as a {@code char} and returns {@code '\0'} if the {@code String} is the wrong length
     */
    public static char calculateCheckDigit(String inputString) {
        if(inputString.length() != 10)
        {
            int weight = 10;
            int buffer = 0;
            int result = 0;

            for(char c : inputString.toCharArray()) {
                buffer += weight * (c - '0');
                weight--;
            }
            result = 11 - buffer % 11;

            if(result == 10) {
                return 'X';
            } else {
                return (char) ('0' + result);
            }
        } else {
            return '\0';
        }
    }

    public ISBN_13 convertToISBN_13() {
        if(this.isValid) {
            String bufferString = "978".concat(this.isbn.substring(0, 9));
            return new ISBN_13(bufferString.concat(Character.toString(ISBN_10.calculateCheckDigit(bufferString))), true);
        } else {
            return null;
        }
    }
}
