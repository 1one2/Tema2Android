package com.example.temarecyclerview;

import android.app.Application;

import java.io.Serializable;

public class ApplicationController extends Application implements Serializable {
    private static ApplicationController instance;
    private static final long serialVersionUID = 1L;
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }
    public static ApplicationController getInstance()
    {
        return instance;
    }

}
