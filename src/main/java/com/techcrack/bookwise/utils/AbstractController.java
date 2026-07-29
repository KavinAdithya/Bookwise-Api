package com.techcrack.bookwise.utils;

/**
 * IMPORTANT: Always use in controller layer only.
 * @param <L> Logger Type
 * @param <S> Service Type
 * @param <H> Helper Type
 */
public class AbstractController<L, S, H> extends AbstractLogger<L>{
    protected final H helper;
    protected final S service;

    public AbstractController(Class<L> loggerType, S service, H helper) {
        super(loggerType);
        this.service = service;
        this.helper = helper;
    }
}
