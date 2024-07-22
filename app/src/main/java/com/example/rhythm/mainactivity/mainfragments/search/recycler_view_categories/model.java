package com.example.rhythm.mainactivity.mainfragments.search.recycler_view_categories;

import java.util.List;

public class model {

    String coverURL;
    List<String> songs;

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public  model() {}

    public model(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}

