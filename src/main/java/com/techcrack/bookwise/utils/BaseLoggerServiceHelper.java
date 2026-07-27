package com.techcrack.bookwise.utils;

public class BaseLoggerServiceHelper<L, S, H> extends BaseLoggerService<L, S> {
    protected final H helper;

    public BaseLoggerServiceHelper(Class<L> loggerType, S service, H helper) {
        super(loggerType, service);
        this.helper = helper;
    }
}
