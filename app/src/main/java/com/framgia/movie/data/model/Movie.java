package com.framgia.movie.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TrongThien on 9/19/2017.
 */

public class Movie extends BaseObservable {
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String TEXT_IMDB = "IMDB: ";
    public static final String TEXT_POPULARITY = "Popularity: ";

    @SerializedName("id")
    @Expose
    @Bindable
    private int mId;

    @SerializedName("title")
    @Expose
    @Bindable
    private String mTitle;

    @SerializedName("overview")
    @Expose
    @Bindable
    private String mOverview;

    @SerializedName("release_date")
    @Expose
    @Bindable
    private String mReleaseDate;

    @SerializedName("vote_average")
    @Expose
    @Bindable
    private float mVoteAverage;

    @SerializedName("poster_path")
    @Expose
    @Bindable
    private String mPosterPath;

    @SerializedName("popularity")
    @Expose
    @Bindable
    private String mPopularity;

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getVoteAverage() {
        return TEXT_IMDB + mVoteAverage;
    }

    public String getPosterPath() {
        return BASE_IMAGE_URL + mPosterPath;
    }

    public String getPopularity() {
        return TEXT_POPULARITY + mPopularity;
    }
}
