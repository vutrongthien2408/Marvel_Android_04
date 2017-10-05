package com.framgia.movie.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TrongThien on 10/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyFavorite.db";
    private static final String SQL_CREATE_MOVIE = "CREATE TABLE "
            + MovieDataContract.MovieEntry.TABLE_NAME
            + " ("
            + MovieDataContract.MovieEntry.COLUMN_MOVIE_ID
            + " INTEGER PRIMARY KEY,"
            + MovieDataContract.MovieEntry.COLUMN_TITLE
            + " TEXT,"
            + MovieDataContract.MovieEntry.COLUMN_OVERVIEW
            + " TEXT,"
            + MovieDataContract.MovieEntry.COLUMN_POSTER_PATH
            + " TEXT,"
            + MovieDataContract.MovieEntry.COLUMN_POPULARITY
            + " TEXT,"
            + MovieDataContract.MovieEntry.COLUMN_VOTE_AVERAGE
            + " FLOAT,"
            + MovieDataContract.MovieEntry.COLUMN_RELEASE_DATE
            + " TEXT)";
    private static final String SQL_DELETE_MOVIE =
            "DROP TABLE IF EXISTS" + MovieDataContract.MovieEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE);
    }
}
