package com.hcmute.socialnetwork.model;

import com.google.firebase.Timestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {
    private static User instance;
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    private String userId = ""; // Default value for String is an empty string
    private String email = "";
    private String userName = "";
    private String firstName = "";
    private String lastName = "";
    private String gender = "";
    private LocalDate dateOfBirth ; // Default value is current date
    private String phoneNumber = "";
    private int avatar = 0; // Default value for int is 0
    private ArrayList<Blog> blogs = new ArrayList<>(); // Default empty list
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> followings = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();

    public User(String phoneNumber, String firstName, String lastName, Timestamp now) {
    }

    public User() {
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<Blog> blogs) {
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

    public User(String userId, String email, String userName, String firstName, String lastName, String gender, LocalDate dateOfBirth, String phoneNumber, int avatar, ArrayList<Blog> blogs, ArrayList<User> followers, ArrayList<User> followings, ArrayList<Notification> notifications) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
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

    @Override
    public String toString() {
        return "CurrentUser{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber.toString() + '\'' +
                ", avatar=" + avatar +
                ", blogs=" + blogs.size() +
                ", followers=" + followers.size() +
                ", followings=" + followings.size() +
                ", notifications=" + notifications.size() +
                '}';
    }
}
