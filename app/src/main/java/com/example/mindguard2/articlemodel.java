package com.example.mindguard2;

public class articlemodel {
    String Heading;
    String content;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public articlemodel(String heading, String content, String link) {
        Heading = heading;
        this.content = content;
        this.link = link;
    }

    String link;

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public articlemodel(String heading, String content) {
        Heading = heading;
        this.content = content;
    }
}
