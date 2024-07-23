package com.example.rhythm.mainactivity.mainfragments.search.recycler_view_categories;

import java.util.List;

public class model {

    String coverURL;

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

