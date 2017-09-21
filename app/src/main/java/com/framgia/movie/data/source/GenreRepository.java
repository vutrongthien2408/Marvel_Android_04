package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Genre;
import com.framgia.movie.data.source.remote.GenreRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/21/2017.
 */

public final class GenreRepository implements GenreDataSource.RemoteDataSource {
    private GenreRemoteDataSource mRemoteDataSource;

    private static GenreRepository sGenreRepository;

    public static GenreRepository getInstance(GenreRemoteDataSource genreRemoteDataSource) {
        if (sGenreRepository == null) {
            sGenreRepository = new GenreRepository(genreRemoteDataSource);
        }
        return sGenreRepository;
    }

    private GenreRepository(GenreRemoteDataSource genreRemoteDataSource) {
        mRemoteDataSource = genreRemoteDataSource;
    }

    @Override
    public Observable<List<Genre>> loadGenres() {
        return mRemoteDataSource.loadGenres();
    }
}
