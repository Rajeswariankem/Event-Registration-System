package com.raaji.eventmanagement.exception;

public class EventCapacityFullException
        extends RuntimeException {

    public EventCapacityFullException(String message) {
        super(message);
    }
}