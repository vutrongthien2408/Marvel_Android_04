package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.remote.MovieRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/19/2017.
 */

public final class MovieRepository implements TheMovieRemoteDataSource.MovieDataSource {
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private static MovieRepository sMovieRepository;

    public MovieRepository(MovieRemoteDataSource remoteDataSource) {
        mMovieRemoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource mRemoteDataSource) {
        if (sMovieRepository == null) {
            sMovieRepository = new MovieRepository(mRemoteDataSource);
        }
        return sMovieRepository;
    }

    @Override
    public Observable<List<Movie>> loadMovieByGenre(int genreId) {
        return mMovieRemoteDataSource.loadMovieByGenre(genreId);
    }

    @Override
    public Observable<List<Movie>> loadMovieByCharactor(int charactorId) {
        return mMovieRemoteDataSource.loadMovieByCharactor(charactorId);
    }

    @Override
    public Observable<List<Movie>> loadTheSame(int movieId) {
        return mMovieRemoteDataSource.loadTheSame(movieId);
    }

    @Override
    public Observable<List<Movie>> loadMovieByName(String name) {
        return mMovieRemoteDataSource.loadMovieByName(name);
    }
}
