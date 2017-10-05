package com.framgia.movie.screen.movie;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import com.framgia.movie.screen.search_movie.SearchMovieActivity;

/**
 * Created by TrongThien on 9/25/2017.
 */

public class MovieItemViewModel extends BaseObservable {

    private Movie mMovie;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> mItemClickListener;

    public MovieItemViewModel(Movie movie,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> itemClickListener) {
        mMovie = movie;
        mItemClickListener = itemClickListener;
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mMovie);
    }

    public void onAddMovieToLocal(View view) {
        Intent intent = SearchMovieActivity.getFavoriteMovieIntent(view.getContext(), mMovie);
        view.getContext().startActivity(intent);
    }
}
