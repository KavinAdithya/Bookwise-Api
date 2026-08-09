package com.techcrack.bookwise.dtos.user.request;

import com.techcrack.bookwise.dtos.subscription.request.SubscriptionRegisterDTO;
import com.techcrack.bookwise.entity.Users;

public class UserRegisterRequest {
    private String name;
    private String email;
    private String username;
    private String password;
    private String address;
    private String contact;
    private SubscriptionRegisterDTO subscription;

    public UserRegisterRequest(String address, String contact, String email, String name, String password, String username) {
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public SubscriptionRegisterDTO getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionRegisterDTO subscription) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Users buildUser() {
        Users users = new Users();
        users.initialize(null);
        users.setUsername(username);
        users.setPassword(password);
        users.setName(name);
        users.setEmail(email);
        users.setContact(contact);
        users.setAddress(address);
        return users;
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
                ", subscription=" + subscription +
                '}';
    }
}
