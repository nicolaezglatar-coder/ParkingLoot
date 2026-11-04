package com.parking.parkinglot.common;

public class UserDto {
    private final String username;
    private final String email;

    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
