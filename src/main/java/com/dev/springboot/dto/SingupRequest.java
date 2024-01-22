package com.dev.springboot.dto;

public class SingupRequest {


    private  String email;

    private  String name;

    public SingupRequest() {
    }

    public SingupRequest(String email, String name, String password, String cin) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    private  String password;

    private  String cin;
}
