package com.framgia.movie.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.framgia.movie.data.model.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 10/2/2017.
 */

public final class FavoriteLocalDataSource extends DatabaseHelper
        implements com.framgia.movie.data.source.FavoriteDataSource {
    private static FavoriteLocalDataSource sLocalDataSource;

    public static FavoriteLocalDataSource getInstance(Context context) {
        if (sLocalDataSource == null) {
            sLocalDataSource = new FavoriteLocalDataSource(context);
        }
        return sLocalDataSource;
    }

    private FavoriteLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =
                db.query(MovieDataContract.MovieEntry.TABLE_NAME, null, null, null, null, null,
                        null);
        if (cursor != null && cursor.moveToFirst()) {
            movies = new ArrayList<>();
            do {
                movies.add(new Movie(cursor));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return movies;
    }

    @Override
    public int deleteMovie(Movie movie) {
        return 0;
    }

    @Override
    public boolean insertMovie(Movie movie) {
        if (movie == null) {
            return false;
        }
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(MovieDataContract.MovieEntry.TABLE_NAME, null, movie.getValue());
        return result != -1;
    }
}
