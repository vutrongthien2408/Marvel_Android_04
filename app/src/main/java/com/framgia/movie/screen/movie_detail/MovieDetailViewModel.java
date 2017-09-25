package com.framgia.movie.screen.movie_detail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.GridLayoutManager;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Charactor;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Movie_detail screen.
 */

public class MovieDetailViewModel extends BaseObservable implements MovieDetailContract.ViewModel {
    private List<Charactor> mCharactors = new ArrayList<>();
    private MovieDetailContract.Presenter mPresenter;
    @Bindable
    private CharactorAdapter mCharactorAdapter;
    @Bindable
    private GridLayoutManager mGridLayoutManager;

    public MovieDetailViewModel(Context context) {
        mCharactorAdapter = new CharactorAdapter();
        mGridLayoutManager = new GridLayoutManager(context, mCharactors.size());
    }

    public GridLayoutManager getGridLayoutManager() {
        return mGridLayoutManager;
    }

    public void setGridLayoutManager(GridLayoutManager gridLayoutManager) {
        mGridLayoutManager = gridLayoutManager;
    }

    public CharactorAdapter getCharactorAdapter() {
        return mCharactorAdapter;
    }

    public void setCharactorAdapter(CharactorAdapter charactorAdapter) {
        mCharactorAdapter = charactorAdapter;
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
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoadDetailSuccess(List<Charactor> charactors) {
        mCharactorAdapter.setCharactors(charactors);
        notifyPropertyChanged(BR.charactorAdapter);
    }

    @Override
    public void onLoadDetailFail(String err) {
    }
}
