package com.framgia.movie.screen.search_movie;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import com.framgia.movie.screen.movie.MovieAdapter;
import com.framgia.movie.screen.movie_detail.MovieDetailActivity;
import java.util.List;

/**
 * Exposes the data to be used in the Search_movie screen.
 */

public class SearchMovieViewModel extends BaseObservable implements SearchMovieContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> {

    private SearchMovieContract.Presenter mPresenter;
    private Context mContext;
    @Bindable
    private MovieAdapter mAdapter;

    public SearchMovieViewModel(Context context) {
        mContext = context;
        mAdapter = new MovieAdapter(context);
        mAdapter.setItemClickListener(this);
    }

    public MovieAdapter getAdapter() {
        return mAdapter;
    }

    public void onBackPress(View view) {
        ((Activity) (view.getContext())).finish();
    }

    public boolean onQueryTextSubmit(String s) {
        if (s.equals("")) {
            return false;
        }
        mPresenter.searchMovieByName(s);
        return true;
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
    public void setPresenter(SearchMovieContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.searchMovieByCharactorId();
    }

    @Override
    public void onSearchSuccess(List<Movie> movies) {
        mAdapter.updateMovie(movies);
    }

    @Override
    public void onSearchFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemRecyclerViewClick(Movie item) {
        mContext.startActivity(MovieDetailActivity.getMovieIntent(mContext, item));
    }
}
