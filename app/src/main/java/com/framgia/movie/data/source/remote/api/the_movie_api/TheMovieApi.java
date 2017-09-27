package com.framgia.movie.data.source.remote.api.the_movie_api;

import com.framgia.movie.data.model.CharactorRespone;
import com.framgia.movie.data.model.GenreReponse;
import com.framgia.movie.data.model.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TrongThien on 9/19/2017.
 */

public interface TheMovieApi {
    /**
     * Get movie by genre
     */

    @GET("{version}/genre/{genre_id}/movies?")
    Observable<MovieResponse> loadActionMovie(@Path("version") String version,
            @Path("genre_id") int genreId, @Query("api_key") String apiKey);

    /**
     * Get list genre
     */

    @GET("{version}/genre/movie/list")
    Observable<GenreReponse> loadGenres(@Path("version") String version,
            @Query("api_key") String apiKey);

    /**
     * Get character by movie id
     */

    @GET("{version}/movie/{movie_id}/credits")
    Observable<CharactorRespone> loadCharactor(@Path("version") String version,
            @Path("movie_id") int movieId, @Query("api_key") String apiKey);

    /**
     * Get movie by charactor
     */
    @GET("{version}/person/{person_id}/movie_credits")
    Observable<MovieResponse> loadMovieByCharactor(@Path("version") String version,
            @Path("person_id") int personId, @Query("api_key") String apiKey);

    /**
     * Get the same movie
     */
    @GET("{version}/movie/{movie_id}/similar")
    Observable<MovieResponse> loadTheSameMovie(@Path("version") String version,
            @Path("movie_id") int movieId, @Query("api_key") String apiKey);
}
