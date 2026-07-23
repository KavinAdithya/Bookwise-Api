package com.techcrack.bookwise.utils;

public abstract class BaseLoggerRepository<T, R> extends BaseLogger<T> {
    protected final R repo;

    public BaseLoggerRepository(Class<T> logger, R repo) {
        super(logger);
        this.repo = repo;
    }
}
