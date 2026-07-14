package com.techcrack.bookwise.exceptions.customized;

import java.util.Objects;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(Class<?> type, String message) {
        super("Object Type : " + type + " not found. Details : " + message);
    }
}
