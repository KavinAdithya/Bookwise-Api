package com.techcrack.bookwise.dtos.user.request;

public class UserAuthenticateDTO {
    private String username;
    private String password;

    public UserAuthenticateDTO(String password, String username) {
        this.password = password;
        this.username = username;
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
}
