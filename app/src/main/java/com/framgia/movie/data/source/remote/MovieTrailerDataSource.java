package com.framgia.movie.data.source.remote;

import com.framgia.movie.BuildConfig;
import com.framgia.movie.data.model.MovieTrailer;
import com.framgia.movie.data.model.MovieTrailerRespone;
import com.framgia.movie.data.source.TheMovieRemoteDataSource;
import com.framgia.movie.data.source.remote.api.ServiceGenerator;
import com.framgia.movie.data.source.remote.api.the_movie_api.TheMovieApi;
import com.framgia.movie.screen.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by TrongThien on 9/29/2017.
 */

public final class MovieTrailerDataSource
        implements TheMovieRemoteDataSource.MovieTrailerDataSource {
    private static MovieTrailerDataSource sMovieTrailer;
    private TheMovieApi mMovieApi;

    public static MovieTrailerDataSource getInstance() {
        if (sMovieTrailer == null) {
            sMovieTrailer = new MovieTrailerDataSource();
        }
        return sMovieTrailer;
    }

    private MovieTrailerDataSource() {
        mMovieApi = ServiceGenerator.createService(TheMovieApi.class);
    }

    @Override
    public Observable<List<MovieTrailer>> loadMovieTrailer(int movieId) {
        return mMovieApi.loadMovieTrailer(BaseActivity.API_VERSION, movieId, BuildConfig.API_KEY)
                .flatMap(new Function<MovieTrailerRespone, ObservableSource<List<MovieTrailer>>>() {

                    @Override
                    public ObservableSource<List<MovieTrailer>> apply(
                            MovieTrailerRespone trailerRespone) throws Exception {
                        if (trailerRespone != null) {
                            return Observable.just(trailerRespone.getTrailers());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
