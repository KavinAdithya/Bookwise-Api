package com.techcrack.bookwise.applicationServices.admin.repository;

import com.techcrack.bookwise.applicationServices.admin.dto.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.applicationServices.admin.entity.AdminRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.applicationServices.admin.repository.queries.AdminRevenueSqlQueries.ADMIN_TOTAL_REVENUE_INCOME_TYPE_BASED;

public interface AdminRevenueRepository extends JpaRepository<AdminRevenue, Long> {
    @Query(value = ADMIN_TOTAL_REVENUE_INCOME_TYPE_BASED, nativeQuery = true)
    List<AdminRevenueIncomeBasedProjector> calculateTotalAdminRevenue(LocalDateTime startDate, LocalDateTime endDate, boolean isAllIncomeType, String incomeType);
}
