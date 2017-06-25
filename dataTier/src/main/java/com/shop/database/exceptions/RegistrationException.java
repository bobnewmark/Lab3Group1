package com.shop.database.exceptions;

/**
 * <code>RegistrationException</code> is a custom exception for cases when it is impossible to create a user.
 */
public class RegistrationException extends Exception {

    public RegistrationException() {
    }

    public RegistrationException(String message) {
        super(message);
    }
}
