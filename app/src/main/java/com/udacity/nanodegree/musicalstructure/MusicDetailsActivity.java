package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MusicDetailsActivity extends AppCompatActivity {

    static TextView textViewSong, textViewArtist, textViewYear, textViewGenres, textViewRhythm, textViewDuration, textViewAboutArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        textViewSong = findViewById(R.id.text_view_song_name);
        textViewArtist = findViewById(R.id.text_view_artist_name);
        textViewYear = findViewById(R.id.text_view_song_year);
        textViewGenres= findViewById(R.id.text_view_song_genres);
        textViewRhythm = findViewById(R.id.text_view_song_rhythm);
        textViewDuration = findViewById(R.id.text_view_song_duration);
        textViewAboutArtist = findViewById(R.id.text_view_about_artist);

        Bundle musicData = getIntent().getExtras();
        textViewSong.setText(musicData.getString("song"));
        textViewArtist.setText(musicData.getString("artist"));
        textViewYear.setText(String.valueOf(musicData.getInt("year")));
        textViewGenres.setText(musicData.getString("genres"));
        textViewRhythm.setText(musicData.getString("rhythm"));
        textViewDuration.setText(musicData.getString("duration"));
        textViewAboutArtist.setText(musicData.getString("aboutArtist"));
    }

}
