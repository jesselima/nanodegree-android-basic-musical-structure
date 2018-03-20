package com.udacity.nanodegree.musicalstructure.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.nanodegree.musicalstructure.models.MusicListModel;
import com.udacity.nanodegree.musicalstructure.R;

import java.util.List;


public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context context;
    private List<MusicListModel> musicList;

    public MusicListAdapter(Context context, List<MusicListModel> musicList) {
        this.context = context;
        this.musicList = musicList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.id.setText(String.valueOf(musicList.get(position).getSongId()));
        holder.song.setText(musicList.get(position).getSongName());
        holder.artist.setText(musicList.get(position).getArtistName());
        holder.year.setText(String.valueOf(musicList.get(position).getSongYear()));
        //Glide.with(context).load(OBJECT_LIST.get(position).getImage_link()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView id, song, artist, year;
        //public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_view_song_id);
            song = itemView.findViewById(R.id.text_view_song_name);
            artist = itemView.findViewById(R.id.text_view_artist_name);
            year = itemView.findViewById(R.id.text_view_song_year);
            // imageView = itemView.findViewById(R.id.image);
        }
    }
}

