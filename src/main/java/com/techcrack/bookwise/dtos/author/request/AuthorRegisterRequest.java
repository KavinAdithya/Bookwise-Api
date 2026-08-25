package com.techcrack.bookwise.dtos.author.request;

import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.user.request.UserRegisterRequest;
import com.techcrack.bookwise.entity.Author;

public class AuthorRegisterRequest {
    private String bio;

    private UserRegisterRequest user;

    public AuthorRegisterRequest(String bio, UserRegisterRequest user) {
        this.bio = bio;
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserRegisterRequest getUser() {
        return user;
    }

    public void setUser(UserRegisterRequest user) {
        this.user = user;
    }

    public Author buildAuthor() {
        Author author = new Author();

        author.setBio(bio);
        author.setUser(
            user.buildUser()
        );

        author.initialize(null);

        author.setStatus(Status.PENDING);

        return author;
    }


    @Override
    public String toString() {
        return "AuthorRequestDTO{" +
                "bio='" + bio + '\'' +
                ", userRegisterDTO=" + user +
                '}';
    }
}
