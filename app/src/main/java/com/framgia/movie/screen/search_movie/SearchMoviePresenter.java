package com.framgia.movie.screen.search_movie;

/**
 * Listens to user actions from the UI ({@link SearchMovieActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SearchMoviePresenter implements SearchMovieContract.Presenter {
    private static final String TAG = SearchMoviePresenter.class.getName();

    private final SearchMovieContract.ViewModel mViewModel;

    public SearchMoviePresenter(SearchMovieContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
