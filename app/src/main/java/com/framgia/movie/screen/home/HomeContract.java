package com.framgia.movie.screen.home;

import com.framgia.movie.data.model.Genre;
import com.framgia.movie.screen.BasePresenter;
import com.framgia.movie.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HomeContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetGenresSuccess(List<Genre> genres);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void loadGenres();
    }
}
