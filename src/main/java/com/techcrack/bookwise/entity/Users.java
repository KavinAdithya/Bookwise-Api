package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String address;
    @Column(unique = true, nullable = false)
    private String contact;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users users)) return false;
        return isActive() == users.isActive() && Objects.equals(getId(), users.getId()) && Objects.equals(getName(), users.getName()) && Objects.equals(getEmail(), users.getEmail()) && Objects.equals(getPassword(), users.getPassword()) && Objects.equals(getAddress(), users.getAddress()) && Objects.equals(getContact(), users.getContact()) && getRole() == users.getRole() && Objects.equals(getUsername(), users.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword(), getAddress(), getContact(), getRole(), isActive(), getUsername());
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                "address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
