package com.Tharun.Chat_Application.controller;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAction {
    private String username;
    private String type; // "JOIN" or "LEAVE"
}