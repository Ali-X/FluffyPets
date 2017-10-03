package com.fluffypets.exeptions;

public class AccessException extends RuntimeException {
    public AccessException(String message) {
        super(message);
    }
}