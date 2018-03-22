package com.udacity.nanodegree.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MusicDetailsActivity extends AppCompatActivity {

    int idSong;

    TextView textViewSong, textViewArtist, textViewYear, textViewGenres, textViewRhythm, textViewDuration;
    String urlSound, urlArtistImage;

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

        Bundle musicData = getIntent().getExtras();
        idSong = musicData.getInt("year");
        urlSound = musicData.getString("urlSound");
        urlArtistImage = musicData.getString("urlArtistImage");

        textViewSong.setText(musicData.getString("song"));
        textViewArtist.setText(musicData.getString("artist"));
        textViewYear.setText(String.valueOf(musicData.getInt("year")));
        textViewGenres.setText(musicData.getString("genres"));
        textViewRhythm.setText(musicData.getString("rhythm"));
        textViewDuration.setText(musicData.getString("duration"));
    }

    public void goToPlayNow(View view){
        Intent intent = new Intent(this, NowPlayingActivity.class);

        startActivity(intent);
    }
}
