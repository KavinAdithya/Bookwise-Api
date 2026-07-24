package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    private String bio;

    @OneToOne
    @JoinColumn(unique = true)
    private Users user;

    public Author() {
        super();
    }

    public Author(String bio, Status status, Users user) {
        this.bio = bio;
        this.status = status;
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(getId(), author.getId()) && getStatus() == author.getStatus() && Objects.equals(getBio(), author.getBio()) && Objects.equals(getUser(), author.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getBio(), getUser());
    }

    @Override
    public String toString() {
        return "Author{" +
                "bio='" + bio + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
