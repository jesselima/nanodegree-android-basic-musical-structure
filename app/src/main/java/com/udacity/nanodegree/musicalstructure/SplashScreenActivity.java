package com.udacity.nanodegree.musicalstructure;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMusicListActivity();
            }
        }, 3000);
    }

    private void goToMusicListActivity(){
        Intent intent = new Intent(this, MusicListActivity.class);
        startActivity(intent);
        finish();
    }
}
