package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.remote.api.action_movie_api.MovieResponse;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/19/2017.
 */

public interface MovieDataSource {
    /**
     * RemoteData
     */
    interface RemoteDataSource {
        Observable<List<Movie>> loadMovieByGenre(int genreId);
    }
}
