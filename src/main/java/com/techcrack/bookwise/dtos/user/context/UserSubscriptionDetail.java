package com.techcrack.bookwise.dtos.user.context;

import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;

public record UserSubscriptionDetail(Users user, Subscription subscription) {
}
