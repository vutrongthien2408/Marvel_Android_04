package com.framgia.movie.data.source.remote;

import com.framgia.movie.BuildConfig;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.model.MovieResponse;
import com.framgia.movie.data.source.TheMovieRemoteDataSource;
import com.framgia.movie.data.source.remote.api.ServiceGenerator;
import com.framgia.movie.data.source.remote.api.the_movie_api.TheMovieApi;
import com.framgia.movie.screen.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.Calendar;
import java.util.List;

/**
 * Created by TrongThien on 9/19/2017.
 */

public final class MovieRemoteDataSource implements TheMovieRemoteDataSource.MovieDataSource {
    private TheMovieApi mMovieApi;
    private static MovieRemoteDataSource sMovieRemoteDataSource;

    private MovieRemoteDataSource() {
        mMovieApi = ServiceGenerator.createService(TheMovieApi.class);
    }

    public static MovieRemoteDataSource getInstance() {
        if (sMovieRemoteDataSource == null) {
            sMovieRemoteDataSource = new MovieRemoteDataSource();
        }
        return sMovieRemoteDataSource;
    }

    @Override
    public Observable<List<Movie>> loadMovieByGenre(int genreId) {
        return mMovieApi.loadActionMovie(BaseActivity.API_VERSION, genreId, BuildConfig.API_KEY)
                .flatMap(new Function<MovieResponse, ObservableSource<List<Movie>>>() {

                    @Override
                    public ObservableSource<List<Movie>> apply(MovieResponse movieResponse)
                            throws Exception {
                        if (movieResponse != null) {
                            return Observable.just(movieResponse.getMovies());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> loadMovieByCharactor(int charactorId) {
        return mMovieApi.loadMovieByCharactor(BaseActivity.API_VERSION, charactorId,
                BuildConfig.API_KEY)
                .flatMap(new Function<MovieResponse, ObservableSource<List<Movie>>>() {

                    @Override
                    public ObservableSource<List<Movie>> apply(MovieResponse movieResponse)
                            throws Exception {
                        if (movieResponse != null) {
                            return Observable.just(movieResponse.getMovieByCharactors());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> loadTheSame(int movieId) {
        return mMovieApi.loadTheSameMovie(BaseActivity.API_VERSION, movieId, BuildConfig.API_KEY)
                .flatMap(new Function<MovieResponse, ObservableSource<List<Movie>>>() {

                    @Override
                    public ObservableSource<List<Movie>> apply(MovieResponse movieResponse)
                            throws Exception {
                        if (movieResponse != null) {
                            return Observable.just(movieResponse.getMovies());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> loadMovieByName(String name) {
        return mMovieApi.loadMovieByName(BaseActivity.API_VERSION, BuildConfig.API_KEY, name,
                Calendar.getInstance().get(Calendar.YEAR))
                .flatMap(new Function<MovieResponse, ObservableSource<List<Movie>>>() {

                    @Override
                    public ObservableSource<List<Movie>> apply(MovieResponse movieResponse)
                            throws Exception {
                        if (movieResponse != null) {
                            return Observable.just(movieResponse.getMovies());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
