package com.framgia.movie.screen.search_movie;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.FavoriteRepository;
import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.data.source.remote.MovieRemoteDataSource;
import com.framgia.movie.screen.home.CategoryName;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link SearchMovieActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
public class SearchMoviePresenter implements SearchMovieContract.Presenter {
    private static final String TAG = SearchMoviePresenter.class.getName();
    private int mCharactorId;
    private final SearchMovieContract.ViewModel mViewModel;
    private MovieRepository mMovieRepository;
    private FavoriteRepository mFavoriteRepository;
    private CompositeDisposable mCompositeDisposable;
    private String mCategory;

    public SearchMoviePresenter(SearchMovieContract.ViewModel viewModel, int charactorId,
            String category, FavoriteRepository favoriteRepository) {
        mViewModel = viewModel;
        mCharactorId = charactorId;
        mCategory = category;
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
        mCompositeDisposable = new CompositeDisposable();
        mFavoriteRepository = favoriteRepository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void searchMovieByName(String name) {
        Disposable disposable = mMovieRepository.loadMovieByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mViewModel.onSearchSuccess(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onSearchFail(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void searchMovieByCharactorId() {
        if (mCharactorId == SearchMovieActivity.CHARACTOR_ID_DEFAULT) {
            return;
        }
        getMovieByCharactorId();
    }

    @Override
    public void searchMovieByCategory() {
        if (mCategory.equals("") || mCategory.equals(CategoryName.FAVORITE)) {
            return;
        }
        Disposable disposable = mMovieRepository.loadMovieByCategory(mCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mViewModel.onSearchSuccess(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onSearchFail(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void showFavorite() {
        if (!mCategory.equals(CategoryName.FAVORITE) && !mCategory.equals("")) {
            return;
        }
        if (mFavoriteRepository.getMovies() == null) {
            return;
        }
        List<Movie> movies = new ArrayList<>();

        movies.addAll(mFavoriteRepository.getMovies());
        mViewModel.onSearchSuccess(movies);
    }

    @Override
    public void insertMovie(Movie movie) {
        if (mFavoriteRepository.insertMovie(movie)) {
            List<Movie> movies = new ArrayList<>();
            movies.addAll(mFavoriteRepository.getMovies());
            mViewModel.onInsertMovieSuccess();
            mViewModel.onSearchSuccess(movies);
        } else {
            mViewModel.onInsertMovieFail();
        }
    }

    private void getMovieByCharactorId() {
        Disposable disposable = mMovieRepository.loadMovieByCharactor(mCharactorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mViewModel.onSearchSuccess(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewModel.onSearchFail(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
