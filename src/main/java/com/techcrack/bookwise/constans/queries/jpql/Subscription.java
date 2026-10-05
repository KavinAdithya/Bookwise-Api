package com.techcrack.bookwise.constans.queries.jpql;

public class Subscription {
    public static final String HAS_LIMIT_EXISTS_FOR_BORROW_BOOK = """
                SELECT COUNT(s) > 0
                FROM Subscription s
                WHERE s.user.id = :userId
                  AND s.isActive = true
                  AND s.booksAllowedPerMonth > 0
            """;

    public static final String FETCH_SUBSCRIPTIONS = """
                SELECT s.subscriptions
                FROM Subscription s
                WHERE s.user.id = :userId AND
                       s.isActive = true
            """;

    public static final String FETCH_EXPIRED_USERID = """
                SELECT
                    u.id
                FROM Subscription s
                INNER JOIN s.user u
                where s.isActive AND
                    s.endDate < :currentSystemDate
            """;
}
