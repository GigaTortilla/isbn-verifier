package org.gigatortilla.isbn.verifier;

public class ISBN {
    private String numberISBN10 = "";
    private String numberISBN13 = "";
    private boolean validISBN10 = false;
    private boolean validISBN13 = false;

    public String getISBN10() {
        return numberISBN10;
    }

    public String getISBN13() {
        return numberISBN13;
    }

    public boolean getValidISBN10() {
        return validISBN10;
    }

    public boolean getValidISBN13() {
        return validISBN13;
    }

    public void setISBN10(String numberISBN10) {
        int maxLen = (numberISBN10.length() < 10) ? numberISBN10.length() : 10;
        this.numberISBN10 = numberISBN10.substring(0, maxLen);
    }

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

    // TODO: check for edge cases
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
        String bufferString = "978".concat(this.numberISBN10.substring(0, 9));
        this.numberISBN13 = bufferString.concat(Integer.toString(ISBN.calcCheckDigit13(bufferString)));
        return this.numberISBN13;
    }

    public String convert13to10() {
        if(this.numberISBN13.substring(0, 3).equals("978")) {
            String bufferString = this.numberISBN13.substring(3, 12);
            this.numberISBN10 = bufferString.concat(Integer.toString(ISBN.calcCheckDigit10(bufferString)));
            return this.numberISBN10;
        } else {
            return null;
        }
    }

    public static char calcCheckDigit10(String inputString) {
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
    }

    public static char calcCheckDigit13(String inputString) {
        return 0;
    }
}
