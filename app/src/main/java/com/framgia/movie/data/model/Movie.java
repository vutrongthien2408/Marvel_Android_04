package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TrongThien on 9/19/2017.
 */

public class Movie {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("title")
    @Expose
    private String mTitle;

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
