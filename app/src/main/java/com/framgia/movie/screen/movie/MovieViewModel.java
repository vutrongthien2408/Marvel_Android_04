package com.framgia.movie.screen.movie;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import com.framgia.movie.screen.movie_detail.MovieDetailActivity;
import java.util.List;

/**
 * Exposes the data to be used in the Movie screen.
 */

public class MovieViewModel extends BaseObservable implements MovieContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> {
    @Bindable
    private MovieAdapter mAdapter;

    private MovieContract.Presenter mPresenter;
    private Context mContext;

    public MovieViewModel(@NonNull Context context) {
        mAdapter = new MovieAdapter(context);
        mContext = context;
    }

    public MovieAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MovieAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(MovieContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getMovieFromApi();
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        mAdapter.updateMovie(movies);
    }

    @Override
    public void onGetMovieFail(String msg) {

    }

    @Override
    public void onItemRecyclerViewClick(Movie item) {
        mContext.startActivity(MovieDetailActivity.getMovieIntent(mContext, item));
    }
}
