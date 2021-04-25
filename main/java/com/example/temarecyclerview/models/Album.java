package com.example.temarecyclerview.models;

import java.io.Serializable;

public class Album implements Serializable {


    private static final long serialVersionUID = 1L;
    private int id;
    private String title;

    public Album(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
