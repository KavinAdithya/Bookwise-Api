package com.techcrack.bookwise.dtos;

public class PendingAuthorDTO {
    private long authorId;
    private String authorName;
    private String bio;
    private String username;
    private String email;

    public PendingAuthorDTO(long authorId, String authorName, String bio, String username, String email) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.bio = bio;
        this.username = username;
        this.email = email;
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
