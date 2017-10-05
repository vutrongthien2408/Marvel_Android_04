package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.source.remote.CharactorRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/24/2017.
 */

public class CharactorRepository implements TheMovieRemoteDataSource.CharactorDataSource {
    private CharactorRemoteDataSource mCharactorRemoteDataSource;

    private static CharactorRepository sCharactor;

    public static CharactorRepository getInstance(
            CharactorRemoteDataSource charactorRemoteDataSource) {

        if (sCharactor == null) {
            sCharactor = new CharactorRepository(charactorRemoteDataSource);
        }
        return sCharactor;
    }

    private CharactorRepository(CharactorRemoteDataSource charactorRemoteDataSource) {
        mCharactorRemoteDataSource = charactorRemoteDataSource;
    }

    @Override
    public Observable<List<Charactor>> loadCharactorByMovie(int movieId) {
        return mCharactorRemoteDataSource.loadCharactorByMovie(movieId);
    }
}
