package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.local.FavoriteLocalDataSource;
import java.util.List;

/**
 * Created by TrongThien on 10/2/2017.
 */

public final class FavoriteRepository implements FavoriteDataSource {
    private static FavoriteRepository sFavoriteRepository;
    private FavoriteLocalDataSource mFavoriteLocalDataSource;

    public static FavoriteRepository getInstance(FavoriteLocalDataSource favoriteLocalDataSource) {
        if (sFavoriteRepository == null) {
            sFavoriteRepository = new FavoriteRepository(favoriteLocalDataSource);
        }
        return sFavoriteRepository;
    }

    private FavoriteRepository(FavoriteLocalDataSource favoriteLocalDataSource) {
        mFavoriteLocalDataSource = favoriteLocalDataSource;
    }

    @Override
    public List<Movie> getMovies() {
        return mFavoriteLocalDataSource.getMovies();
    }

    @Override
    public int deleteMovie(Movie movie) {
        return mFavoriteLocalDataSource.deleteMovie(movie);
    }

    @Override
    public boolean insertMovie(Movie movie) {
        return mFavoriteLocalDataSource.insertMovie(movie);
    }
}
