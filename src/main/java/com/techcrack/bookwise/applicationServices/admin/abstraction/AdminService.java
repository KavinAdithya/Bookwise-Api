package com.techcrack.bookwise.applicationServices.admin.abstraction;

import com.techcrack.bookwise.applicationServices.admin.dto.response.AdminHomePageSummaryResponse;

public interface AdminService {
    AdminHomePageSummaryResponse computeAdminPageSummary();
}
