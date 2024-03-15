package com.hcmute.socialnetwork.model;

import com.google.firebase.Timestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {
    private String userId;


    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private int avatar;
    private ArrayList<String> blogs;
    private ArrayList<User> followers;
    private ArrayList<User> followings;
    private ArrayList<Notification> notifications;

    public User(String phoneNumber, String firstName, String lastName, String tran, Timestamp now, String number, int thumb1, Object o, Object o1, Object o2, Object o3) {
    }

    public User() {
    }

    public User(String userId, String email, String firstName, String lastName, String gender, LocalDate dateOfBirth, String phoneNumber, int avatar, ArrayList<String> blogs, ArrayList<User> followers, ArrayList<User> followings, ArrayList<Notification> notifications) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.blogs = blogs;
        this.followers = followers;
        this.followings = followings;
        this.notifications = notifications;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public ArrayList<String> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<String> blogs) {
        this.blogs = blogs;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<User> followings) {
        this.followings = followings;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public static class Notification {
        private String content;
        private LocalTime postedTime;

        public Notification(String content, LocalTime postedTime) {
            this.content = content;
            this.postedTime = postedTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalTime getPostedTime() {
            return postedTime;
        }

        public void setPostedTime(LocalTime postedTime) {
            this.postedTime = postedTime;
        }
    }
}
