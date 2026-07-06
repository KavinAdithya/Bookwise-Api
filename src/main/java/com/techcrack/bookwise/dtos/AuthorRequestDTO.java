package com.techcrack.bookwise.dtos;

import com.techcrack.bookwise.entity.Users;

public class AuthorRequestDTO {
    private String bio;

    private UserRegisterDTO userRegisterDTO;

    public AuthorRequestDTO(String bio, UserRegisterDTO userRegisterDTO) {
        this.bio = bio;
        this.userRegisterDTO = userRegisterDTO;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserRegisterDTO getUserRegisterDTO() {
        return userRegisterDTO;
    }

    public void setUserRegisterDTO(UserRegisterDTO userRegisterDTO) {
        this.userRegisterDTO = userRegisterDTO;
    }

    @Override
    public String toString() {
        return "AuthorRequestDTO{" +
                "bio='" + bio + '\'' +
                ", userRegisterDTO=" + userRegisterDTO +
                '}';
    }
}
