package com.hcmute.socialnetwork.helper;

import android.util.Patterns;

public class Validate {

    public static boolean isEmailValid(String email) {
        // Using Android's built-in Patterns class to validate email
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        // You can add your own password validation logic here
        // For example, checking if the password has a minimum length
        return password.length() >= 6;
    }
}
