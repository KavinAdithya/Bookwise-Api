package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionResponse;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
import com.techcrack.bookwise.dtos.user.response.UserRegisterResponse;
import com.techcrack.bookwise.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    public Users mapToUser(UserRegisterRequest source, Roles role) {
        Users user = new Users();

        user.setAddress(source.getAddress());
        user.setContact(source.getContact());
        user.setAddress(source.getAddress());
        user.setEmail(source.getEmail());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setName(source.getName());
        user.setRole(role);

        user.initialize(ApplicationData.HARD_CODED_CURRENT_ID);
        return user;
    }

    public UserRegisterResponse mapToUserResponse(Users user, SubscriptionResponse subscriptionResponse) {
        return new UserRegisterResponse(
                user.getAddress(),
                user.getContact(),
                user.getEmail(),
                user.getId(),
                user.getUsername(),
                subscriptionResponse
        );
    }
}
