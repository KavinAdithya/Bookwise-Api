package com.techcrack.bookwise.utils.dtoMapper;

import com.techcrack.bookwise.dtos.SubscriptionResponseDTO;
import com.techcrack.bookwise.dtos.UserRegisterDTO;
import com.techcrack.bookwise.dtos.UserResponseDTO;
import com.techcrack.bookwise.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    public Users mapToUser(UserRegisterDTO source) {
        Users user = new Users();

        user.setAddress(source.getAddress());
        user.setContact(source.getContact());
        user.setAddress(source.getAddress());
        user.setEmail(source.getEmail());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setName(source.getName());
        user.setRole(source.getRole());

        return user;
    }

    public UserResponseDTO mapToUserResponse(Users user, SubscriptionResponseDTO subscriptionResponseDTO) {
        return new UserResponseDTO(
                user.getAddress(),
                user.getContact(),
                user.getEmail(),
                user.getId(),
                user.getUsername(),
                subscriptionResponseDTO
        );
    }
}
