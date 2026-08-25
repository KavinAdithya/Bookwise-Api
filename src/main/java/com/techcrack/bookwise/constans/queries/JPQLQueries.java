package com.techcrack.bookwise.constans.queries;

public class JPQLQueries {
    public static final String UPDATE_AUTHOR_STATUS = """
                UPDATE Author a
                SET a.status = :status
                WHERE a.id IN :ids
            """;

    public static final String CHANGE_STATUS_ALL_BOOKS = """
                Update Book b
                SET b.isActive = :isActive,
                b.bookStatus = :status,
                b.updatedBy = :updatedBy,
                b.updatedAt = :updatedAt,
                b.publishDate = :publishDate
                WHERE b.id IN :bookIds
            """;

    public static final String FETCH_SUBSCRIPTIONS = """
                SELECT s.subscriptions
                FROM Subscription s
                WHERE s.user.id = :userId AND
                       s.isActive = true
            """;

    public static final String HAS_LIMIT_EXISTS_FOR_BORROW_BOOK = """
                SELECT COUNT(s) > 0
                FROM Subscription s
                WHERE s.user.id = :userId
                  AND s.isActive = true
                  AND s.booksAllowedPerMonth > 0
            """;
    public static final String UPDATE_BOOK_QUANTITY = """
                Update Book b
                SET b.availableCopies = b.availableCopies + :quantity,
                b.updatedBy = :updatedBy,
                b.updatedAt = :updatedAt
                WHERE b.id = :bookId
            """;
}
