package com.framgia.movie.screen.home;

import com.framgia.movie.data.model.Genre;
import com.framgia.movie.data.source.GenreRepository;
import com.framgia.movie.data.source.remote.GenreRemoteDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link HomeActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HomePresenter implements HomeContract.Presenter {

    private static final String TAG = "HomePresenter";
    private GenreRepository mGenreRepository;

    private final HomeContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable;

    public HomePresenter(HomeContract.ViewModel viewModel) {
        mViewModel = viewModel;

        mCompositeDisposable = new CompositeDisposable();
        mGenreRepository = GenreRepository.getInstance(GenreRemoteDataSource.getInstance());
    }

    private void getListGenres() {
        Disposable disposable = mGenreRepository.loadGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Genre>>() {
                    @Override
                    public void accept(List<Genre> genres) throws Exception {
                        mViewModel.onGetGenresSuccess(genres);
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
    public void loadGenres() {
        getListGenres();
    }
}
