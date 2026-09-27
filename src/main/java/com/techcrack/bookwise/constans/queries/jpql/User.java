package com.techcrack.bookwise.constans.queries.jpql;

public class User {
    public static final String FIND_EXISTING_USER = """
            SELECT u
            FROM Users u
            WHERE u.email = :email OR
            u.username = :username OR
            u.contact = :contact
            """;
    public static final String FETCH_ALL_USERS = """
                SELECT
                    new com.techcrack.bookwise.dtos.user.response.
                        AdminUserViewResponse(
                            u.id,
                            u.name,
                            u.username,
                            u.email,
                            u.role)
                FROM Users u
                WHERE u.role != "AUTHOR"
            """;

    public static final String FETCH_ALL_USERS_ACTIVE_BASED =
            FETCH_ALL_USERS +
                    """
                        AND u.isActive = :isActive
                    """;
    public static final String FETCH_USER_SUBSCRIPTION = """
                SELECT
                    new com.techcrack.bookwise.dtos.user.context
                        .UserSubscriptionDetail(
                            u,
                            s
                        )
                FROM Subscription s
                JOIN s.user u
                WHERE u.isActive
                    AND u.id = :userId
            """;
}
