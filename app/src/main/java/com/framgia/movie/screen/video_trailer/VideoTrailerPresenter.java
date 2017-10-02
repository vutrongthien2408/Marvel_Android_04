package com.framgia.movie.screen.video_trailer;

import com.framgia.movie.data.model.MovieTrailer;
import com.framgia.movie.data.source.MovieTrailerRepository;
import com.framgia.movie.data.source.remote.MovieTrailerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link VideoTrailerActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class VideoTrailerPresenter implements VideoTrailerContract.Presenter {
    private static final int FIRST_ITEM = 0;

    private final VideoTrailerContract.ViewModel mViewModel;
    private int mMovieId;
    private MovieTrailerRepository mTrailerRepository;
    private CompositeDisposable mCompositeDisposable;

    public VideoTrailerPresenter(VideoTrailerContract.ViewModel viewModel, int movieId) {
        mViewModel = viewModel;
        mMovieId = movieId;
        mCompositeDisposable = new CompositeDisposable();
        mTrailerRepository =
                MovieTrailerRepository.getInstance(MovieTrailerDataSource.getInstance());
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadMovieTrailer() {
        Disposable disposable = mTrailerRepository.loadMovieTrailer(mMovieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieTrailer>>() {
                    @Override
                    public void accept(List<MovieTrailer> trailers) throws Exception {
                        mViewModel.onLoadTrailerSuccess(trailers.get(FIRST_ITEM));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onLoadTrailerFail(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
