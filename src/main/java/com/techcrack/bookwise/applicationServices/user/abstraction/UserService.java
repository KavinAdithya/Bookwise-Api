package com.techcrack.bookwise.applicationServices.user.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.user.dto.password.request.PasswordResetRequest;
import com.techcrack.bookwise.applicationServices.notification.dto.request.SendOtpRequest;
import com.techcrack.bookwise.applicationServices.notification.dto.request.VerifyOtpRequest;
import com.techcrack.bookwise.applicationServices.user.dto.context.AuthenticationResult;
import com.techcrack.bookwise.applicationServices.user.dto.context.UserSubscriptionDetail;
import com.techcrack.bookwise.applicationServices.user.dto.response.AdminUserViewResponse;
import com.techcrack.bookwise.applicationServices.user.entity.Users;
import com.techcrack.bookwise.applicationServices.user.repository.queries.projection.UserCountProjection;

import java.util.List;

public interface UserService extends BasicCRUD<Users> {
    AuthenticationResult authenticate(String username, String password);
    Users getUser(String username);
    String verifyOtp(VerifyOtpRequest request);
    void sendOtp(SendOtpRequest request);
    void resetPassword(PasswordResetRequest request);
    List<AdminUserViewResponse> getAllUsers(int filterIsActive);
    UserSubscriptionDetail getUserWithSubscription(long userId);
    UserCountProjection computeUserCounts();
}
