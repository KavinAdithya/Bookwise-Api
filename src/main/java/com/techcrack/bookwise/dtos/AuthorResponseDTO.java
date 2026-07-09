package com.techcrack.bookwise.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techcrack.bookwise.constans.Status;


@JsonPropertyOrder({
        "id",
        "bio",
        "status",
        "user"
})
public class AuthorResponseDTO {
    private Long id;
    private String bio;
    private UserResponseDTO user;
    private Status status;

    public AuthorResponseDTO(Long id, String bio, UserResponseDTO user, Status status) {
        this.id = id;
        this.bio = bio;
        this.user = user;
        this.status = status;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthorResponseDTO{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}
