package com.techcrack.bookwise.exceptions.customized;

public class InsufficientAmountPaidException extends RuntimeException{
    public InsufficientAmountPaidException(String message) {
        super(message);
    }
}
