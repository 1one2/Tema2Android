package com.example.temarecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.temarecyclerview.adapters.PhotosAdapter;
import com.example.temarecyclerview.models.Album;
import com.example.temarecyclerview.models.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    ArrayList<Photo> photos = new ArrayList<Photo>();
    PhotosAdapter adapter = new PhotosAdapter(photos);
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        album = ((Album)getIntent().getSerializableExtra("Album"));
        setUpRecyclerView();
        getPhotos();
    }

    private void getPhotos()
    {
        VolleyConfigSingleton volleyConfigSingleton = VolleyConfigSingleton.getInstance(getApplicationContext());
        RequestQueue queue = volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/photos?albumId=" + album.getId();
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
            Photo usr = new Photo(object.getString("url"));

            photos.add(usr);
        }
        int numberOfPhotos = photos.size();
        photos.clear();
        for(int i = 1; i <= numberOfPhotos; i++)
        {
            photos.add(new Photo("https://picsum.photos/id/" + i + "/300"));
        }
        if(photos.size() > 0)
        Toast.makeText(getApplicationContext(),"number: " + photos.size(), Toast.LENGTH_SHORT).show();
        else

            Toast.makeText(getApplicationContext(),"Nu avem photos", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }
    private void setUpRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.photosRV);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}