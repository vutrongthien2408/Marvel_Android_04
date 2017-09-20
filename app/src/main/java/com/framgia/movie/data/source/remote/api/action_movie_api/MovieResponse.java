package com.framgia.movie.data.source.remote.api.action_movie_api;

import com.framgia.movie.data.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TrongThien on 9/19/2017.
 */

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private List<Movie> mMovies;
}
