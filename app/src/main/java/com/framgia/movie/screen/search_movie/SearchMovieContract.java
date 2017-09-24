package com.framgia.movie.screen.search_movie;

import com.framgia.movie.screen.BasePresenter;
import com.framgia.movie.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchMovieContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
