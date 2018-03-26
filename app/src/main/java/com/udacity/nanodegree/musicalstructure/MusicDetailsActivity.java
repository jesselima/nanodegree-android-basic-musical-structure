package com.udacity.nanodegree.musicalstructure;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MusicDetailsActivity extends AppCompatActivity {

    TextView textViewSong, textViewArtist, textViewYear, textViewGenres, textViewRhythm, textViewDuration, textViewAboutArtist;
    ImageView textViewArtistImage, imageViewFacebook;
    String urlArtistImage, urlFacebookPage;

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
        textViewArtistImage = findViewById(R.id.image_view_artist);
        imageViewFacebook = findViewById(R.id.image_facebook);

        Bundle musicData = getIntent().getExtras();
        textViewSong.setText(musicData.getString("song"));
        textViewArtist.setText(musicData.getString("artist"));
        textViewYear.setText(String.valueOf(musicData.getInt("year")));
        textViewGenres.setText(musicData.getString("genres"));
        textViewRhythm.setText(musicData.getString("rhythm"));
        textViewDuration.setText(musicData.getString("duration"));
        textViewAboutArtist.setText(musicData.getString("aboutArtist"));

        urlArtistImage = (musicData.getString("urlArtistImage"));
        Glide.with(this)
                .load(urlArtistImage)
                .into(textViewArtistImage);


        urlFacebookPage = (musicData.getString("urlFacebookPage"));
        imageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(urlFacebookPage);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }




}
