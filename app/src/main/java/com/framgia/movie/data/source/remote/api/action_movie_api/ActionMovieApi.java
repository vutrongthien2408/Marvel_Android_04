package com.framgia.movie.data.source.remote.api.action_movie_api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TrongThien on 9/19/2017.
 */

public interface ActionMovieApi {
    @GET("{version}/genre/{genre_id}/movies?")
    Observable<MovieResponse> loadActionMovie(@Path("version") String version,
            @Path("genre_id") int genreId, @Query("api_key") String apiKey);
}
