package com.techcrack.bookwise.dtos.user.response;

import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionDetails;

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
