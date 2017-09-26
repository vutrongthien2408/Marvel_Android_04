package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/29/2017.
 */

public class MovieTrailerRespone {
    @SerializedName("results")
    @Expose
    private List<MovieTrailer> mTrailers = new ArrayList<>();

    public List<MovieTrailer> getTrailers() {
        return mTrailers;
    }
}
