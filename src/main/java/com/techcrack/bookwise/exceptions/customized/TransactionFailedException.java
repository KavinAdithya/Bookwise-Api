package com.techcrack.bookwise.exceptions.customized;

public class TransactionFailedException extends RuntimeException {
    public TransactionFailedException(String message) {
        super(message);
    }
}
