package com.hcmute.socialnetwork.model;

import com.google.firebase.Timestamp;

public class Account {
    private String phone;
    private String email;

    private String password;
    private String otp;
    public Account(String phoneNumber, String password, String otp, Timestamp now) {
    }

    public Account() {
    }

    public Account(String phone, String password, String otp) {
        this.phone = phone;
        this.password = password;
        this.otp = otp;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
