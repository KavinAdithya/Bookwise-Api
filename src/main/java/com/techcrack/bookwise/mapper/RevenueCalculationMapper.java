package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.dtos.admin.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.dtos.admin.context.AdminIncomeTypeBasedRevenue;
import com.techcrack.bookwise.dtos.author.context.AuthorIncomeTypeBasedRevenue;
import com.techcrack.bookwise.dtos.author.sqlProjector.AuthorRevenueProjector;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RevenueCalculationMapper {

    public AdminIncomeTypeBasedRevenue mapToAdminIncomeTypeBasedRevenue(IncomeType incomeType, AdminRevenueIncomeBasedProjector adminRevenueIncomeBasedProjector, AuthorRevenueProjector authorRevenueProjector, BigDecimal profitOrLoss) {
        return new AdminIncomeTypeBasedRevenue(
                incomeType,
                adminRevenueIncomeBasedProjector.getTotalIncome(),
                authorRevenueProjector== null ? BigDecimal.ZERO : authorRevenueProjector.getTotalRevenue(),
                authorRevenueProjector== null ? BigDecimal.ZERO : authorRevenueProjector.getRevenueClaimed(),
                authorRevenueProjector== null ? BigDecimal.ZERO : authorRevenueProjector.getRevenueNotClaimed(),
                profitOrLoss
        );
    }

    public AuthorIncomeTypeBasedRevenue mapToAuthorIncomeTypeBasedRevenue(IncomeType incomeType, AuthorRevenueProjector authorRevenueProjector) {
        assert authorRevenueProjector != null;

        return new AuthorIncomeTypeBasedRevenue(
                incomeType,
                authorRevenueProjector.getTotalRevenue(),
                authorRevenueProjector.getRevenueClaimed(),
                authorRevenueProjector.getRevenueNotClaimed()
        );
    }
}
