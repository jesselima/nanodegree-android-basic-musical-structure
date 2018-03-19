package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MusicListActivity extends AppCompatActivity {

    private TextView textViewResult;
    private String json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        textViewResult = findViewById(R.id.text_view_result);

        loadJson();

//        Button buttonParse = findViewById(R.id.button_parse);

//        buttonParse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadJson();
//            }
//        });
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

                String song = musicdata.getString("song");
                int year = musicdata.getInt("year");
                String artist = musicdata.getString("artist");

                textViewResult.append(song + ", " + ", " + artist + String.valueOf(year)+ "\n\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
