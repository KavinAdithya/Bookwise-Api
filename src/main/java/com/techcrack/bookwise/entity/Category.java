package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Categories")
public class Category extends BaseEntity {

    @Column(unique = true)
    private String name;

    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
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
                ", createAt=" + createdAt +
                '}';
    }
}