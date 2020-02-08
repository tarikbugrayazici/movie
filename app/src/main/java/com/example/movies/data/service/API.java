package com.example.movies.data.service;

import com.example.movies.data.entity.ActorsEntity;
import com.example.movies.data.entity.ActorsMoviesEntity;
import com.example.movies.data.entity.ActorsPhotoEntity;
import com.example.movies.data.entity.ActorsTaggedImages;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.DetailInfo;
import com.example.movies.data.entity.GalleryPhotoModel;
import com.example.movies.data.entity.MovieCrew;
import com.example.movies.data.entity.PersonP;
import com.example.movies.data.entity.TabPersonEntity;
import com.example.movies.data.entity.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tarik on 16.11.2019.
 */

public interface API {
    @GET("discover/movie")
    Call<BaseEntity> getMovie(@Query("api_key") String apikey,
                              @Query("language") String language,
                              @Query("sort_by") String sort_by,
                              @Query("include_adult") boolean include_adult,
                              @Query("include_video") boolean include_video,
                              @Query("page") int page);

    @GET("discover/tv")
    Call<BaseEntity> getTv(@Query("api_key") String apikey,
                           @Query("language") String language,
                           @Query("sort_by") String sort_by,
                           @Query("page") int page,
                           @Query("timezone") String timezone,
                           @Query("include_null_first_air_dates") boolean include_null_first_air_dates);

    @GET("trending/all/day")
    Call<BaseEntity> getTrendAll(@Query("api_key") String apikey);

    @GET("trending/movie/day")
    Call<BaseEntity> getTrendMovie(@Query("api_key") String apikey);

    @GET("trending/tv/day")
    Call<BaseEntity> getTrendTv(@Query("api_key") String apikey);

    @GET("trending/person/day")
    Call<TabPersonEntity> getTrendPerson(@Query("api_key") String apikey);

    @GET("search/movie")
    Call<BaseEntity> getSearchMovie(@Query("api_key") String apikey,
                                    @Query("language") String language,
                                    @Query("query") String query,
                                    @Query("page") int page,
                                    @Query("include_adult") boolean includeAdult);

    @GET("search/tv")
    Call<BaseEntity> getSearchTv(@Query("api_key") String apikey,
                                 @Query("language") String language,
                                 @Query("query") String query,
                                 @Query("page") int page);

    @GET("movie/{movie_id}/images")
    Call<GalleryPhotoModel> getBackdrops(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("include_image_language") String imageLanguage);


    @GET("movie/{movie_id}")
    Call<DetailInfo> getMovieDetail(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language);


    @GET("movie/{movie_id}/credits")
    Call<MovieCrew> getMovieCrew(
            @Path("movie_id") int id,
            @Query("api_key") String apikey);

    @GET("movie/{movie_id}/similar")
    Call<BaseEntity> getSimilarMovies(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") int page);

    @GET("movie/{movie_id}/recommendations")
    Call<BaseEntity> getRecommendedMovies(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") int page);

    @GET("movie/{movie_id}/videos")
    Call<Trailers> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language);

    @GET("person/{movie_id}")
    Call<ActorsEntity> getActorsDetail(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language);

    @GET("person/{movie_id}/images")
    Call<ActorsPhotoEntity> getActorsPhotos(
            @Path("movie_id") int id,
            @Query("api_key") String apikey);

    @GET("person/{movie_id}/movie_credits")
    Call<ActorsMoviesEntity> getActorsMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language);

    @GET("person/{movie_id}/tv_credits")
    Call<ActorsMoviesEntity> getActorsTv(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language);

    @GET("person/{movie_id}/tagged_images")
    Call<ActorsTaggedImages> getActorsTaggedImages(
            @Path("movie_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") int page);

    @GET("person/{person_id}/")
    Call<PersonP> getActorsPictures(
            @Path("person_id") int id,
            @Query("api_key") String apikey,
            @Query("language") String language);

}
