package com.framgia.movie.screen.movie;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.data.source.remote.MovieRemoteDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link MovieFragment}), retrieves the data and updates
 * the UI as required.
 */
public class MoviePresenter implements MovieContract.Presenter {

    private MovieContract.ViewModel mViewModel;
    private MovieRepository mMovieRepository;
    private int mGenreId;
    private CompositeDisposable mCompositeDisposable;

    public MoviePresenter() {
        mCompositeDisposable = new CompositeDisposable();
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
    }

    public MoviePresenter(MovieContract.ViewModel viewModel, int genreId) {
        mViewModel = viewModel;
        mGenreId = genreId;
        mCompositeDisposable = new CompositeDisposable();
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
    }

    public void setViewModel(MovieContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    private void getListMovie() {
        Disposable disposable = mMovieRepository.loadMovieByGenre(mGenreId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mViewModel.onGetMovieSuccess(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onGetMovieFail(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getMovieFromApi() {
        getListMovie();
    }
}