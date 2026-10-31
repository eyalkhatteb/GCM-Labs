package com.example.gcm;

/**
 * Thrown when a username or a password breaks one of the Lab 1 rules.
 * The message says which rule was broken, so it can be shown to the user as is.
 */
public class InvalidUserException extends Exception {

    /**
     * Creates the exception.
     *
     * @param message which rule was broken, in words the user can read
     */
    public InvalidUserException(String message) {
        super(message);
    }
}
