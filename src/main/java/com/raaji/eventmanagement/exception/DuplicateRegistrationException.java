package com.raaji.eventmanagement.exception;

public class DuplicateRegistrationException
        extends RuntimeException {

    public DuplicateRegistrationException(
            String message) {

        super(message);
    }
}