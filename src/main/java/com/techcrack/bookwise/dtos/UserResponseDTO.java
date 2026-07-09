package com.techcrack.bookwise.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "username",
        "email",
        "contact",
        "address",
        "subscription"
})
public class UserResponseDTO {
    private Long id;
    private String email;
    private String username;
    private String address;
    private String contact;
    private SubscriptionResponseDTO subscription;

    public UserResponseDTO(String address, String contact, String email, Long id, String username, SubscriptionResponseDTO subscription) {
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.id = id;
        this.username = username;
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

    public SubscriptionResponseDTO getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionResponseDTO subscription) {
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
