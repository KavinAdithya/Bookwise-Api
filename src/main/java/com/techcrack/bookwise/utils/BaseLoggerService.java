package com.techcrack.bookwise.utils;

public class BaseLoggerService<T, S> extends BaseLogger<T> {
    protected final S service;

    public BaseLoggerService(Class<T> loggerType, S service) {
        super(loggerType);
        this.service = service;
    }
}
