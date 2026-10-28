package com.techcrack.bookwise.applicationServices.user.dto.response;

import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.applicationServices.subscription.dto.response.SubscriptionDetails;

import java.time.LocalDateTime;

public record AdminUserDetailViewResponse(
        String name,
        Roles role,
        String username,
        String email,
        String address,
        String contact,
        LocalDateTime createdAt,
        SubscriptionDetails subscription) {
}
