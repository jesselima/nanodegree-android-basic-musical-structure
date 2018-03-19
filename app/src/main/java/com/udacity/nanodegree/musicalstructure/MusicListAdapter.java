package com.udacity.nanodegree.musicalstructure;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        holder.songId.setText(musicList.get(position).getSongId());
        holder.songName.setText(musicList.get(position).getSongName());
        holder.artistName.setText(musicList.get(position).getArtistName());
        holder.songYear.setText(musicList.get(position).getSongYear());
        //Glide.with(context).load(OBJECT_LIST.get(position).getImage_link()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView songId, songName, artistName, songYear;
        //public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            songId = itemView.findViewById(R.id.text_view_song_id);
            songName = itemView.findViewById(R.id.text_view_song_name);
            artistName = itemView.findViewById(R.id.text_view_artist_name);
            songYear = itemView.findViewById(R.id.text_view_song_year);
            // imageView = itemView.findViewById(R.id.image);
        }
    }
}

