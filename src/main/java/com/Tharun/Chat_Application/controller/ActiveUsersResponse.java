package com.Tharun.Chat_Application.controller;

import java.util.Set;

public class ActiveUsersResponse {
    private Set<String> users;

    public ActiveUsersResponse(Set<String> users) {
        this.users = users;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
