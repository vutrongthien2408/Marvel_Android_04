package com.framgia.movie.data.model;

import com.framgia.movie.data.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/19/2017.
 */

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private List<Movie> mMovies = new ArrayList<>();
    @SerializedName("cast")
    @Expose
    private List<Movie> mMovieByCharactors = new ArrayList<>();

    public List<Movie> getMovieByCharactors() {
        return mMovieByCharactors;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }
}
