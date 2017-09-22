package com.framgia.movie.screen.movie;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BasePresenter;
import com.framgia.movie.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MovieContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetMovieSuccess(List<Movie> movies);

        void onGetMovieFail(String msg);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getMovieFromApi();
    }
}
