package com.techcrack.bookwise.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseLogger<T> {
    protected final Logger logger;

    public BaseLogger(Class<T> type) {
        this.logger = LoggerFactory.getLogger(type);
    }
}
