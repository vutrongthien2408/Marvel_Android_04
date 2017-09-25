package com.framgia.movie.screen.movie_detail;

import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.CharactorRepository;
import com.framgia.movie.data.source.remote.CharactorRemoteDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link MovieDetailActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class MovieDetailPresenter implements MovieDetailContract.Presenter {
    private static final String TAG = MovieDetailPresenter.class.getName();

    private final MovieDetailContract.ViewModel mViewModel;
    private int mMovieId;
    private CharactorRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public MovieDetailPresenter(MovieDetailContract.ViewModel viewModel, int movieId) {
        mRepository = CharactorRepository.getInstance(CharactorRemoteDataSource.getInstance());
        mViewModel = viewModel;
        mMovieId = movieId;
        mCompositeDisposable = new CompositeDisposable();
    }

    private void getListCharactor(Movie movie) {

    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    public void getCharactor() {
        Disposable disposable = mRepository.loadCharactorByMovie(mMovieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Charactor>>() {
                    @Override
                    public void accept(List<Charactor> charactors) throws Exception {
                        mViewModel.onLoadDetailSuccess(charactors);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onLoadDetailFail(throwable.toString());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMovieDetail() {
        getCharactor();
    }
}
