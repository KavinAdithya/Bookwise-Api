package com.techcrack.bookwise.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime createAt;
    @ManyToOne
    private Users createdBy;
    private boolean isActive;

    public Category() {
        super();
    }

    public Category(String name, Users createdBy, LocalDateTime createAt) {
        this.name = name;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.isActive  = true;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return isActive() == category.isActive() && Objects.equals(getId(), category.getId()) && Objects.equals(getName(), category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), isActive());
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                ", createdBy=" + createdBy +
                ", createAt=" + createAt +
                '}';
    }
}