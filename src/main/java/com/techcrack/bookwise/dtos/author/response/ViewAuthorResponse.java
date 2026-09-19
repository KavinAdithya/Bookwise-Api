package com.techcrack.bookwise.dtos.author.response;

import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.entity.Author;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ViewAuthorResponse {
    private long id;
    private String authorName;
    private Status status;
    private String bio;
    private String username;
    private String email;
    private String contact;
    private String address;
    private LocalDateTime createdAt;

    public ViewAuthorResponse() {
        super();
    }

    public ViewAuthorResponse(Author author) {
        this.id = author.getId();
        this.authorName = author.getUser().getName();
        this.address = author.getUser().getAddress();
        this.bio = author.getBio();
        this.status = author.getStatus();
        this.contact = author.getUser().getContact();
        this.username = author.getUser().getUsername();
        this.createdAt = author.getCreatedAt();
        this.email = author.getUser().getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ViewAuthorResponse{" +
                "authorName='" + authorName + '\'' +
                ", status=" + status +
                ", bio='" + bio + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
