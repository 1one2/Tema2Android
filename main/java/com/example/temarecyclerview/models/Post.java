package com.example.temarecyclerview.models;

public class Post {
    private String title;
    private String body;

    public Post(String title,String body)
    {
        this.title = title;
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }
}
