package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Subscriptions;
import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Subscriptions")
public class Subscription extends BaseEntity {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subscriptions subscriptions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    public Subscription() {
        super();
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
