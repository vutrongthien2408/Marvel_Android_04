package com.framgia.movie.screen.video_trailer;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.movie.R;
import com.framgia.movie.databinding.ActivityVideoTrailerBinding;
import com.framgia.movie.screen.search_movie.SearchMovieActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;

/**
 * Video_trailer Screen.
 */
public class VideoTrailerActivity extends YouTubeBaseActivity {

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";
    private VideoTrailerContract.ViewModel mViewModel;

    public static Intent getVideoIntent(Context context, int movieId) {
        Intent intent = new Intent(context, VideoTrailerActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int movieId =
                getIntent().getIntExtra(EXTRA_MOVIE_ID, SearchMovieActivity.CHARACTOR_ID_DEFAULT);
        mViewModel = new VideoTrailerViewModel(this);

        VideoTrailerContract.Presenter presenter = new VideoTrailerPresenter(mViewModel, movieId);
        mViewModel.setPresenter(presenter);

        ActivityVideoTrailerBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_video_trailer);
        binding.setViewModel((VideoTrailerViewModel) mViewModel);
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
