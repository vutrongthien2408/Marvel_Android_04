package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TrongThien on 9/25/2017.
 */

public class Charactor {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_path")
    @Expose
    private String mProfilePath;

    public Charactor() {
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return Movie.BASE_IMAGE_URL + mProfilePath;
    }
}
