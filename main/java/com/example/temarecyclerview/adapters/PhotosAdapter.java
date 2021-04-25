package com.example.temarecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temarecyclerview.R;
import com.example.temarecyclerview.interfaces.OnItemClickListener;
import com.example.temarecyclerview.models.Photo;
import com.example.temarecyclerview.models.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    ArrayList<Photo> Photos;

    public PhotosAdapter(ArrayList<Photo> Photos)
    {
        this.Photos = Photos;
    }


    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_cell,parent,false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo Photo = Photos.get(position);
        holder.bind(Photo);
    }

    @Override
    public int getItemCount() {
        if(Photos == null)
            return 0;
        return Photos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private View view;
        public PhotoViewHolder(View view)
        {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.photoIV);
        }

        public void bind(Photo Photo)
        {
            Picasso.get().load(Photo.getUrl()).into(imageView);
        }

    }
}
