package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.Genre;
import com.framgia.movie.data.model.Movie;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/25/2017.
 */

public interface TheMovieRemoteDataSource {

    /**
     * load list genre
     */
    interface GenreDataSource {
        Observable<List<Genre>> loadGenres();
    }

    /**
     * load movie by genre
     */
    interface MovieDataSource {
        Observable<List<Movie>> loadMovieByGenre(int genreId);

        Observable<List<Movie>> loadMovieByCharactor(int charactorId);
    }

    /**
     * load charactor by movie id
     */
    interface CharactorDataSource {
        Observable<List<Charactor>> loadCharactorByMovie(int movieId);
    }
}
