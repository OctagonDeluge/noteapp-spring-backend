package com.example.application.exception;

public class EventIsEmptyException extends RuntimeException {
    public EventIsEmptyException() {
        super("Event is empty");
    }
}
