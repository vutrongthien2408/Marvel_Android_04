package com.framgia.movie.screen.movie;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Exposes the data to be used in the Movie_fragment screen.
 */

public class MovieFragmentViewModel extends BaseObservable {
    @Bindable
    private MovieAdapter mAdapter;

    public MovieAdapter getAdapter() {
        return mAdapter;
    }
}
