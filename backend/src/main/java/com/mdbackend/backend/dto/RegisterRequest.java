package com.mdbackend.backend.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String photoProfile;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }
    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }
}
