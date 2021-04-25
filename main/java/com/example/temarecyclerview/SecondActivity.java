package com.example.temarecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.temarecyclerview.adapters.AlbumAdapter;
import com.example.temarecyclerview.interfaces.OnAlbumClickListener;
import com.example.temarecyclerview.models.Album;
import com.example.temarecyclerview.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    private ArrayList<Album> albums = new ArrayList<Album>();
    private User user;
    AlbumAdapter adapter = new AlbumAdapter(albums, new OnAlbumClickListener() {
        @Override
        public void onClick(Album album) {
            //start 3rd activity
            Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
            i.putExtra("Album",album);
            startActivity(i);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        user = ((User) getIntent().getSerializableExtra("User"));
        Toast.makeText(getApplicationContext(),user.getName(),Toast.LENGTH_SHORT).show();
        setUpRecyclerView();
        getAlbums();
    }
    private void getAlbums()
    {
        VolleyConfigSingleton volleyConfigSingleton = VolleyConfigSingleton.getInstance(getApplicationContext());
        RequestQueue queue = volleyConfigSingleton.getRequestQueue();
            String url = "https://jsonplaceholder.typicode.com/albums?userId=" + user.getId();
        StringRequest getPostsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    handleUserResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(getPostsRequest);
    }

    private void handleUserResponse(String response) throws JSONException {
        JSONArray postJSONArray = new JSONArray(response);
        for(int index = 0; index < postJSONArray.length();index++)
        {
            JSONObject object = postJSONArray.getJSONObject(index);
            Album usr = new Album(object.getInt("id"),object.getString("title"));

            albums.add(usr);
        }
        adapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.albumRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}