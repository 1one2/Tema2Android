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
import com.example.temarecyclerview.models.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {

    ArrayList<User>users;
    OnItemClickListener onItemClickListener;

    public MyAdapter(ArrayList<User> users, OnItemClickListener oicl)
    {
        this.users = users;
        onItemClickListener = oicl;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_cell,parent,false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView username;
        private ImageView arrow;
        private View view;
        public UserViewHolder(View view)
        {
            super(view);
            name = view.findViewById(R.id.userTV);
            username = view.findViewById(R.id.usernameTV);
            arrow = view.findViewById(R.id.arrow);
            this.view = view;
        }

        public void bind(User user)
        {
            name.setText(user.getName());
            username.setText(user.getUsername());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(user);
                }
            });
            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(arrow,user);
                }
            });
        }

    }
}
