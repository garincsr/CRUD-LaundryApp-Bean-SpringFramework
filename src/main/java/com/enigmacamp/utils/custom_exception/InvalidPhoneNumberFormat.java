package com.enigmacamp.utils.custom_exception;

public class InvalidPhoneNumberFormat extends Exception {
    public InvalidPhoneNumberFormat(String message) {
        super(message);
    }
}
