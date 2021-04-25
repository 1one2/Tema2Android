package com.example.temarecyclerview.interfaces;

import android.app.ListActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.temarecyclerview.models.Album;
import com.example.temarecyclerview.models.User;

public interface OnItemClickListener {
    public void onClick(User user);
    public void onClick(ImageView iv, User user);

}
