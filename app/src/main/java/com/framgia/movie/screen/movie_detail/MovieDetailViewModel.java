package com.framgia.movie.screen.movie_detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.GridLayoutManager;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import com.framgia.movie.screen.search_movie.SearchMovieActivity;
import java.util.List;

/**
 * Exposes the data to be used in the Movie_detail screen.
 */

public class MovieDetailViewModel extends BaseObservable implements MovieDetailContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor> {
    private static final int GRID_SPAN_COUNT = 4;
    private MovieDetailContract.Presenter mPresenter;
    private Context mContext;
    @Bindable
    private CharactorAdapter mCharactorAdapter;
    @Bindable
    private GridLayoutManager mGridLayoutManager;

    public MovieDetailViewModel(Context context) {

        mCharactorAdapter = new CharactorAdapter(context);
        mContext = context;
        mGridLayoutManager = new GridLayoutManager(context, GRID_SPAN_COUNT);
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
        mPresenter.loadMovieDetail();
        mCharactorAdapter.setItemClickListener(this);
    }

    @Override
    public void onLoadDetailSuccess(List<Charactor> charactors) {
        mCharactorAdapter.setCharactors(charactors);
        notifyPropertyChanged(BR.charactorAdapter);
    }

    @Override
    public void onLoadDetailFail(String err) {
    }

    @Override
    public void onItemRecyclerViewClick(Charactor item) {
        Intent intent = SearchMovieActivity.getMovieIntent(mContext, item.getId());
        mContext.startActivity(intent);
    }
}
