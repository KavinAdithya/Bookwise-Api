package com.techcrack.bookwise.exceptions.customized;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}
