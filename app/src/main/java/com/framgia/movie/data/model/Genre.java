package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TrongThien on 9/19/2017.
 */

public class Genre {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
