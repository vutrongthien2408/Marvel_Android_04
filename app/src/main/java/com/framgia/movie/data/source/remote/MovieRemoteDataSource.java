package com.framgia.movie.data.source.remote;

import com.framgia.movie.BuildConfig;
import com.framgia.movie.data.source.remote.api.action_movie_api.MovieResponse;
import com.framgia.movie.data.source.MovieDataSource;
import com.framgia.movie.data.source.remote.api.ServiceGenerator;
import com.framgia.movie.data.source.remote.api.action_movie_api.ActionMovieApi;
import com.framgia.movie.screen.BaseActivity;
import io.reactivex.Observable;

/**
 * Created by TrongThien on 9/19/2017.
 */

public final class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {
    private ActionMovieApi mMovieApi;
    private static MovieRemoteDataSource sMovieRemoteDataSource;

    private MovieRemoteDataSource() {
        mMovieApi = ServiceGenerator.createService(ActionMovieApi.class);
    }

    public static MovieRemoteDataSource getInstance() {
        if (sMovieRemoteDataSource == null) {
            sMovieRemoteDataSource = new MovieRemoteDataSource();
        }
        return sMovieRemoteDataSource;
    }

    @Override
    public Observable<MovieResponse> loadMovieByGenre(int genreId) {
        return mMovieApi.loadActionMovie(BaseActivity.API_VERSION, genreId, BuildConfig.API_KEY);
    }
}
