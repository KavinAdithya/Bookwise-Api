package com.techcrack.bookwise.constans;

public class RawQueries {
    public static final String DEACTIVATE_ALL_SUBSCRIPTIONS = """
                Update Subscriptions
                SET is_active = 0,
                updated_by = :updatedBy,
                updated_at = :updatedAt
                WHERE user_id = :userId
            """;


}
