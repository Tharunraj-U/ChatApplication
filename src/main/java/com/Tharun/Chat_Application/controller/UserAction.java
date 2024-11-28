package com.Tharun.Chat_Application.controller;

public class UserAction {
    private String username;
    private String type; // "JOIN" or "LEAVE"

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
