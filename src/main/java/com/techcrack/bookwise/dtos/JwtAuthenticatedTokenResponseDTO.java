package com.techcrack.bookwise.dtos;

public class JwtAuthenticatedTokenResponseDTO {
    private final String token;

    public JwtAuthenticatedTokenResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
