package com.techcrack.bookwise.applicationServices.admin.mapper;

import com.techcrack.bookwise.applicationServices.admin.dto.response.AdminHomePageSummaryResponse;
import com.techcrack.bookwise.applicationServices.book.repository.projection.BookCountProjection;
import com.techcrack.bookwise.applicationServices.user.repository.queries.projection.UserCountProjection;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AdminMapper {
    public AdminHomePageSummaryResponse mapToAdminHomePageSummaryResponse(
            UserCountProjection userCountProjection,
            BookCountProjection bookCountProjection,
            BigDecimal totalProfitOrLoss
    ) {
        return new AdminHomePageSummaryResponse(
                userCountProjection.getTotalUsers(),
                bookCountProjection.getTotalBooks(),
                bookCountProjection.getTotalApprovedBooks(),
                bookCountProjection.getTotalPendingBooks(),
                bookCountProjection.getTotalRejectedBooks(),
                userCountProjection.getTotalAuthors(),
                totalProfitOrLoss
        );
    }
}
