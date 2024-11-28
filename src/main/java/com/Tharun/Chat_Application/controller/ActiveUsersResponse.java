package com.Tharun.Chat_Application.controller;

import lombok.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUsersResponse {
    private Set<String> users;
}