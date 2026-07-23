package com.techcrack.bookwise.constans;

public class RawQueries {
    public static final String DEACTIVATE_ALL_SUBSCRIPTIONS = """
                Update Subscriptions
                SET is_active = 0,
                updated_by = :updatedBy,
                updated_at = :updatedAt
                WHERE user_id = :userId
            """;

    public static final String BORROW_LIMIT_AVAILABLE = """
                SELECT EXISTS (
                    SELECT 1
                    FROM Subscriptions
                    WHERE is_active = 1 AND
                           user_id = :userId
                )
            """;
}
