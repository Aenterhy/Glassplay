package com.malice.glassplay.model;

import android.util.Log;

/**
 * Google play application represenation
 *
 * @author Roman Gerasimenko
 */
public class App {
    private String title;
    private String image;
    private String creator;
    private String rating;

    public App(String title, String image, String creator, String rating) {
        this.title = title;
        this.image = image;
        this.creator = creator;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getCreator() {
        return creator;
    }

    public String getRating() {
        return rating;
    }
}
