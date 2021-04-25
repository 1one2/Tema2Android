package com.example.temarecyclerview.adapters;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temarecyclerview.R;
import com.example.temarecyclerview.interfaces.OnItemClickListener;
import com.example.temarecyclerview.models.Post;
import com.example.temarecyclerview.models.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    ArrayList<Post>posts;

    public PostAdapter(ArrayList<Post> posts)
    {
        this.posts = posts;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_cell,parent,false);
        PostViewHolder viewHolder = new PostViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        if(posts == null)
            return 0;
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView body;
        private View view;
        public PostViewHolder(View view)
        {
            super(view);
            this.title = view.findViewById(R.id.title);
            this.body = view.findViewById(R.id.body);
            this.view = view;
        }

        public void bind(Post post)
        {
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }

    }
}
