package com.udacity.nanodegree.musicalstructure.models;


public class MusicListModel {

    private int songId, songYear;
    private String songName, artistName;

    public MusicListModel(int id, String songName, String artistName, int songYear) {
        this.songId = id;
        this.songName = songName;
        this.artistName = artistName;
        this.songYear = songYear;
    }

    public int getSongId() {
        return songId;
    }

    public int getSongYear() {
        return songYear;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }
}
