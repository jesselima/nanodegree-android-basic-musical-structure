package com.udacity.nanodegree.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MusicDetailsActivity extends AppCompatActivity {


    int idSong;

    TextView textViewSong, textViewArtist, textViewYear, textViewGenres, textViewRhythm, textViewDuration;
    String urlSound, urlArtistImage;

    private String json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        // TODO: Id must be come from intent
        idSong = 10;

        textViewSong = findViewById(R.id.text_view_song_name);
        textViewArtist = findViewById(R.id.text_view_artist_name);
        textViewYear = findViewById(R.id.text_view_song_year);
        textViewGenres= findViewById(R.id.text_view_song_genres);
        textViewRhythm = findViewById(R.id.text_view_song_rhythm);
        textViewDuration = findViewById(R.id.text_view_song_duration);

        loadJson();
    }


    public void loadJson(){

        json = null;

        try {
            InputStream is = getApplicationContext().getAssets().open("musicdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("musicdata");


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject musicdata = jsonArray.getJSONObject(i);

                    int idObject = musicdata.getInt("id");

                    if (idObject == idSong){
                        textViewSong.setText(musicdata.getString("song"));
                        textViewArtist.setText(musicdata.getString("artist"));
                        textViewYear.setText(String.valueOf(musicdata.getInt("year")));
                        textViewGenres.setText(musicdata.getString("genres"));
                        textViewRhythm.setText(musicdata.getString("rhythm"));
                        textViewDuration.setText(musicdata.getString("duration"));

                        urlSound = musicdata.getString("urlSound");
                        urlArtistImage = musicdata.getString("urlArtistImage");

                    }




                }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void goToPlayNow(View view){
        Intent intent = new Intent(this, NowPlayingActivity.class);
        startActivity(intent);
    }
}
