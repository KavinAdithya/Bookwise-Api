package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Subscriptions;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subscriptions subscriptions;

    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    public Subscription() {
        super();
    }

    public void initialize() {
        this.isActive = true;
        this.createdAt = ApplicationData.SYSTEM_DATE;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
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
        if (!(o instanceof Subscription that)) return false;
        return isActive() == that.isActive() && Objects.equals(getId(), that.getId()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate()) && getSubscriptions() == that.getSubscriptions();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartDate(), getEndDate(), getSubscriptions(), isActive());
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptions=" + subscriptions +
                ", isActive=" + isActive +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
