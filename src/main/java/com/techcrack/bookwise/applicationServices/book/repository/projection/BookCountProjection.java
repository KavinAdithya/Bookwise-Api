package com.techcrack.bookwise.applicationServices.book.repository.projection;

public interface BookCountProjection {
    long getTotalBooks();
    long getTotalApprovedBooks();
    long getTotalPendingBooks();
    long getTotalRejectedBooks();
}
