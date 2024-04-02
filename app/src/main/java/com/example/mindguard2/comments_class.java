package com.example.mindguard2;

public class comments_class {
    String name,comment;

    public comments_class() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public comments_class(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
}
