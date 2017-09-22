package com.framgia.movie.screen.home;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Genre;
import java.util.List;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private Context mContext;
    private HomeContract.Presenter mPresenter;
    private FragmentManager mManager;
    @Bindable
    private HomePagerAdapter mPagerAdapter;

    public HomeViewModel(Context context, FragmentManager manager) {
        mContext = context;
        mManager = manager;
        mPagerAdapter = new HomePagerAdapter(mManager);
    }

    public HomePagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(HomePagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
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
        mPagerAdapter.setGenres(genres);
        notifyPropertyChanged(BR.pagerAdapter);
    }
}
