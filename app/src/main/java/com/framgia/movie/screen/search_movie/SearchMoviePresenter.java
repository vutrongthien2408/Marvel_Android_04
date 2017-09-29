package com.framgia.movie.screen.search_movie;

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
 * Listens to user actions from the UI ({@link SearchMovieActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SearchMoviePresenter implements SearchMovieContract.Presenter {
    private static final String TAG = SearchMoviePresenter.class.getName();
    private int mCharactorId;
    private final SearchMovieContract.ViewModel mViewModel;
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable;
    private String mCategory;

    public SearchMoviePresenter(SearchMovieContract.ViewModel viewModel, int charactorId,
            String category) {
        mViewModel = viewModel;
        mCharactorId = charactorId;
        mCategory = category;
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
        mCompositeDisposable = new CompositeDisposable();
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
        if (mCategory.equals("")) {
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
