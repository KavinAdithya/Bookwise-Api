package com.techcrack.bookwise.applicationServices.admin.repository.queries;

public class AdminRevenueSqlQueries {
    public static final String ADMIN_TOTAL_REVENUE_INCOME_TYPE_BASED = """
                SELECT
                    ar.source_type AS incomeType,
                    COALESCE(SUM(amount), 0) AS totalIncome
                FROM admin_revenues ar
                WHERE ar.is_active = 1 AND
                    ar.income_date >= :startDate AND
                    ar.income_date <= :endDate AND
                    (1 = :isAllIncomeType OR
                        ar.source_type = :incomeType)
                    GROUP BY ar.source_type
            """;
}
