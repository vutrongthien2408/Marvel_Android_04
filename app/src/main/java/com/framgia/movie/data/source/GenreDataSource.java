package com.framgia.movie.data.source;

import com.framgia.movie.data.model.Genre;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by TrongThien on 9/21/2017.
 */

public interface GenreDataSource {
    /**
     * RemoteData
     */
    interface RemoteDataSource {
        Observable<List<Genre>> loadGenres();
    }
}
