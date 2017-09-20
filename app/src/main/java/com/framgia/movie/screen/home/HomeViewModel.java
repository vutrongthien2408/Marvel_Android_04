package com.framgia.movie.screen.home;

import android.content.Context;
import android.databinding.BaseObservable;
import com.framgia.movie.data.model.Genre;
import java.util.List;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private Context mContext;
    private HomeContract.Presenter mPresenter;

    public HomeViewModel(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.loadGenres();
    }

    @Override
    public void onGetGenresSuccess(List<Genre> genres) {

    }
}
