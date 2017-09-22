package com.framgia.movie.screen.movie;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Movie;
import java.util.List;

/**
 * Exposes the data to be used in the Movie screen.
 */

public class MovieViewModel extends BaseObservable implements MovieContract.ViewModel {
    @Bindable
    private MovieAdapter mAdapter;

    private MovieContract.Presenter mPresenter;

    public MovieViewModel() {
        mAdapter = new MovieAdapter();
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
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        mAdapter.setMovies(movies);
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onGetMovieFail(String msg) {

    }
}
