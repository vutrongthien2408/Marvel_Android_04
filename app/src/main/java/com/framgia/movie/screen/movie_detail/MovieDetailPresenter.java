package com.framgia.movie.screen.movie_detail;

import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.CharactorRepository;
import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.data.source.remote.CharactorRemoteDataSource;
import com.framgia.movie.data.source.remote.MovieRemoteDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link MovieDetailActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {
    private static final String TAG = MovieDetailPresenter.class.getName();
    private static final int CHARACTOR_LIST_SIZE = 20;

    private MovieDetailContract.ViewModel mViewModel;
    private int mMovieId;
    private CharactorRepository mRepository;
    private CompositeDisposable mCompositeDisposable;
    private MovieRepository mMovieRepository;

    public MovieDetailPresenter() {
        mRepository = CharactorRepository.getInstance(CharactorRemoteDataSource.getInstance());
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
        mCompositeDisposable = new CompositeDisposable();
    }

    public MovieDetailPresenter(MovieDetailContract.ViewModel viewModel, int movieId) {
        mRepository = CharactorRepository.getInstance(CharactorRemoteDataSource.getInstance());
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
        mViewModel = viewModel;
        mMovieId = movieId;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setViewModel(MovieDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
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
                        List<Charactor> charactorList = new ArrayList<>();
                        if (charactors.size() > CHARACTOR_LIST_SIZE) {
                            for (int i = 0; i < CHARACTOR_LIST_SIZE; i++) {
                                charactorList.add(charactors.get(i));
                            }
                        } else {
                            charactorList.addAll(charactors);
                        }
                        mViewModel.onLoadDetailSuccess(charactorList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onLoadDetailFail(throwable.toString());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void getTheSameMovie() {
        Disposable disposable = mMovieRepository.loadTheSame(mMovieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        List<Movie> movieList = new ArrayList<>();
                        if (movies.size() > CHARACTOR_LIST_SIZE) {
                            for (int i = 0; i < CHARACTOR_LIST_SIZE; i++) {
                                movieList.add(movies.get(i));
                            }
                        } else {
                            movieList.addAll(movies);
                        }
                        mViewModel.onLoadTheSameMovieSuccess(movieList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onLoadTheSameMovieFail(throwable.toString());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMovieDetail() {
        getCharactor();
        getTheSameMovie();
    }
}
