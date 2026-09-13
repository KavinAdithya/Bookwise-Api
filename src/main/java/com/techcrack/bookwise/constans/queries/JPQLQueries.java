package com.techcrack.bookwise.constans.queries;

public class JPQLQueries {
    public static final String UPDATE_AUTHOR_STATUS = """
                UPDATE Author a
                SET a.status = :status,
                a.updatedBy = :updatedBy,
                a.updatedAt = :updatedAt
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

    public static final String FIND_EXISTING_USER = """
            SELECT u
            FROM Users u
            WHERE u.email = :email OR
            u.username = :username OR
            u.contact = :contact
            """;

    public static final String FETCH_AUTHORS_ADMIN_VIEW = """
                SELECT
                    new com.techcrack.bookwise.dtos.author.response.AdminViewAuthorResponse(
                        a.Id,
                        a.user.name,
                        a.status,
                        a.user.email,
                        a.createdAt
                    )
                FROM Author a
                WHERE a.isActive
            """;

    public static final String FETCH_ALL_USER_IDS = """
                SELECT
                    a.user.id
                FROM Author a
                WHERE a.id in :authorIds
            """;

    public static final String FETCH_AUTHOR_ADMIN_VIEW_PENDING = """
                SELECT
                    new com.techcrack.bookwise.dtos.author.response.AdminViewAuthorResponse(
                        a.Id,
                        a.user.name,
                        a.status,
                        a.user.email,
                        a.createdAt
                    )
                FROM Author a
                WHERE a.isActive
                AND a.status = :status
            """;

    public static final String FETCH_BOOKS_FOR_AUTHOR = """
                SELECT
                    new com.techcrack.bookwise.dtos.book.response.AuthorBookViewResponse(
                       b.id,
                       b.title,
                       b.category.name,
                       b.coverImageUrl,
                       b.totalCopies,
                       b.availableCopies,
                       b.bookStatus
                    )
                FROM Book b
                WHERE b.author.id = :authorId
            """;

    public static final String FIND_AUTHOR_ID_BY_USER_ID = """
                SELECT
                    a.id
                FROM Author a
                WHERE a.user.id = :userId
            """;
}
