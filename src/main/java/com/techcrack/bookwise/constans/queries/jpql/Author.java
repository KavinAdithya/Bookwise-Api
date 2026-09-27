package com.techcrack.bookwise.constans.queries.jpql;

public class Author {
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
    public static final String UPDATE_AUTHOR_STATUS = """
                UPDATE Author a
                SET a.status = :status,
                a.updatedBy = :updatedBy,
                a.updatedAt = :updatedAt
                WHERE a.id IN :ids
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

    public static final String FIND_AUTHOR_ID_BY_USER_ID = """
                SELECT
                    a.id
                FROM Author a
                WHERE a.user.id = :userId
            """;
}
