package com.brotech.Auth.common.payload;


import lombok.Getter;

@Getter
public final class Login {

    private final String email;
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
