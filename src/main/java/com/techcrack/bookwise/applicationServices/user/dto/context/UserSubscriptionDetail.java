package com.techcrack.bookwise.applicationServices.user.dto.context;

import com.techcrack.bookwise.applicationServices.subscription.entity.Subscription;
import com.techcrack.bookwise.applicationServices.user.entity.Users;

public record UserSubscriptionDetail(Users user, Subscription subscription) {
}
