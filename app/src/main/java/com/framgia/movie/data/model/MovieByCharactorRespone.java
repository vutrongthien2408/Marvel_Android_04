package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/26/2017.
 */

public class MovieByCharactorRespone {
    @SerializedName("cast")
    @Expose
    private List<Movie> mMovieByCharactors = new ArrayList<>();

    public MovieByCharactorRespone(List<Movie> movieByCharactors) {
        mMovieByCharactors = movieByCharactors;
    }

    public List<Movie> getMovieByCharactors() {
        return mMovieByCharactors;
    }
}
