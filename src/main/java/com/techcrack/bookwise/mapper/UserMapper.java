package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.dtos.user.context.AuthenticationResult;
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
}
