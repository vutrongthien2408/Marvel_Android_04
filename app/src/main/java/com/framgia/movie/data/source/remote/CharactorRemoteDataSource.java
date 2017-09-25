package com.framgia.movie.data.source.remote;

import com.framgia.movie.BuildConfig;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.CharactorRespone;
import com.framgia.movie.data.source.TheMovieRemoteDataSource;
import com.framgia.movie.data.source.remote.api.ServiceGenerator;
import com.framgia.movie.data.source.remote.api.the_movie_api.TheMovieApi;
import com.framgia.movie.screen.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by TrongThien on 9/24/2017.
 */

public final class CharactorRemoteDataSource
        implements TheMovieRemoteDataSource.CharactorDataSource {
    private TheMovieApi mTheMovieApi;
    private static CharactorRemoteDataSource sCharactor = new CharactorRemoteDataSource();

    public static CharactorRemoteDataSource getInstance() {
        return sCharactor;
    }

    private CharactorRemoteDataSource() {
        mTheMovieApi = ServiceGenerator.createService(TheMovieApi.class);
    }

    @Override
    public Observable<List<Charactor>> loadCharactorByMovie(int movieId) {
        return mTheMovieApi.loadCharactor(BaseActivity.API_VERSION, movieId, BuildConfig.API_KEY)
                .flatMap(new Function<CharactorRespone, ObservableSource<List<Charactor>>>() {
                    @Override
                    public ObservableSource<List<Charactor>> apply(
                            CharactorRespone charactorRespone) throws Exception {
                        if (charactorRespone != null) {
                            return Observable.just(charactorRespone.getCharactors());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
