package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.dtos.admin.context.AdminRevenueSummary;
import com.techcrack.bookwise.mapper.RevenueCalculationMapper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.service.admin.AdminRevenueCalculationService;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class AdminRevenueController extends AbstractController<AdminRevenueController, AdminRevenueCalculationService, RevenueCalculationMapper> {

    public AdminRevenueController(AdminRevenueCalculationService adminRevenueCalculationService, RevenueCalculationMapper adminRevenueCalculationMapper, CurrentUserService userSession) {
        super(AdminRevenueController.class, adminRevenueCalculationService, adminRevenueCalculationMapper, userSession);
    }

    @GetMapping("/admin-revenue/summary")
    public ResponseEntity<ApiResponseEntity<AdminRevenueSummary>> computeAdminRevenueDashboardDetails(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate,
            @RequestParam("incomeType") IncomeType incomeType) {

        logger.info("Request received to fetch dashboard details for admin with income type {}", incomeType.name());

        AdminRevenueSummary revenueSummary = service.getRevenueBasedOnIncomeType(startDate, endDate, incomeType);

        logger.info("Request completed to fetch all dashboard details");

        return ResponseEntityHelper
                .buildSuccessResponse("Admin Revenue Summary Computed", revenueSummary);
    }
}
