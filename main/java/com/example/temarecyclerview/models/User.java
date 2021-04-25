package com.example.temarecyclerview.models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.temarecyclerview.MainActivity;
import com.example.temarecyclerview.VolleyConfigSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {


    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String username;
    private ArrayList<Post> posts;
    private Context context;
    private boolean showing;

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    public User(int id, String name, String username, Context context){
        this.id = id;
        this.name = name;
        this.username = "@" + username;
        this.context = context;
        posts = new ArrayList<Post>();
        showing = false;
        //posts.clear();
    }

    public ArrayList<Post> getPostsList() {

        getPosts(this,context);
        showing = true;
        return posts;
    }

    private void getPosts(User user, Context context)
    {
        VolleyConfigSingleton volleyConfigSingleton = VolleyConfigSingleton.getInstance(context);
        RequestQueue queue = volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/posts?userId=" + user.getId();

       // Toast.makeText(context,url,Toast.LENGTH_SHORT).show();
        StringRequest getPostsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    handlePostResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(getPostsRequest);
    }
    private void handlePostResponse(String response) throws JSONException {
        JSONArray postJSONArray = new JSONArray(response);
        for(int index = 0; index < postJSONArray.length();index++)
        {
            JSONObject object = postJSONArray.getJSONObject(index);
            Post post = new Post(object.getString("title"),object.getString("body"));

            posts.add(post);
        }

        //adapter.notifyDataSetChanged();
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
