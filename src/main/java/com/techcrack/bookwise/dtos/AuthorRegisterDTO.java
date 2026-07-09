package com.techcrack.bookwise.dtos;

public class AuthorRegisterDTO {
    private String bio;

    private UserRegisterDTO user;

    public AuthorRegisterDTO(String bio, UserRegisterDTO user) {
        this.bio = bio;
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserRegisterDTO getUser() {
        return user;
    }

    public void setUser(UserRegisterDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthorRequestDTO{" +
                "bio='" + bio + '\'' +
                ", userRegisterDTO=" + user +
                '}';
    }
}
