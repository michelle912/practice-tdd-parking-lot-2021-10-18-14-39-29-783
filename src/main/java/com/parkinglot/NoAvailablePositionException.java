package com.parkinglot;

public class NoAvailablePositionException extends Exception {
    public NoAvailablePositionException(String message) {
        super(message);
    }
}
