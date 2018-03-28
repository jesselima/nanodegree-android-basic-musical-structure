package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

public class MusicListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MusicListAdapter adapter;
    private List<MusicListModel> musicList;
    private String json = null;
    CardView cardViewDescriptionLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        cardViewDescriptionLibrary = findViewById(R.id.card_description_music_list);
        cardViewDescriptionLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewDescriptionLibrary.setVisibility(View.GONE);
            }
        });

        Toast.makeText(this, getString(R.string.all_songs), Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recycler_view);
        musicList = new ArrayList<>();

        loadJson();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MusicListAdapter(this, musicList);
        recyclerView.setAdapter(adapter);

    }

    /**
     * This method read the local file musicdata.json and all the songs available on the JSON file.
     * All the is loaded in a musicList. It's a List<MusicListModel> object. This Object(musicList) with JSON data
     * is loaded in a Adapter object. This object Adapter is used in the RecyclerView.
     *
     * The code below had as a guideline this resources below.
     *
     * How to parse local JSON file in Android Studio | JSON Filter Data Tutorial
     *      https://www.youtube.com/watch?v=h71Ia9iFWfI
     * How to Parse a Json Using Volley - Android Studio Tutorial
     *      https://www.youtube.com/watch?v=y2xtLqP8dSQ
     *      https://codinginflow.com/code-examples/android/volley-json-parsing
     */
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
                        musicdata.getString("urlArtistImage"),
                        musicdata.getString("genres"),
                        musicdata.getString("urlFacebookPage")
                );
                musicList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
