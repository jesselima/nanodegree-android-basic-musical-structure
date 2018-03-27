package com.udacity.nanodegree.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MusicLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

        CardView cardRock = findViewById(R.id.card_rock);
        CardView cardElectronic = findViewById(R.id.card_electronic);
        CardView cardIndian = findViewById(R.id.card_indian);
        CardView cardRbSoul = findViewById(R.id.card_soul);
        CardView cardReggae = findViewById(R.id.card_reggae);
        CardView cardPop = findViewById(R.id.card_pop);
        Button btnAllSongs = findViewById(R.id.card_all_songs);

        cardRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = "Rock";
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListByGenreActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        });

        cardElectronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = "Electronic";
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListByGenreActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        });

        cardIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = "Indian";
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListByGenreActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        });

        cardRbSoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = "Soul";
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListByGenreActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        });

        cardReggae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = "Reggae";
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListByGenreActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        });

        cardPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = "Pop";
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListByGenreActivity.class);
                intent.putExtra("genre", genre);
                startActivity(intent);
            }
        });

        btnAllSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicLibraryActivity.this, MusicListActivity.class);
                startActivity(intent);
            }
        });
    }
}
