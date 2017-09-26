package com.framgia.movie.screen.movie_detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import com.framgia.movie.R;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.databinding.ActivityMovieDetailBinding;
import com.framgia.movie.screen.BaseActivity;

/**
 * Movie_detail Screen.
 */
public class MovieDetailActivity extends BaseActivity {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private MovieDetailContract.ViewModel mViewModel;

    public static Intent getMovieIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, (Parcelable) movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(EXTRA_MOVIE);
        mViewModel = new MovieDetailViewModel(this);

        MovieDetailContract.Presenter presenter =
                new MovieDetailPresenter(mViewModel, movie.getId());
        mViewModel.setPresenter(presenter);
        mViewModel.setMovieId(movie.getId());

        ActivityMovieDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        binding.setViewModel((MovieDetailViewModel) mViewModel);
        binding.setMovie(movie);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
