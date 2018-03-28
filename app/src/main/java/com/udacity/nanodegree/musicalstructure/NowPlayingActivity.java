package com.udacity.nanodegree.musicalstructure;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.udacity.nanodegree.musicalstructure.OpenMXPlayer.OpenMXPlayer;
import com.udacity.nanodegree.musicalstructure.OpenMXPlayer.PlayerEvents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class NowPlayingActivity extends AppCompatActivity {

    int idSong, year;
    String genres, rhythm, duration, aboutArtist;
    static String urlArtistImage;
    public static String urlSound, song, artist, urlFacebookPage;
    private String json = null;
    Button btnSongDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        Bundle musicData = getIntent().getExtras();
        idSong = musicData.getInt("id");
        song = musicData.getString("song");
        artist = musicData.getString("artist");
        urlArtistImage = musicData.getString("urlArtistImage");
        urlFacebookPage = musicData.getString("urlFacebookPage");

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }

        loadJson(idSong);

        btnSongDetails = findViewById(R.id.btn_song_details);
        btnSongDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSongDetails();
            }
        });
    }

    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        SeekBar seekbar;
        TextView seekBarTitle, progress, textViewSongName, textViewArtistName;
        ImageView artistImage;

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

                long minutes = (currentms / 1000) / 60;
                long seconds = (currentms / 1000) % 60;
                String secondsStr = Long.toString(seconds);
                String secs;
                if (secondsStr.length() >= 2) {
                    secs = secondsStr.substring(0, 2);
                } else {
                    secs = "0" + secondsStr;
                }
                String progressTotal = minutes + ":" + secs;
                progress.setText(String.valueOf(progressTotal));
            }
            @Override
            public void onPlay() {
            }
            @Override
            public void onError() {
                seekbar.setProgress(0);
                Toast.makeText(getActivity(), R.string.error,  Toast.LENGTH_SHORT).show();
                seekBarTitle.setText(R.string.error_message);
            }
        };

        OpenMXPlayer playerEvents = new OpenMXPlayer(events);

        public PlaceholderFragment() {
        }

        /**
         *
         * @param inflater uses the method inflate to inflate the now_playing layout inside the ViewGroup container
         * @param container is the FrameLayout that will hold all the child views in now play activity
         * @param savedInstanceState ...
         * @return
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.now_playing, container,false);

            textViewSongName = rootView.findViewById(R.id.text_view_song_name);
            textViewArtistName = rootView.findViewById(R.id.text_view_artist_name);
            artistImage = rootView.findViewById(R.id.image_artist);

            (rootView.findViewById(R.id.btn_play)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_pause)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_stop)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_previous)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_next)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_back_repeat)).setOnClickListener(this);
            (rootView.findViewById(R.id.btn_shuffle)).setOnClickListener(this);

            seekbar = rootView.findViewById(R.id.seekbar);
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override public void onStopTrackingTouch(SeekBar seekBar) {}
                @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                    if (fromUser) playerEvents.seek(progress);
                }
            });

            seekBarTitle = rootView.findViewById(R.id.text_view_seek_bar_title);
            progress = rootView.findViewById(R.id.text_view_progress);

            return rootView;
        }



        @Override
        public void onResume(){
            super.onResume();
            playerEvents.waitPlay();
        }

        @Override
        public void onStart(){
            super.onStart();
            playerEvents.waitPlay();
            textViewSongName.setText(song);
            textViewArtistName.setText(artist);
            Glide.with(this)
                    .load(urlArtistImage)
                    .into(artistImage);

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_play:
                    playerEvents.setDataSource(urlSound);
                    playerEvents.play();
                    seekBarTitle.setText(R.string.now_playing);
                    Toast.makeText(getContext(),getString(R.string.playing) + song, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_pause:
                    playerEvents.pause();
                    seekBarTitle.setText(R.string.paused);
                    Toast.makeText(getContext(), R.string.music_paused, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_stop:
                    playerEvents.stop();
                    seekBarTitle.setText(R.string.stopped);
                    Toast.makeText(getContext(), R.string.music_stopped, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_previous:
                    Toast.makeText(getContext(), R.string.previous_clicked, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_next:
                    Toast.makeText(getContext(), R.string.next_clicked, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_back_repeat:
                    Toast.makeText(getContext(), R.string.repeat_clicked, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_shuffle:
                    Toast.makeText(getContext(), R.string.shuffle_clicked, Toast.LENGTH_SHORT).show();
                    break;
            }

        }

    }

    /**
     *
     * @param idSong is the id of the song.
     * @return the url of the sound file.
     */
    public String loadJson(int idSong){

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

                int idObjectJSON = musicdata.getInt("id");

                if (idObjectJSON == idSong){
                    urlSound = musicdata.getString("urlSound");
                    song = musicdata.getString("song");
                    artist = musicdata.getString("artist");
                    genres = musicdata.getString("genres");
                    rhythm = musicdata.getString("rhythm");
                    duration = musicdata.getString("duration");
                    year = musicdata.getInt("year");
                    urlArtistImage = musicdata.getString("urlArtistImage");
                    aboutArtist = musicdata.getString("aboutArtist");
                    urlFacebookPage = musicdata.getString("urlFacebookPage");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return urlSound;
    }



    public void goToSongDetails(){
        Intent intent = new Intent(this, MusicDetailsActivity.class);
            intent.putExtra("song", idSong);
            intent.putExtra("song", song);
            intent.putExtra("artist", artist);
            intent.putExtra("genres", genres);
            intent.putExtra("rhythm", rhythm);
            intent.putExtra("duration", duration);
            intent.putExtra("year", year);
            intent.putExtra("urlArtistImage", urlArtistImage);
            intent.putExtra("aboutArtist", aboutArtist);
            intent.putExtra("urlFacebookPage", urlFacebookPage);
        startActivity(intent);
    }
}
