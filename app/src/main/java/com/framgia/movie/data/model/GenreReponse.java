package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by TrongThien on 9/21/2017.
 */

public class GenreReponse {
    @SerializedName("genres")
    @Expose
    private List<Genre> mGenreList;

    public List<Genre> getGenreList() {
        return mGenreList;
    }
}
