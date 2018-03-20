package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    //ImageView btnPlay;

    private String json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        recyclerView = findViewById(R.id.recycler_view);
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
                        musicdata.getInt("year")
                );
                musicList.add(data);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
