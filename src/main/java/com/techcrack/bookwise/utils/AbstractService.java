package com.techcrack.bookwise.utils;

import com.techcrack.bookwise.jwt.CurrentUserService;

/**
 * IMPORTANT: Use when we need validation and repo in the service layer.
 * @param <L> Logger Class Type
 * @param <R> Repository Type
 * @param <V> Validation Type
 */
public abstract class AbstractService<L, R, V> extends AbstractLogger<L>  {
    protected final V validations;
    protected final R repo;
    protected final CurrentUserService userSession;

    public AbstractService(Class<L> loggerType, R repo, V validation,  CurrentUserService userSession) {
        super(loggerType);
        this.repo = repo;
        this.validations = validation;
        this.userSession = userSession;
    }
}
