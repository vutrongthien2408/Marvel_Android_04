package com.framgia.movie.screen.search_movie;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BasePresenter;
import com.framgia.movie.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchMovieContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchSuccess(List<Movie> movies);

        void onSearchFail(String err);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void searchMovieByName();

        void searchMovieByCharactorId();
    }
}
