package com.udacity.nanodegree.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.udacity.nanodegree.musicalstructure.adapters.MusicListAdapter;
import com.udacity.nanodegree.musicalstructure.models.MusicListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicListByGenreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MusicListAdapter adapter;
    private List<MusicListModel> musicList;

    private String json = null;

    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_by_genre);

        Bundle musicData = getIntent().getExtras();
            genre = musicData.getString("genre");
        Toast.makeText(this, genre, Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recycler_view_genres);
        musicList = new ArrayList<>();

        loadJson();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MusicListAdapter(this, musicList);
        recyclerView.setAdapter(adapter);

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

                MusicListModel data = new MusicListModel(
                        musicdata.getInt("id"),
                        musicdata.getString("song"),
                        musicdata.getString("artist"),
                        musicdata.getInt("year"),
                        musicdata.getString("aboutArtist"),
                        musicdata.getString("urlArtistImage")
                );
                musicList.add(data);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public  void goToMusicByGenre(View view){
        Intent intent = new Intent(this, MusicGenresActivity.class);
        startActivity(intent);
    }
}
