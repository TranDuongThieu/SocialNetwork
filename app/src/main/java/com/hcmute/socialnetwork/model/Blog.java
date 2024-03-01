package com.hcmute.socialnetwork.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Blog {
    private int thumbnail;
    private ArrayList<String> video;
    private String description;
    private LocalDateTime postedAt;
    private String postedBy;
    private ArrayList<String> likes;
    private ArrayList<Comment> comments;

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<String> getVideo() {
        return video;
    }

    public void setVideo(ArrayList<String> video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Blog() {
    }
    public Blog(int thumbnail, ArrayList<String> video, String description, LocalDateTime postedAt, String postedBy, ArrayList<String> likes, ArrayList<Comment> comments) {
        this.thumbnail = thumbnail;
        this.video = video;
        this.description = description;
        this.postedAt = postedAt;
        this.postedBy = postedBy;
        this.likes = likes;
        this.comments = comments;
    }



    public static class Comment{
        private String userId;
        private String content;
        private LocalDateTime commentedAt;
        private ArrayList<String> likeCmt;

        public Comment(String userId, String content, LocalDateTime commentedAt, ArrayList<String> likeCmt) {
            this.userId = userId;
            this.content = content;
            this.commentedAt = commentedAt;
            this.likeCmt = likeCmt;
        }

        public Comment() {
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getCommentedAt() {
            return commentedAt;
        }

        public void setCommentedAt(LocalDateTime commentedAt) {
            this.commentedAt = commentedAt;
        }

        public ArrayList<String> getLikeCmt() {
            return likeCmt;
        }

        public void setLikeCmt(ArrayList<String> likeCmt) {
            this.likeCmt = likeCmt;
        }
    }

}
