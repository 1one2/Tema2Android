package com.example.temarecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.temarecyclerview.adapters.MyAdapter;
import com.example.temarecyclerview.adapters.PostAdapter;
import com.example.temarecyclerview.interfaces.OnItemClickListener;
import com.example.temarecyclerview.models.Post;
import com.example.temarecyclerview.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Post> posts = new ArrayList<Post>();
    MyAdapter adapter = new MyAdapter(users, new OnItemClickListener() {
        @Override
        public void onClick(User user) {//on cell click
            Toast.makeText(getApplicationContext(),"Clicked!!!" + user.getName(),Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this,SecondActivity.class);
            i.putExtra("User",user);
            startActivity(i);
        }

        @Override
        public void onClick(ImageView iv, User user) {

            //linearLayout.getChildCount()
            //linearLayout.removeAllViews()

            View parent = (View)iv.getParent();
            RecyclerView recyclerView = parent.findViewById(R.id.postsList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
           // recyclerView.setLayoutManager(linearLayoutManager);
                if(user.isShowing()){
                    user.setShowing(false);
                    Toast.makeText(getApplicationContext(),"IF", Toast.LENGTH_SHORT).show();
                    //PostAdapter postAdapter = new PostAdapter(new ArrayList<Post>());
                    //postAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(null);
                   // postAdapter.notifyDataSetChanged();
                }
                else {
                    PostAdapter postAdapter = new PostAdapter(user.getPostsList());
                    user.setShowing(true);
                    Toast.makeText(getApplicationContext(),"ELSE", Toast.LENGTH_SHORT).show();


                    recyclerView.setAdapter(null);
                    recyclerView.setLayoutManager(null);
                    recyclerView.setAdapter(postAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    postAdapter.notifyDataSetChanged();
                     }
        }

    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"App started",Toast.LENGTH_LONG).show();
        setUpRecyclerView();
        getUsers();
    }


    private void getUsers()
    {
        VolleyConfigSingleton volleyConfigSingleton = VolleyConfigSingleton.getInstance(getApplicationContext());
        RequestQueue queue = volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/users";
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
            User usr = new User(object.getInt("id"),object.getString("name"),object.getString("username"),getApplicationContext());

            users.add(usr);
        }
        adapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.userRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}