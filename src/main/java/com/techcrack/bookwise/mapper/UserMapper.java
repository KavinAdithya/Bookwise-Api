package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.dtos.subscription.response.SubscriptionDetails;
import com.techcrack.bookwise.dtos.user.context.AuthenticationResult;
import com.techcrack.bookwise.dtos.user.context.UserSubscriptionDetail;
import com.techcrack.bookwise.dtos.user.response.AdminUserDetailViewResponse;
import com.techcrack.bookwise.dtos.user.response.AuthenticatedResponse;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
import com.techcrack.bookwise.dtos.user.response.AuthenticatedUserDetails;
import com.techcrack.bookwise.dtos.user.response.UserRegisterResponse;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public Users mapToUser(UserRegisterRequest source) {
       return source.buildUser();
    }

    public UserRegisterResponse mapToUserResponse(Users user, Subscription subscription) {
        return new UserRegisterResponse(
                user,
                subscription
        );
    }

    public AuthenticatedResponse mapToJwtToken(AuthenticationResult authenticationResult) {
        return new AuthenticatedResponse(
                    new AuthenticatedUserDetails(
                            authenticationResult.userPrincipal().getName(),
                            authenticationResult.userPrincipal().getRole()
                    ),
                authenticationResult.token());
    }

    public AdminUserDetailViewResponse mapToAdminUserDetailViewResponse(UserSubscriptionDetail source) {
        return new AdminUserDetailViewResponse(
                source.user().getName(),
                source.user().getRole(),
                source.user().getUsername(),
                source.user().getEmail(),
                source.user().getAddress(),
                source.user().getContact(),
                source.user().getCreatedAt(),
                new SubscriptionDetails(
                        source.subscription().getId(),
                        source.subscription().getSubscriptions(),
                        source.subscription().getStartDate(),
                        source.subscription().getEndDate()
                )
        );
    }
}
