package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.dtos.admin.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.entity.AdminRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.constans.queries.sql.AdminRevenueSqlQueries.ADMIN_TOTAL_REVENUE_INCOME_TYPE_BASED;

public interface AdminRevenueRepository extends JpaRepository<AdminRevenue, Long> {
    @Query(value = ADMIN_TOTAL_REVENUE_INCOME_TYPE_BASED, nativeQuery = true)
    List<AdminRevenueIncomeBasedProjector> calculateTotalAdminRevenue(LocalDateTime startDate, LocalDateTime endDate, boolean isAllIncomeType, String incomeType);
}
