package com.techcrack.bookwise.utils.responseHelper;

public record RegistrationResult<T, K>(
        T entity,
        K relatedEntity
) {}