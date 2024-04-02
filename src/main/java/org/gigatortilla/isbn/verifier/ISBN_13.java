package org.gigatortilla.isbn.verifier;

public class ISBN_13 extends ISBN {
    ISBN_13() {
        super();
    }

    ISBN_13(String isbn) {
        super();
        this.isbn = isbn.substring(0, (isbn.length() < 13) ? isbn.length() : 13);
    }

    ISBN_13(String isbn, boolean isValid) {
        super();
        this.isbn = isbn.substring(0, (isbn.length() < 13) ? isbn.length() : 13);
        this.isValid = isValid;
    }

    /**
     * Checks if the input String is a valid ISBN-13 number.
     * @param inputISBN     the {@code String} to be checked 
     * @return              {@code true} if the input {@code String} is valid, 
     *                      {@code false} if the input {@code String} is not a valid ISBN-13 number
     * @throws IllegalArgumentException if the input {@code String} is too short. 
     *                                  If it is too long everything past the 10th character is not used.
     * @throws NumberFormatException if the input {@code String} contains non-numeric characters.
     */
    public static boolean check(String inputISBN) {
        int buffer = 0;
        if(inputISBN.length() < 13) {
            throw new IllegalArgumentException("The input string consists of less than 13 characters!");
        }
        Long.parseLong(inputISBN);

        for(int i = 0; i < inputISBN.substring(0, 13).length(); i++) {
            buffer += (inputISBN.charAt(i) - '0') * ((i % 2 == 0) ? 1 : 3);
        }
        return buffer % 10 == 0;
    }

    /** 
     * Calculates the check digit for ISBN-13 numbers. Since ISBN-13 is a subset of EAN13 this method is the same for both codes.
     * @param       inputString     the number to calculate the check digit for
     * @return      {@code char} representation of the check digit or {@code '\0'} if the string has the wrong length or type 
     */
    public static char calculateCheckDigit(String inputString) {
        int sum = 0;
        int result;

        // Ensure the array is accessed correctly to prevent an IndexOutOfBoundsException
        try {
            for(int i = 0; i < 12; i++) {
                // Multiply the digit with its corresponding weight
                // the weight is alternated between 1 and 3
                sum += (inputString.charAt(i) - '0') * (i % 2 == 0 ? 1 : 3);
            }
            // The result has to be between 0 and 9 such that the weighted sum
            // of the complete ISBN is divisible by 10
            result = 10 - sum % 10;
            return (char) ('0' + (result == 10 ? 0 : result));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            return '\0';
        }
    }

    public ISBN_10 convertToISBN_10() {
        if(this.isValid) {
            String bufferString = this.isbn.substring(3, 12);
            return new ISBN_10(bufferString.concat(Character.toString(ISBN_10.calculateCheckDigit(bufferString))), true);
        } else {
            return null;
        }
    }
}
