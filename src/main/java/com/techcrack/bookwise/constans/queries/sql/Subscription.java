package com.techcrack.bookwise.constans.queries.sql;

public class Subscription {
    public static final String DEACTIVATE_ALL_SUBSCRIPTIONS = """
                Update Subscriptions
                SET is_active = 0,
                updated_by = :updatedBy,
                updated_at = :updatedAt
                WHERE user_id = :userId
            """;

    public static final String UPDATE_SUBSCRIPTION_BOOK_ALLOWED_COUNT = """
                  UPDATE Subscriptions
                  SET books_allowed_per_month = books_allowed_per_month - :quantity
                  WHERE Is_Active = 1 AND user_Id = :userId;
            """;

    public static final String INACTIVATE_EXPIRED_SUBSCRIPTIONS = """
                UPDATE Subscriptions s
                SET Is_Active = 0
                WHERE s.Is_Active = 1 AND
                    s.user_id IN :userIds
            """;
}
