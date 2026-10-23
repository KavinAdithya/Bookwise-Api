package com.techcrack.bookwise.constans.queries.sql;

public class AdminRevenueSqlQueries {
    public static final String ADMIN_TOTAL_REVENUE_INCOME_TYPE_BASED = """
                SELECT
                    ar.income_type AS incomeType,
                    COALESCE(SUM(amount), 0) AS totalIncome
                FROM AdminRevenues ar
                WHERE ar.is_active = 1 AND
                    ar.income_date >= :startDate AND
                    ar.income_date <= :endDate AND
                    (1 = :isAllIncomeType OR
                        ar.income_type = :incomeType)
                    GROUP BY ar.income_type
            """;
}
