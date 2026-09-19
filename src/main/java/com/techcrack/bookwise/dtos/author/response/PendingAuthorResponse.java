package com.techcrack.bookwise.dtos.author.response;

import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.entity.Author;

public class PendingAuthorResponse {
    private long authorId;
    private String authorName;
    private String bio;
    private String username;
    private String email;
    private Status status;

    public PendingAuthorResponse(long authorId, String authorName, String bio, String username, String email) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.bio = bio;
        this.username = username;
        this.email = email;
    }

    public PendingAuthorResponse(Author author) {
        this.authorId = author.getId();
        this.authorName = author.getUser().getName();
        this.bio = author.getBio();
        this.username = author.getUser().getUsername();
        this.email = author.getUser().getEmail();
        this.status = author.getStatus();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PendingAuthorDTO{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", bio='" + bio + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
