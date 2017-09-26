package com.framgia.movie.screen.video_trailer;

import com.framgia.movie.data.model.MovieTrailer;
import com.framgia.movie.screen.BasePresenter;
import com.framgia.movie.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VideoTrailerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onLoadTrailerSuccess(MovieTrailer trailer);

        void onLoadTrailerFail(String err);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void loadMovieTrailer();
    }
}
