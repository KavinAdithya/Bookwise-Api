package com.techcrack.bookwise.applicationServices.admin.service;

import com.techcrack.bookwise.applicationServices.admin.abstraction.AdminService;
import com.techcrack.bookwise.applicationServices.admin.dto.response.AdminHomePageSummaryResponse;
import com.techcrack.bookwise.applicationServices.admin.mapper.AdminMapper;
import com.techcrack.bookwise.applicationServices.book.abstraction.BookService;
import com.techcrack.bookwise.applicationServices.book.repository.projection.BookCountProjection;
import com.techcrack.bookwise.applicationServices.user.abstraction.UserService;
import com.techcrack.bookwise.applicationServices.user.repository.queries.projection.UserCountProjection;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminServiceImpl extends AbstractLogger<AdminServiceImpl>
        implements AdminService {

    private final UserService userService;
    private final BookService bookService;
    private final AdminRevenueCalculationService adminRevenueCalculationService;
    private final AdminMapper mapper;

    public AdminServiceImpl(UserService userService,
                            BookService bookService,
                            AdminRevenueCalculationService adminRevenueCalculationService,
                            AdminMapper adminMapper) {
        super(AdminServiceImpl.class);
        this.userService = userService;
        this.bookService = bookService;
        this.adminRevenueCalculationService = adminRevenueCalculationService;
        this.mapper = adminMapper;
    }

    @Override
    @Cacheable
    public AdminHomePageSummaryResponse computeAdminPageSummary() {
        logger.info("Dashboard details computing started");

        UserCountProjection userCountProjection = userService.computeUserCounts();
        BookCountProjection bookCountProjection = bookService.getBookCountDetails();
        BigDecimal totalProfitOrLoss = adminRevenueCalculationService.computeAdminTotalRevenue();

        logger.info("Dashboard details computed");

        return mapper.mapToAdminHomePageSummaryResponse(userCountProjection, bookCountProjection, totalProfitOrLoss);
    }
}
