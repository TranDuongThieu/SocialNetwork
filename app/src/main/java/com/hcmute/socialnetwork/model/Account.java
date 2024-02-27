package com.hcmute.socialnetwork.model;

public class Account {
    private String email;
    private String password;
    private String otp;
    public Account() {
    }

    public Account(String email, String password, String otp) {
        this.email = email;
        this.password = password;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


}
