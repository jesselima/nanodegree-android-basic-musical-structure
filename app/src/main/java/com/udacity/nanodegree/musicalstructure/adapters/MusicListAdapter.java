package com.udacity.nanodegree.musicalstructure.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.nanodegree.musicalstructure.NowPlayingActivity;
import com.udacity.nanodegree.musicalstructure.models.MusicListModel;
import com.udacity.nanodegree.musicalstructure.R;

import java.util.List;

/**
 * The code in this class had the project below as a guide line to create a RecyclerView properly.
 * https://github.com/miskoajkula/CardViewPhpMysql.git
 */

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
        holder.genre.setText(musicList.get(position).getsongGenre());
        holder.year.setText(String.valueOf(musicList.get(position).getSongYear()));
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView id, song, artist, year, genre;
        private ImageView imageViewPlayButton, like, playlist;
        private ViewGroup listItemBlock;

        private ViewHolder(View itemView) {
            super(itemView);

            imageViewPlayButton = itemView.findViewById(R.id.btn_play);
            imageViewPlayButton.setOnClickListener(this);

            listItemBlock = itemView.findViewById(R.id.list_item_block);
            listItemBlock.setOnClickListener(this);

            genre = itemView.findViewById(R.id.text_view_music_genre);
            genre.setOnClickListener(this);

            id = itemView.findViewById(R.id.text_view_song_id);
            song = itemView.findViewById(R.id.text_view_song_name);
            artist = itemView.findViewById(R.id.text_view_artist_name);
            year = itemView.findViewById(R.id.text_view_song_year);
            like = itemView.findViewById(R.id.btn_like);
            playlist = itemView.findViewById(R.id.btn_add_to_playlist);

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, R.string.adde_to_your_favorites, Toast.LENGTH_SHORT).show();
                }
            });
            playlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, R.string.adde_to_your_library, Toast.LENGTH_SHORT).show();
                }
            });
        }

        /**
         * When a item in the list of songs is clicked this method get the items values such position, put then these values in a Intent object and and send then to NowPlayingActivity.
         *
         * @param itemView
         */
        @Override
        public void onClick(View itemView) {

            int position = getAdapterPosition();

            Intent intent = new Intent(itemView.getContext(), NowPlayingActivity.class);
                intent.putExtra("id", musicList.get(position).getSongId());
                intent.putExtra("song", musicList.get(position).getSongName());
                intent.putExtra("artist", musicList.get(position).getArtistName());
                intent.putExtra("urlArtistImage", musicList.get(position).getUrlArtistImage());
            itemView.getContext().startActivity(intent);
        }
    }
}

