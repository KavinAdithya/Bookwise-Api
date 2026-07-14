package com.techcrack.bookwise.responseHelper;

public record RegistrationResult<T, K>(
        T entity,
        K relatedEntity
) {}