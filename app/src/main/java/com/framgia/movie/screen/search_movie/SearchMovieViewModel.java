package com.framgia.movie.screen.search_movie;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.movie.MovieAdapter;
import java.util.List;

/**
 * Exposes the data to be used in the Search_movie screen.
 */

public class SearchMovieViewModel extends BaseObservable implements SearchMovieContract.ViewModel {

    private SearchMovieContract.Presenter mPresenter;
    private MovieAdapter mMovieAdapter;
    private Context mContext;
    @Bindable
    private MovieAdapter mAdapter;

    public SearchMovieViewModel(Context context) {
        mContext = context;
        mMovieAdapter = new MovieAdapter(context);
        mAdapter = new MovieAdapter(context);
    }

    public MovieAdapter getAdapter() {
        return mAdapter;
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
    public void setPresenter(SearchMovieContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.searchMovieByCharactorId();
    }

    @Override
    public void onSearchSuccess(List<Movie> movies) {
        mAdapter.setMovies(movies);
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onSearchFail(String err) {

    }
}
