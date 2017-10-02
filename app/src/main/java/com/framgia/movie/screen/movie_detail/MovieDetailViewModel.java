package com.framgia.movie.screen.movie_detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import com.framgia.movie.screen.search_movie.SearchMovieActivity;
import com.framgia.movie.screen.video_trailer.VideoTrailerActivity;
import java.util.List;

/**
 * Exposes the data to be used in the Movie_detail screen.
 */

public class MovieDetailViewModel extends BaseObservable implements MovieDetailContract.ViewModel {
    private MovieDetailContract.Presenter mPresenter;
    private Context mContext;
    @Bindable
    private CharactorAdapter mCharactorAdapter;
    @Bindable
    private TheSameMovieAdapter mTheSameMovieAdapter;
    @Bindable
    private LinearLayoutManager mLinearLayoutManager;
    @Bindable
    private int mMovieId;

    public MovieDetailViewModel(Context context) {

        mCharactorAdapter = new CharactorAdapter(context);
        mTheSameMovieAdapter = new TheSameMovieAdapter(context);
        mContext = context;
        setListener();
    }

    public void onPosterClicked(View view) {
        Intent intent = VideoTrailerActivity.getVideoIntent(mContext, mMovieId);
        mContext.startActivity(intent);
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    public CharactorAdapter getCharactorAdapter() {
        return mCharactorAdapter;
    }

    public TheSameMovieAdapter getTheSameMovieAdapter() {
        return mTheSameMovieAdapter;
    }

    public void onBackPress(View view) {
        ((Activity) (mContext)).finish();
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
    }

    @Override
    public void onLoadDetailSuccess(List<Charactor> charactors) {
        mCharactorAdapter.updateCharactors(charactors);
    }

    @Override
    public void onLoadDetailFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadTheSameMovieSuccess(List<Movie> movies) {
        mTheSameMovieAdapter.updateMovie(movies);
    }

    @Override
    public void onLoadTheSameMovieFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMovieId(int movieId) {
        mMovieId = movieId;
        notifyPropertyChanged(BR.movieId);
    }

    public void setListener() {
        mTheSameMovieAdapter.setItemClickListener(
                new BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie>() {
                    @Override
                    public void onItemRecyclerViewClick(Movie item) {
                        mContext.startActivity(MovieDetailActivity.getMovieIntent(mContext, item));
                    }
                });
        mCharactorAdapter.setItemClickListener(
                new BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor>() {
                    @Override
                    public void onItemRecyclerViewClick(Charactor item) {
                        mContext.startActivity(
                                SearchMovieActivity.getMovieIntent(mContext, item.getId()));
                    }
                });
    }
}
