package com.techcrack.bookwise.utils;

public abstract class BaseLoggerRepoValidation<T, R, V> extends BaseLoggerRepository<T, R> {
    protected final V validations;

    public BaseLoggerRepoValidation(Class<T> loggerType, R repo, V validation) {
        super(loggerType, repo);
        this.validations = validation;
    }
}
