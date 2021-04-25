package com.example.temarecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temarecyclerview.R;
import com.example.temarecyclerview.interfaces.OnAlbumClickListener;
import com.example.temarecyclerview.interfaces.OnItemClickListener;
import com.example.temarecyclerview.models.Album;
import com.example.temarecyclerview.models.Post;

import java.util.ArrayList;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    ArrayList<Album> albums;
    OnAlbumClickListener oicl;

    public AlbumAdapter(ArrayList<Album> albums, OnAlbumClickListener oicl)
    {
        this.albums = albums;
        this.oicl = oicl;
    }


    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.album_cell,parent,false);
        AlbumViewHolder viewHolder = new AlbumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.bind(album);
    }

    @Override
    public int getItemCount() {
        if(albums == null)
            return 0;
        return albums.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private View view;
        public AlbumViewHolder(View view)
        {
            super(view);
            this.title = view.findViewById(R.id.titleAlbum);
            this.view = view;
        }

        public void bind(Album album)
        {
            title.setText(album.getTitle());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oicl.onClick(album);
                }
            });
        }

    }
}
