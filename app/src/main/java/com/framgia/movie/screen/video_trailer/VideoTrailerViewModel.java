package com.framgia.movie.screen.video_trailer;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;
import com.framgia.movie.BR;
import com.framgia.movie.data.model.MovieTrailer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

/**
 * Exposes the data to be used in the Video_trailer screen.
 */

public class VideoTrailerViewModel extends BaseObservable
        implements VideoTrailerContract.ViewModel, YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_REQUEST = 1;
    private VideoTrailerContract.Presenter mPresenter;
    private Context mContext;

    private String mVideoKey;
    @Bindable
    private String mName;
    @Bindable
    private YouTubePlayer.OnInitializedListener mListener;

    public VideoTrailerViewModel(Context context) {
        mContext = context;
    }

    public String getName() {
        return mName;
    }

    public YouTubePlayer.OnInitializedListener getListener() {
        return mListener;
    }

    public void setListener(YouTubePlayer.OnInitializedListener listener) {
        mListener = listener;
    }

    public void onBackPress(View view) {
        ((Activity) (view.getContext())).finish();
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
    public void setPresenter(VideoTrailerContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.loadMovieTrailer();
    }

    @Override
    public void onLoadTrailerSuccess(MovieTrailer movieTrailer) {
        mVideoKey = movieTrailer.getKey();
        mName = movieTrailer.getName();
        mListener = this;
        notifyPropertyChanged(BR.listener);
        notifyPropertyChanged(BR.name);
    }

    @Override
    public void onLoadTrailerFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
            YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(mVideoKey);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
            YouTubeInitializationResult youTubeInitializationResult) {
        youTubeInitializationResult.getErrorDialog((Activity) mContext, RECOVERY_REQUEST).show();
    }
}