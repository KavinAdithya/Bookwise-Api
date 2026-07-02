package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.Roles;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String contact;
    private Roles role;
    private boolean isActive;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "SubscriptionId")
    private Subscription subscription;

    public Users() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users users)) return false;
        return isActive() == users.isActive() && Objects.equals(getId(), users.getId()) && Objects.equals(getName(), users.getName()) && Objects.equals(getEmail(), users.getEmail()) && Objects.equals(getPassword(), users.getPassword()) && Objects.equals(getAddress(), users.getAddress()) && Objects.equals(getContact(), users.getContact()) && getRole() == users.getRole() && Objects.equals(getSubscription(), users.getSubscription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword(), getAddress(), getContact(), getRole(), isActive(), getSubscription());
    }

    @Override
    public String toString() {
        return "Users{" +
                "address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", subscription=" + subscription +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
