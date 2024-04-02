package com.example.mindguard2;

import java.io.Serializable;

public class tweets implements Serializable {
    String tweet,name,like,comments,uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public tweets(String tweet, String name, String like, String comments, String uid) {
        this.tweet = tweet;
        this.name = name;
        this.like = like;
        this.comments = comments;
        this.uid = uid;
    }

    public tweets() {
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public tweets(String tweet, String name, String like, String comments) {
        this.tweet = tweet;
        this.name = name;
        this.like = like;
        this.comments = comments;
    }
}
