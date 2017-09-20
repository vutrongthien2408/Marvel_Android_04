package com.framgia.movie.screen.home;

import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.data.source.remote.MovieRemoteDataSource;

/**
 * Listens to user actions from the UI ({@link HomeActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HomePresenter implements HomeContract.Presenter {
    private MovieRepository mMovieRepository;
    private final HomeContract.ViewModel mViewModel;

    public HomePresenter(HomeContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadGenres() {
    }
}
