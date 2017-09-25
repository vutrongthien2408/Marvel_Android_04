package com.framgia.movie.data.source.remote;

import com.framgia.movie.BuildConfig;
import com.framgia.movie.data.model.Genre;
import com.framgia.movie.data.source.TheMovieRemoteDataSource;
import com.framgia.movie.data.source.remote.api.ServiceGenerator;
import com.framgia.movie.data.model.GenreReponse;
import com.framgia.movie.data.source.remote.api.the_movie_api.TheMovieApi;
import com.framgia.movie.screen.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by TrongThien on 9/21/2017.
 */

public final class GenreRemoteDataSource implements TheMovieRemoteDataSource.GenreDataSource {
    private static GenreRemoteDataSource sGenreRemoteDataSource;
    private TheMovieApi mTheMovieApi;

    public static GenreRemoteDataSource getInstance() {
        if (sGenreRemoteDataSource == null) {
            sGenreRemoteDataSource = new GenreRemoteDataSource();
        }
        return sGenreRemoteDataSource;
    }

    private GenreRemoteDataSource() {
        mTheMovieApi = ServiceGenerator.createService(TheMovieApi.class);
    }

    @Override
    public Observable<List<Genre>> loadGenres() {
        return mTheMovieApi.loadGenres(BaseActivity.API_VERSION, BuildConfig.API_KEY)
                .flatMap(new Function<GenreReponse, ObservableSource<List<Genre>>>() {
                    @Override
                    public ObservableSource<List<Genre>> apply(GenreReponse genreReponse)
                            throws Exception {
                        if (genreReponse != null) {
                            return Observable.just(genreReponse.getGenreList());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
