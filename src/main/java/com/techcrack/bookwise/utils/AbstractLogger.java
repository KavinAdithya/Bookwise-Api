package com.techcrack.bookwise.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IMPORTANT: Use only we you a need logger only.
 * @param <L> Logger Type
 */
public abstract class AbstractLogger<L> {
    protected final Logger logger;

    public AbstractLogger(Class<L> type) {
        this.logger = LoggerFactory.getLogger(type);
    }
}
