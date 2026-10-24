package com.techcrack.bookwise.constans.queries.sql;

public class AuthorRevenueSqlQueries {
    public static final String TOTAL_AUTHOR_REVENUE = """
                SELECT
                    ar.source_type As IncomeType,
                    COALESCE(SUM(amount), 0) AS TotalRevenue,
                    COALESCE(
                        SUM(
                            CASE
                                WHEN amount_disbursed = 1 THEN amount
                                ELSE 0.0
                            END
                        ),
                    0) AS RevenueClaimed,
                    COALESCE(
                        SUM(
                            CASE
                                WHEN amount_disbursed = 0 THEN amount
                                ELSE 0.0
                            END
                        ),
                        0
                    ) AS RevenueNotClaimed
                FROM author_revenues ar
                WHERE ar.is_active = 1
                    AND ar.income_date >= :startDate
                    AND ar.income_date <= :endDate
                    AND (:isAllIncomeType = 1 OR ar.source_type = :incomeType)
                GROUP BY ar.source_type
            """;

    public static final String BOOKS_QUANTITY = """
                SELECT
                    ar.source_type AS IncomeType,
                    COALESCE(
                        SUM(
                            CASE
                                WHEN bb.quantity IS NOT NULL THEN bb.quantity
                                ELSE 0
                            END
                        ),
                        0
                    ) As BorrowBookCount,
                    COALESCE(
                        SUM(
                            CASE
                                WHEN pb.quantity IS NOT NULL THEN pb.quantity
                                ELSE 0
                            END
                        ),
                        0
                    ) AS PurchaseBookCount
                FROM author_revenues ar
                LEFT JOIN borrowed_books bb
                ON ar.source_type = 'BOOK_BORROW_FEE'
                    AND ar.source_id = bb.id
                LEFT JOIN purchased_books pb
                ON ar.source_type = 'BOOK_PURCHASE_FEE'
                    AND ar.source_id = pb.id
                WHERE ar.is_active = 1
                    AND ar.income_date >= :startDate
                    AND ar.income_date <= :endDate
                    AND (:isAllIncomeType = 1 OR ar.source_type = :incomeType)
                GROUP BY ar.source_type
            """;
}
