package com.framgia.movie.screen.search_movie;

/**
 * Exposes the data to be used in the Search_movie screen.
 */

public class SearchMovieViewModel implements SearchMovieContract.ViewModel {

    private SearchMovieContract.Presenter mPresenter;

    public SearchMovieViewModel() {
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
    }
}
