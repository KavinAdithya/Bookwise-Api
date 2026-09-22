package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.dtos.passwordReset.PasswordResetRequest;
import com.techcrack.bookwise.dtos.passwordReset.SendOtpRequest;
import com.techcrack.bookwise.dtos.passwordReset.VerifyOtpRequest;
import com.techcrack.bookwise.dtos.passwordReset.VerifyOtpResponse;
import com.techcrack.bookwise.dtos.user.context.AuthenticationResult;
import com.techcrack.bookwise.dtos.user.context.UserSubscriptionDetail;
import com.techcrack.bookwise.dtos.user.response.*;
import com.techcrack.bookwise.dtos.user.request.UserAuthenticateRequest;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.service.UserRegistrationService;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.mapper.UserMapper;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController extends AbstractController<UserController, UserService, UserMapper> {
    private final UserRegistrationService userRegistrationService;


    public UserController(UserRegistrationService userRegistrationService,
                          UserService service,
                          UserMapper mapper,
                          CurrentUserService userSession) {
        super(UserController.class, service, mapper, userSession);
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<ApiResponseEntity<UserRegisterResponse>> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        logger.info("Request received to create a user with {}", userRegisterRequest.getUsername());

        Users user = mapper.mapToUser(userRegisterRequest);

        RegistrationResult<Users, Subscription> registrationResult = userRegistrationService.register(user, userRegisterRequest.getSubscription().getPlan());

        logger.info("Request completed for create user {}", user.getUsername());

        UserRegisterResponse response =  mapper.mapToUserResponse(
                    registrationResult.entity(),
                    registrationResult.relatedEntity()
                );

        return ResponseEntityHelper.buildSuccessResponse(
                "User created successfully",
                response
        );
    }

    @PostMapping("/users/login")
    public ResponseEntity<ApiResponseEntity<AuthenticatedResponse>> authenticateUser(@RequestBody UserAuthenticateRequest userAuthenticateRequest) {
        logger.info("Login Request received for {}", userAuthenticateRequest.getUsername());

        AuthenticationResult authenticationResult = service.authenticate(userAuthenticateRequest.getUsername(), userAuthenticateRequest.getPassword());

        logger.info("Login Request completed for {} Token generated : {}", userAuthenticateRequest.getPassword(), authenticationResult.token());
        AuthenticatedResponse response =  mapper.mapToJwtToken(authenticationResult);

        return ResponseEntityHelper.buildSuccessResponse(
            "Authentication success! Token Generated",
                    response
        );
    }

    @PostMapping("/users/send-otp")
    public ResponseEntity<ApiResponseEntity<String>> generateOtp(@RequestBody SendOtpRequest request) {

        logger.info("Request Received to send otp for {}", request);
        service.sendOtp(request);
        logger.info("Request completed for otp send {}", request);

        return ResponseEntityHelper
                .buildSuccessResponse("OTP Generated Successfully", "Please check your Email for otp");

    }

    @PostMapping("/users/verify-otp")
    public ResponseEntity<ApiResponseEntity<VerifyOtpResponse>> verifyOtp(@RequestBody VerifyOtpRequest request) {
        logger.info("OTP Verification started for request {}", request);

        String resetToken = service.verifyOtp(request);

        VerifyOtpResponse response = new VerifyOtpResponse(resetToken);

        logger.info("OTP Verification process completed successfully for {}", request);
        return ResponseEntityHelper
                 .buildSuccessResponse("OTP Verified Successfully", response);
    }

    @PostMapping("/users/password-reset")
    public ResponseEntity<ApiResponseEntity<Object>> passwordReset(@RequestBody PasswordResetRequest request) {
        logger.info("Request Received to reset password with {}", request);

        service.resetPassword(request);

        logger.info("Request Completed for reset password with {}", request);

        return ResponseEntityHelper
                .buildSuccessResponse("Password Reset Completed Successfully", null);
    }

    @GetMapping("/users/me")
    public ResponseEntity<ApiResponseEntity<AuthenticatedUserDetails>> getCurrentLoggerInUser() {
        logger.info("Request Received to fetch logged in user details");

        AuthenticatedUserDetails authenticatedUserDetails = new AuthenticatedUserDetails(
                userSession.getCurrentUser().getName(),
                userSession.getCurrentUser().getRole()
        );

        logger.info("Request completed to fetch logged in user details");

        return ResponseEntityHelper
                .buildSuccessResponse("User Detail fetched",  authenticatedUserDetails);

    }

    /**
     *
     * {@code @Param} filterIsActive = 2 Means both active and inactive
     * Filter IsActive = 1 Means active
     * Filter IsActive = 0 Means InActive
     */
    @GetMapping("/admin/users")
    public ResponseEntity<ApiResponseEntity<List<AdminUserViewResponse>>> getAllUsers(@RequestParam int filterIsActive) {
        logger.info("Request Received to fetch all users except author");

        List<AdminUserViewResponse> responses = service.getAllUsers(filterIsActive);

        logger.info("Request Completed to fetch all users");

        return ResponseEntityHelper
                .buildSuccessResponse("Users fetched successfully", responses);
    }

    @GetMapping("/admin/users/{userId}")
    public ResponseEntity<ApiResponseEntity<AdminUserDetailViewResponse>> getUserById(@PathVariable("userId") long userId) {
        logger.info("Request received from admin to fetch a user details of {} ", userId);

        UserSubscriptionDetail subscriptionDetail = service.getUserWithSubscription(userId);

        AdminUserDetailViewResponse response = mapper.mapToAdminUserDetailViewResponse(subscriptionDetail);

        logger.info("Request completed to fetch user {} from admin", userId);

        return ResponseEntityHelper
                .buildSuccessResponse("User Fetched Successfully",
                        response);
    }
}
