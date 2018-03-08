package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> numberList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMusicData();
    }

    public void loadMusicData(){

        String json;


        try {

            InputStream is = getAssets().open("xyz.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);

                if (obj.getString("name").equals("Jesse")){

                    numberList.add(obj.getString("number"));
                    Toast.makeText(getApplicationContext(), numberList.toString(), Toast.LENGTH_SHORT).show();

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
