package com.techcrack.bookwise.dtos;


public class UserResponseDTO {
    private Long id;
    private String email;
    private String username;
    private String address;
    private String contact;
    private SubscriptionResponseDTO subscriptionResponseDTO;

    public UserResponseDTO(String address, String contact, String email, Long id, String username, SubscriptionResponseDTO subscriptionResponseDTO) {
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.id = id;
        this.username = username;
        this.subscriptionResponseDTO = subscriptionResponseDTO;
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

    public SubscriptionResponseDTO getSubscriptionResponseDTO() {
        return subscriptionResponseDTO;
    }

    public void setSubscriptionResponseDTO(SubscriptionResponseDTO subscriptionResponseDTO) {
        this.subscriptionResponseDTO = subscriptionResponseDTO;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", subscription='" + subscriptionResponseDTO + '\'' +
                '}';
    }
}
