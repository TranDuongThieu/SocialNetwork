package com.hcmute.socialnetwork.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Blog {
    private String blogId;
    private int thumbnail;
    private ArrayList<String> video;
    private String description;
    private LocalDateTime postedAt;
    private String postedBy;
    private ArrayList<User> likes;
    private ArrayList<Comment> comments;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

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

    public ArrayList<User> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<User> likes) {
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

    public Blog(String blogId, int thumbnail, ArrayList<String> video, String description, LocalDateTime postedAt, String postedBy, ArrayList<User> likes, ArrayList<Comment> comments) {
        this.blogId = blogId;
        this.thumbnail = thumbnail;
        this.video = video;
        this.description = description;
        this.postedAt = postedAt;
        this.postedBy = postedBy;
        this.likes = likes;
        this.comments = comments;
    }


    public static class Comment {
        private User commentBy;
        private String content;
        private LocalDateTime commentedAt;
        private ArrayList<String> likeCmt;

        public Comment(User commentBy, String content, LocalDateTime commentedAt, ArrayList<String> likeCmt) {
            this.commentBy = commentBy;
            this.content = content;
            this.commentedAt = commentedAt;
            this.likeCmt = likeCmt;
        }

        public Comment() {
        }

        public User commentBy() {
            return commentBy;
        }

        public void setUserId(User commentBy) {
            this.commentBy = commentBy;
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
