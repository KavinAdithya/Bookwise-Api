package com.techcrack.bookwise.dtos.user.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionResponse;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;

@JsonPropertyOrder({
        "id",
        "username",
        "email",
        "contact",
        "address",
        "subscription"
})
public class UserRegisterResponse {
    private Long id;
    private String email;
    private String username;
    private String address;
    private String contact;
    private SubscriptionResponse subscription;

    public UserRegisterResponse(String address, String contact, String email, Long id, String username, SubscriptionResponse subscription) {
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.id = id;
        this.username = username;
        this.subscription = subscription;
    }

    public UserRegisterResponse(Users users, Subscription subscription) {
        this.username = users.getUsername();
        this.id = users.getId();
        this.email = users.getEmail();
        this.contact = users.getContact();
        this.address = users.getAddress();
        this.subscription = new SubscriptionResponse(subscription);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SubscriptionResponse getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionResponse subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", subscription='" + subscription + '\'' +
                '}';
    }
}
