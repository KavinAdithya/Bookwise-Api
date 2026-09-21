package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.passwordReset.PasswordResetRequest;
import com.techcrack.bookwise.dtos.passwordReset.SendOtpRequest;
import com.techcrack.bookwise.dtos.passwordReset.VerifyOtpRequest;
import com.techcrack.bookwise.dtos.user.context.AuthenticationResult;
import com.techcrack.bookwise.dtos.user.context.UserSubscriptionDetail;
import com.techcrack.bookwise.dtos.user.response.AdminUserViewResponse;
import com.techcrack.bookwise.entity.Users;

import java.util.List;

public interface UserService extends BasicCRUD<Users> {
    AuthenticationResult authenticate(String username, String password);
    Users getUser(String username);
    String verifyOtp(VerifyOtpRequest request);
    void sendOtp(SendOtpRequest request);
    void resetPassword(PasswordResetRequest request);
    List<AdminUserViewResponse> getAllUsers(int filterIsActive);
    UserSubscriptionDetail getUserWithSubscription(long userId);
}
