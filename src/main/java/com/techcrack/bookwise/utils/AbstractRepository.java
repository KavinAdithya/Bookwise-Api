package com.techcrack.bookwise.utils;

/**
 * IMPORTANT: Use Only when you need logger and repo. Ensure Used in service layer only.
 * @param <L> Logger Type
 * @param <R> Repository Type
 */
public abstract class AbstractRepository<L, R> extends AbstractLogger<L> {
    protected final R repo;

    public AbstractRepository(Class<L> logger, R repo) {
        super(logger);
        this.repo = repo;
    }
}
