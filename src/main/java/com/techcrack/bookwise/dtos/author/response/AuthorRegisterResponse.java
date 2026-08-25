package com.techcrack.bookwise.dtos.author.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.user.response.UserRegisterResponse;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;


@JsonPropertyOrder({
        "id",
        "bio",
        "status",
        "user"
})
public class AuthorRegisterResponse {
    private Long id;
    private String bio;
    private UserRegisterResponse user;
    private Status status;

    public AuthorRegisterResponse(Long id, String bio, UserRegisterResponse user, Status status) {
        this.id = id;
        this.bio = bio;
        this.user = user;
        this.status = status;
    }

    public AuthorRegisterResponse(Author author, Subscription subscription) {
        this.id = author.getId();
        this.bio = author.getBio();
        this.status = author.getStatus();
        this.user = new UserRegisterResponse(author.getUser(), subscription);
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

    public UserRegisterResponse getUser() {
        return user;
    }

    public void setUser(UserRegisterResponse user) {
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
