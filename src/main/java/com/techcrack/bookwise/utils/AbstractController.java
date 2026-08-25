package com.techcrack.bookwise.utils;

import com.techcrack.bookwise.abstractions.CurrentUserService;

/**
 * IMPORTANT: Always use in controller layer only.
 * @param <L> Logger Type
 * @param <S> Service Type
 * @param <M> Helper Type
 */
public class AbstractController<L, S, M> extends AbstractLogger<L>{
    protected final M mapper;
    protected final S service;
    protected final CurrentUserService userSession;

    public AbstractController(Class<L> loggerType, S service, M mapper, CurrentUserService userSession) {
        super(loggerType);
        this.userSession = userSession;
        this.service = service;
        this.mapper = mapper;
    }
}
