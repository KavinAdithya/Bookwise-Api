package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.dtos.user.response.JwtTokenResponse;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
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

    public JwtTokenResponse mapToJwtToken(String token) {
        return new JwtTokenResponse(token);
    }
}
