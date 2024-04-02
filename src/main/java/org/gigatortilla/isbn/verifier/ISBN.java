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
    protected String isbn = "";
    protected boolean isValid = false;

    /**
     * @return      ISB-number stored in the ISBN object
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * @return      a {@code boolean} which represents whether or not the ISBN object contains a valid ISBN-1X number
     */
    public boolean getValidState() {
        return isValid;
    }

    /**
     * Stores the input string in the {@code ISBN} objects corresponding field with at most 13 characters for ISBN-13
     * @param isbn  the string to be stored in the {@code ISBN} object
     */
    public void setISBN(String isbn) {
        this.isbn = isbn.substring(0, (isbn.length() < 13) ? isbn.length() : 13);
    }
}
