package com.techcrack.bookwise.applicationServices.user.repository.queries.projection;

public interface UserCountProjection {
    long getTotalUsers();
    long getTotalAuthors();
}
