package com.hcmute.socialnetwork.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Blog {
    private String thumbnail;
    private String video;
    private String description;
    private LocalDateTime postedAt;
    private String postedBy;
    private ArrayList<String> likes;
    private ArrayList<Comment> comments;

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
