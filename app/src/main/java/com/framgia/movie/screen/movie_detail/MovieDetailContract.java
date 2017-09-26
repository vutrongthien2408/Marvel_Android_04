package com.framgia.movie.screen.movie_detail;

import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BasePresenter;
import com.framgia.movie.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MovieDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onLoadDetailSuccess(List<Charactor> charactors);

        void onLoadDetailFail(String err);

        void onLoadTheSameMovieSuccess(List<Movie> movies);

        void onLoadTheSameMovieFail(String err);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void loadMovieDetail();
    }
}
