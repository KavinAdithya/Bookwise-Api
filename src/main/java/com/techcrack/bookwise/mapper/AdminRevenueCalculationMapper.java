package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.dtos.admin.sqlProjector.AdminRevenueIncomeBasedProjector;
import com.techcrack.bookwise.dtos.author.context.IncomeTypeBasedRevenue;
import com.techcrack.bookwise.dtos.author.sqlProjector.AuthorRevenueProjector;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.helper.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Component
public class AdminRevenueCalculationMapper {

    public IncomeTypeBasedRevenue mapToIncomeTypeBasedRevenue(IncomeType incomeType, LocalDateTime startDate, LocalDateTime endDate, AdminRevenueIncomeBasedProjector adminRevenueIncomeBasedProjector, AuthorRevenueProjector authorRevenueProjector, BigDecimal profitOrLoss) {
        return new IncomeTypeBasedRevenue(
                startDate,
                endDate,
                incomeType,
                adminRevenueIncomeBasedProjector.getTotalIncome(),
                authorRevenueProjector.getTotalRevenue(),
                authorRevenueProjector.getRevenueClaimed(),
                authorRevenueProjector.getRevenueNotClaimed(),
                profitOrLoss
        );
    }
}
