package org.gigatortilla.isbn.verifier;

/**
 * The {@code ISBN} class is an abstract representation of an ISBN-13 or an ISBN-10 number with its respective equivalent. 
 * The numbers can be converted between the different formats. 
 * An ISBN object can contain strings which may or may not be valid ISB-numbers.
 * They can be checked using the {@code setISBN10state()} and {@code setISBN13state()} methods respectively.
 * 
 * @author      GigaTortilla
 */
public class ISBN {
    private String numberISBN10 = "";
    private String numberISBN13 = "";
    private boolean validISBN10 = false;
    private boolean validISBN13 = false;

    /**
     * @return      ISBN-10 number stored in the ISBN object
     */
    public String getISBN10() {
        return numberISBN10;
    }

    /**
     * @return      ISBN-13 number stored in the ISBN object
     */
    public String getISBN13() {
        return numberISBN13;
    }

    /**
     * @return      a {@code boolean} which represents whether or not the ISBN object contains a valid ISBN-10 number
     */
    public boolean getValidISBN10() {
        return validISBN10;
    }

    /**
     * @return      a {@code boolean} which represents whether or not the ISBN object contains a valid ISBN-13 number
     */
    public boolean getValidISBN13() {
        return validISBN13;
    }

    /**
     * Stores the input string in the {@code ISBN} objects corresponding ISBN-10 field
     * @param numberISBN10  the string to be stored in the {@code ISBN} object
     */
    public void setISBN10(String numberISBN10) {
        int maxLen = (numberISBN10.length() < 10) ? numberISBN10.length() : 10;
        this.numberISBN10 = numberISBN10.substring(0, maxLen);
    }

    /**
     * Stores the input string in the {@code ISBN} objects corresponding field for ISBN-13
     * @param numberISBN13  the string to be stored in the {@code ISBN} object
     */
    public void setISBN13(String numberISBN13) {
        int maxLen = (numberISBN13.length() < 13) ? numberISBN10.length() : 13;
        this.numberISBN13 = numberISBN13.substring(0, maxLen);
    }

    public void setISBN10state() {
        this.validISBN10 = ISBN.checkISBN10(this.numberISBN10);
    }

    public void setISBN13state() {
        this.validISBN13 = ISBN.checkISBN13(this.numberISBN13);
    }

    // TODO: check for edge cases and add documentation
    public static boolean checkISBN10(String inputISBN) {
        int buffer = 0;
        if(inputISBN.length() < 10) {
            System.out.println("The string " + inputISBN + " is too short!");
            return false;
        }

        if(inputISBN.substring(0, 10).contains("X")) {
            if(inputISBN.indexOf('X') != 9) {
                System.out.println("The string " + inputISBN + " is not a valid ISBN-10!");
                return false;
            }
        }

        for (int i = 0; i < inputISBN.substring(0, 10).length(); i++) {
            if(Character.isDigit(inputISBN.charAt(i))) {
                buffer += (inputISBN.charAt(i) - '0') * (i + 1);
            }else if(inputISBN.charAt(i) == 'X') {
                buffer += 100;
            }
        }

        return buffer % 11 == 0;
    }

    /**
     * Checks if the input String is a valid ISBN-13 number.
     * @param inputISBN     the {@code String} to be checked 
     * @return              {@code true} if the input {@code String} is valid, 
     *                      {@code false} if the input {@code String} is not a valid ISBN-13 number 
     *                      or the {@code String} either is shorter than 3 digits or contains non-numeric characters
     */
    public static boolean checkISBN13(String inputISBN) {
        int buffer = 0;
        if(inputISBN.length() < 13) {
            System.out.println("The string " + inputISBN + " is too short!");
            return false;
        }

        // Check if the input contains only numbers
        try {
            Long.parseLong(inputISBN);

            for(int i = 0; i < inputISBN.substring(0, 13).length(); i++) {
                buffer += (inputISBN.charAt(i) - '0') * ((i % 2 == 0) ? 1 : 3);
            }
            return buffer % 10 == 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String convert10to13() {
        try {
            String bufferString = "978".concat(this.numberISBN10.substring(0, 9));
            this.numberISBN13 = bufferString.concat(Integer.toString(ISBN.calcCheckDigit13(bufferString)));
            return this.numberISBN13;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            return null;
        }
        
    }

    public String convert13to10() {
        try {
            if(this.numberISBN13.substring(0, 3).equals("978")) {
                String bufferString = this.numberISBN13.substring(3, 12);
                this.numberISBN10 = bufferString.concat(Integer.toString(ISBN.calcCheckDigit10(bufferString)));
                return this.numberISBN10;
            } else {
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            return null;
        }
    }

    /** 
     * Calculates the check digit for ISBN-10 numbers.
     * @param       inputString     the number to calculate the check digit for
     * @return      the check digit as a {@code char} and returns {@code '\0'} if the {@code String} is the wrong length
     */
    public static char calcCheckDigit10(String inputString) {
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

    /** 
     * Calculates the check digit for ISBN-13 numbers. Since ISBN-13 is a subset of EAN13 this method is the same for both codes.
     * @param       inputString     the number to calculate the check digit for
     * @return      {@code char} representation of the check digit or {@code '\0'} if the string has the wrong length or type 
     */
    public static char calcCheckDigit13(String inputString) {
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
}
