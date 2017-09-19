package com.framgia.movie.data.source;

import com.framgia.movie.data.source.remote.api.action_movie_api.MovieResponse;
import io.reactivex.Observable;

/**
 * Created by TrongThien on 9/19/2017.
 */

public final class MovieRepository implements MovieDataSource.RemoteDataSource {
    private MovieDataSource.RemoteDataSource mRemoteDataSource;
    private static MovieRepository sMovieRepository;

    public MovieRepository(MovieDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(MovieDataSource.RemoteDataSource mRemoteDataSource) {
        if (sMovieRepository == null) {
            sMovieRepository = new MovieRepository(mRemoteDataSource);
        }
        return sMovieRepository;
    }

    @Override
    public Observable<MovieResponse> loadMovieByGenre(int genreId) {
        return mRemoteDataSource.loadMovieByGenre(genreId);
    }
}
