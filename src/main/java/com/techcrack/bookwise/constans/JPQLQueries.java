package com.techcrack.bookwise.constans;

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
                b.updatedAt = :updatedAt
                WHERE b.id IN :bookIds
            """;
}
