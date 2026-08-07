package com.techcrack.bookwise.utils;

import com.techcrack.bookwise.jwt.CurrentUserService;

/**
 * IMPORTANT: Always use in controller layer only.
 * @param <L> Logger Type
 * @param <S> Service Type
 * @param <H> Helper Type
 */
public class AbstractController<L, S, H> extends AbstractLogger<L>{
    protected final H helper;
    protected final S service;
    protected final CurrentUserService userSession;

    public AbstractController(Class<L> loggerType, S service, H helper, CurrentUserService userSession) {
        super(loggerType);
        this.userSession = userSession;
        this.service = service;
        this.helper = helper;
    }
}
