package com.techcrack.bookwise.constans.queries;

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
                           user_id = :userId AND
                           (books_allowed_per_month > 0 OR
                            books_allowed_per_year > 0)
                )
            """;

    public static final String UPDATE_SUBSCRIPTION_BOOK_ALLOWED_COUNT = """
                  UPDATE Subscriptions
                  SET books_allowed_per_month = CASE
                                                    WHEN subscriptions = 'FREE'
                                                        THEN  books_allowed_per_month - :quantity
                                                      ELSE books_allowed_per_month
                                                END
                  WHERE Is_Active = 1 AND user_Id = :userId;
            """;
}
