package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.dtos.admin.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.dtos.author.context.IncomeTypeBasedRevenue;
import com.techcrack.bookwise.dtos.author.sqlProjector.AuthorRevenueProjector;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class AdminRevenueCalculationMapper {

    public IncomeTypeBasedRevenue mapToIncomeTypeBasedRevenue(IncomeType incomeType, AdminRevenueIncomeBasedProjector adminRevenueIncomeBasedProjector, AuthorRevenueProjector authorRevenueProjector, BigDecimal profitOrLoss) {
        return new IncomeTypeBasedRevenue(
                incomeType,
                adminRevenueIncomeBasedProjector.getTotalIncome(),
                authorRevenueProjector== null ? BigDecimal.ZERO : authorRevenueProjector.getTotalRevenue(),
                authorRevenueProjector== null ? BigDecimal.ZERO : authorRevenueProjector.getRevenueClaimed(),
                authorRevenueProjector== null ? BigDecimal.ZERO : authorRevenueProjector.getRevenueNotClaimed(),
                profitOrLoss
        );
    }
}
