package com.example.demo.dto;

public class AccountResponseDTO {
    private Long id;
    private String username;
    private String role;

    public  Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String password) {
        this.role = password;
    }
}
