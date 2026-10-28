package com.techcrack.bookwise.applicationServices.admin.dto.sqlProjector;

import java.math.BigDecimal;

public interface AdminRevenueIncomeBasedProjector {
    String getIncomeType();
    BigDecimal getTotalIncome();
}
