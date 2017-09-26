package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Movie;
import java.util.List;

/**
 * Created by TrongThien on 10/2/2017.
 */

public interface FavoriteDataSource {
    List<Movie> getMovies();

    int deleteMovie(Movie movie);

    boolean insertMovie(Movie movie);
}
