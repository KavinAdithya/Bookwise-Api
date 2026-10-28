package com.techcrack.bookwise.applicationServices.user.repository.queries;

public class UserSQLQueries {
    public static final String USER_COUNT = """
            SELECT
                Count(u.id) AS totalUsers,
                COALESCE(
                    SUM(
                        CASE
                            WHEN a.id IS NULL
                                THEN 0
                            ELSE 1
                        END
                     )
                     , 0
                ) AS totalAuthors
            FROM Users u
            LEFT JOIN Authors a
            ON u.id = a.user_id
            WHERE u.role != 'ADMIN'
            """;
}
