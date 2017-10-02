package com.framgia.movie.screen.home;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.framgia.movie.screen.home.CategoryName.COMING_UP;
import static com.framgia.movie.screen.home.CategoryName.FAVORITE;
import static com.framgia.movie.screen.home.CategoryName.NOW_PLAYING;
import static com.framgia.movie.screen.home.CategoryName.POPULAR;
import static com.framgia.movie.screen.home.CategoryName.TOP_RATED;

/**
 * Created by TrongThien on 9/28/2017.
 */

/**
 * Category stringDef
 */

@Retention(RetentionPolicy.SOURCE)
@StringDef({ NOW_PLAYING, COMING_UP, TOP_RATED, POPULAR, FAVORITE })
public @interface CategoryName {
    String NOW_PLAYING = "now_playing";
    String COMING_UP = "upcoming";
    String TOP_RATED = "top_rated";
    String POPULAR = "popular";
    String FAVORITE = "favorite";
}