package com.techcrack.bookwise.applicationServices.book.repository.queries;

public class BookSqlQueries {
    public static final String BOOK_COUNT = """
            SELECT
                COUNT(b.id) AS totalBooks,
                COALESCE(
                    SUM(
                        CASE WHEN b.book_status = 'PUBLISHED' THEN 1
                            ELSE 0
                        END
                    ), 0) AS totalApprovedBooks,
                COALESCE(SUM(
                     CASE WHEN b.book_status = 'PENDING' THEN 1
                            ELSE 0
                        END
                ), 0) As totalPendingBooks,
                COALESCE(SUM(
                     CASE WHEN b.book_status = 'REJECTED' THEN 1
                            ELSE 0
                        END
                ), 0) AS totalRejectedBooks
            FROM Books b
            """;
}
