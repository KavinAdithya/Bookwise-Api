package com.techcrack.bookwise.dtos;

import com.techcrack.bookwise.constans.Roles;
import com.techcrack.bookwise.entity.Subscription;

public class UserRegisterDTO {
    private String name;
    private String email;
    private String username;
    private String password;
    private String address;
    private String contact;
    private Roles role;
    private SubscriptionDTO subscription;

    public UserRegisterDTO(String address, String contact, String email, String name, String password, Roles role, String username) {
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public SubscriptionDTO getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionDTO subscription) {
        this.subscription = subscription;
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
    public String toString() {
        return "UserCreateDTO{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", role=" + role + '\'' +
                ", subscription=" + subscription +
                '}';
    }
}
