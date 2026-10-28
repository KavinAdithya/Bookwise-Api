package com.techcrack.bookwise.applicationServices.admin.controller;

import com.techcrack.bookwise.applicationServices.admin.abstraction.AdminService;
import com.techcrack.bookwise.applicationServices.admin.dto.response.AdminHomePageSummaryResponse;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController extends AbstractLogger<AdminController> {
    private final AdminService service;

    public AdminController(AdminService adminService) {
        super(AdminController.class);
        this.service = adminService;
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<ApiResponseEntity<AdminHomePageSummaryResponse>> getAdminPageSummary() {
        logger.info("Request to compute admin dashboard");

        AdminHomePageSummaryResponse adminHomePageSummaryResponse = service.computeAdminPageSummary();

        logger.info("Request completed for admin dashboard");

        return ResponseEntityHelper
                .buildSuccessResponse("Admin Dashboard computed", adminHomePageSummaryResponse);
    }
}
