package com.framgia.movie.data.source;

import com.framgia.movie.data.model.MovieTrailer;
import com.framgia.movie.data.source.remote.MovieTrailerDataSource;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/29/2017.
 */

public class MovieTrailerRepository implements TheMovieRemoteDataSource.MovieTrailerDataSource {
    private static MovieTrailerRepository sTrailerRepository;
    private MovieTrailerDataSource mTrailerDataSource;

    public static MovieTrailerRepository getInstance(
            MovieTrailerDataSource movieTrailerDataSource) {
        if (sTrailerRepository == null) {
            sTrailerRepository = new MovieTrailerRepository(movieTrailerDataSource);
        }
        return sTrailerRepository;
    }

    private MovieTrailerRepository(MovieTrailerDataSource trailerDataSource) {
        mTrailerDataSource = trailerDataSource;
    }

    @Override
    public Observable<List<MovieTrailer>> loadMovieTrailer(int movieId) {
        return mTrailerDataSource.loadMovieTrailer(movieId);
    }
}
