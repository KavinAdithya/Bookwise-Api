package com.techcrack.bookwise.constans.queries.sql;

public class AuthorRevenueSqlQueries {
    public static final String TOTAL_AUTHOR_REVENUE = """
                SELECT
                    ar.incomeType As IncomeType,
                    COALESCE(SUM(amount), 0) AS TotalRevenue,
                    COALESCE(
                        SUM(
                            CASE
                                WHEN amountDisbursed = 1 THEN amount
                                ELSE 0.0
                            END
                        ),
                    0) AS RevenueClaimed,
                    COALESCE(
                        SUM(
                            CASE
                                WHEN amountDisbursed = 0 THEN amount
                                ELSE 0.0
                            END
                        ),
                        0
                    ) AS RevenueNotClaimed
                FROM AuthorRevenues ar
                WHERE ar.is_active = 1
                    AND ar.income_date >= :startDate
                    AND ar.income_date <= :endDate
                    AND (:isAllIncomeType = 1 OR ar.income_type = :incomeType)
                GROUP BY ar.income_type
            """;
}
