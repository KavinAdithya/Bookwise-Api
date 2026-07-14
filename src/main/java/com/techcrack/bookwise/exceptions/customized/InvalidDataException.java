package com.techcrack.bookwise.exceptions.customized;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message) {
        super(message);
    }
}
