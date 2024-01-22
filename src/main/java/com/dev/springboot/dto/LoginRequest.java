package com.dev.springboot.dto;

public class LoginRequest {

    private String cin;

    public LoginRequest() {
    }

    public LoginRequest(String cin, String password) {
        this.cin = cin;
        this.password = password;
    }

    private String password;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
