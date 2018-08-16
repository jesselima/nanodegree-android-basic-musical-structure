package com.udacity.nanodegree.musicalstructure.models;


public class MusicListModel {

    private int songId, songYear;
    private String songName, artistName, aboutArtist, urlArtistImage, songGenre, urlFacebookPage;

    public MusicListModel(int id, String songName, String artistName, int songYear, String aboutArtist, String urlArtistImage, String songGenre, String urlFacebookPage) {
        this.songId = id;
        this.songName = songName;
        this.artistName = artistName;
        this.songYear = songYear;
        this.aboutArtist = aboutArtist;
        this.urlArtistImage = urlArtistImage;
        this.songGenre = songGenre;
        this.urlFacebookPage = urlFacebookPage;
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

    public String getAboutArtist() {
        return aboutArtist;
    }

    public String getUrlArtistImage() {
        return urlArtistImage;
    }

    public String getsongGenre() {
        return songGenre;
    }

    public String geturlFacebookPage() {
        return urlFacebookPage;
    }
}
