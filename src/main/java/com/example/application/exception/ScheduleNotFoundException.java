package com.example.application.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException() {
        super("Schedule with such date not found");
    }
}
