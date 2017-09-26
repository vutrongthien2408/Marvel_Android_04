package com.framgia.movie.screen.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import com.framgia.movie.BR;
import com.framgia.movie.R;
import com.framgia.movie.data.model.Genre;
import com.framgia.movie.screen.search_movie.SearchMovieActivity;
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
    @Bindable
    private int mGravity;

    public HomeViewModel(Context context, FragmentManager manager) {
        mContext = context;
        mManager = manager;
        mPagerAdapter = new HomePagerAdapter(mManager);
        mGravity = Gravity.END;
    }

    public int getGravity() {
        return mGravity;
    }

    public HomePagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(HomePagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
    }

    public void onItemNaviClicked(View view) {
        mGravity = Gravity.END;
        notifyPropertyChanged(BR.gravity);
        switch (view.getId()) {
            case R.id.text_now_playing:
                mContext.startActivity(
                        SearchMovieActivity.getCategoryIntent(mContext, CategoryName.NOW_PLAYING));
                break;
            case R.id.text_top_rated:
                mContext.startActivity(
                        SearchMovieActivity.getCategoryIntent(mContext, CategoryName.TOP_RATED));
                break;
            case R.id.text_upcoming:
                mContext.startActivity(
                        SearchMovieActivity.getCategoryIntent(mContext, CategoryName.COMING_UP));
                break;
            case R.id.text_popular:
                mContext.startActivity(
                        SearchMovieActivity.getCategoryIntent(mContext, CategoryName.POPULAR));
                break;
            case R.id.text_favorite:
                mContext.startActivity(
                        SearchMovieActivity.getCategoryIntent(mContext, CategoryName.FAVORITE));
                break;
            default:
                mContext.startActivity(SearchMovieActivity.getCategoryIntent(mContext, null));
                break;
        }
    }

    public void onNavigationClicked(View view) {
        mGravity = mGravity != Gravity.START ? Gravity.START : Gravity.END;
        notifyPropertyChanged(BR.gravity);
    }

    public void onSearch(View view) {
        Intent intent = new Intent(view.getContext(), SearchMovieActivity.class);
        view.getContext().startActivity(intent);
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
