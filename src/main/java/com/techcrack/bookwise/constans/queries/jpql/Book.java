package com.techcrack.bookwise.constans.queries.jpql;

public class Book {
    public static final String CHANGE_STATUS_ALL_BOOKS = """
                Update Book b
                SET b.isActive = :isActive,
                b.bookStatus = :status,
                b.updatedBy = :updatedBy,
                b.updatedAt = :updatedAt,
                b.publishDate = :publishDate
                WHERE b.id IN :bookIds
            """;
    public static final String UPDATE_BOOK_QUANTITY = """
                Update Book b
                SET b.availableCopies = b.availableCopies + :quantity,
                b.updatedBy = :updatedBy,
                b.updatedAt = :updatedAt
                WHERE b.id = :bookId
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
    public static final String FETCH_ALL_BOOKS = """
                SELECT
                   new com.techcrack.bookwise.dtos.book.response.AdminViewBookResponse(
                        b.id,
                        b.title,
                        b.totalCopies,
                        b.availableCopies,
                        u.name,
                        c.name,
                        b.bookStatus,
                        b.coverImageUrl
                   )
                FROM Book b
                JOIN b.category c
                JOIN b.author a
                JOIN a.user u
            """;

    public static  final String FETCH_ALL_BOOKS_BY_STATUS =
            FETCH_ALL_BOOKS + """
                                WHERE b.bookStatus = :bookStatus
                           """;
    public static final String FETCH_USER_BOOK_DETAIL = """
                SELECT
                    new com.techcrack.bookwise.dtos.book.response.UserBookDetailViewResponse(
                        b.id,
                        b.title,
                        b.ISBN,
                        b.description,
                        c.name,
                        u.name,
                        b.availableCopies,
                        b.totalCopies,
                        b.borrowFee,
                        b.purchasePrice,
                        b.coverImageUrl
                    )
                FROM Book b
                JOIN b.category c
                JOIN b.author a
                JOIN a.user u
                WHERE b.id = :bookId
            """;

    public static final String FETCH_ALL_USER_BOOKS = """
            SELECT
                new com.techcrack.bookwise.dtos.book.response.UserBookViewResponse(
                    b.id,
                    b.title,
                    u.name,
                    c.name,
                    b.availableCopies,
                    b.purchasePrice,
                    b.borrowFee,
                    b.coverImageUrl
                )
            FROM Book b
            JOIN b.category c
            JOIN b.author a
            JOIN a.user u
            WHERE b.isActive
            AND b.bookStatus = "PUBLISHED"
            """;
}
