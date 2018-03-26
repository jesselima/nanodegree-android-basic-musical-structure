package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MusicListByGenreActivity extends AppCompatActivity {

    String genre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_by_genre);

        Bundle musicData = getIntent().getExtras();
            genre = musicData.getString("genre");
        Toast.makeText(MusicListByGenreActivity.this, genre, Toast.LENGTH_SHORT).show();

    }
}
