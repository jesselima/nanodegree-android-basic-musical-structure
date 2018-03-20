package com.udacity.nanodegree.musicalstructure;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.udacity.nanodegree.musicalstructure.OpenMXPlayer.OpenMXPlayer;
import com.udacity.nanodegree.musicalstructure.OpenMXPlayer.PlayerEvents;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);



        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }

    }

    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        // TODO: urlSound must come from intent + songName, artistName.
        String urlSound = "https://scontent.xx.fbcdn.net/v/t39.12897-6/24233908_136154023762215_6190567050713235456_n.m4a/Driven.m4a?oh=ca15f82fd29b5367acbe3b85e97c61ed&oe=5B01E683&dl=1";

        //progress = findViewById(R.id.text_view_progress);

        SeekBar seekbar;
        TextView seekBarTitle, progressMinutes, progressSeconds;


        PlayerEvents events = new PlayerEvents() {
            @Override
            public void onStop() {
                seekbar.setProgress(0);
            }
            @Override
            public void onStart(String mime, int sampleRate, int channels, long duration) {
            }
            @Override
            public void onPlayUpdate(int percent, long currentms, long totalms) {
                seekbar.setProgress(percent);

                int progressMin = (int) ((currentms / (1000*60)) % 60);
                int progressSec = (int) (currentms / 1000) % 60 ;
                progressMinutes.setText(String.valueOf(progressMin));
                progressSeconds.setText(String.valueOf(progressSec));
            }
            @Override
            public void onPlay() {
            }
            @Override
            public void onError() {
                seekbar.setProgress(0);
                Toast.makeText(getActivity(), "Error!",  Toast.LENGTH_SHORT).show();
                seekBarTitle.setText("An error has been encountered");
            }
        };

        OpenMXPlayer playerEvents = new OpenMXPlayer(events);

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.now_playing, container,false);

            (rootView.findViewById(R.id.btn_play)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_pause)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_stop)).setOnClickListener(this);

            seekbar = rootView.findViewById(R.id.seekbar);
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override public void onStopTrackingTouch(SeekBar seekBar) {}
                @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                    if (fromUser) playerEvents.seek(progress);
                }
            });

            seekBarTitle = rootView.findViewById(R.id.text_view_seek_bar_title);
            progressMinutes = rootView.findViewById(R.id.text_view_progress_minutes);
            progressSeconds = rootView.findViewById(R.id.text_view_progress_seconds);
            return rootView;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_play:
                    playerEvents.setDataSource(urlSound);
                    playerEvents.play();
                    seekBarTitle.setText("Now Playing...");
                    break;
                case R.id.btn_pause:
                    playerEvents.pause();
                    seekBarTitle.setText("Playback Paused!");
                    break;
                case R.id.btn_stop:
                    playerEvents.stop();
                    seekBarTitle.setText("Playback Stopped!");
                    break;


            }
        }
    }
}
