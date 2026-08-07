package com.techcrack.bookwise.utils;

import com.techcrack.bookwise.jwt.CurrentUserService;

/**
 * IMPORTANT: Use Only when you need logger and repo. Ensure Used in service layer only.
 * @param <L> Logger Type
 * @param <R> Repository Type
 */
public abstract class AbstractRepository<L, R> extends AbstractLogger<L> {
    protected final R repo;
    protected final CurrentUserService userSession;

    public AbstractRepository(Class<L> logger, R repo, CurrentUserService userSession) {
        super(logger);
        this.userSession = userSession;
        this.repo = repo;
    }
}
