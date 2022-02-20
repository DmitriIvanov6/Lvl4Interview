package ru.gb.rest;

public class BadDataException extends RuntimeException{
    public BadDataException(String message) {
        super(message);
    }
}
