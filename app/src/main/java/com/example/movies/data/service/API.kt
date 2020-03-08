package com.example.movies.data.service

import com.example.movies.data.entity.ActorsEntity
import com.example.movies.data.entity.ActorsMoviesEntity
import com.example.movies.data.entity.ActorsPhotoEntity
import com.example.movies.data.entity.ActorsTaggedImages
import com.example.movies.data.entity.BaseEntity
import com.example.movies.data.entity.DetailInfo
import com.example.movies.data.entity.GalleryPhotoModel
import com.example.movies.data.entity.MovieCrew
import com.example.movies.data.entity.PersonP
import com.example.movies.data.entity.TabPersonEntity
import com.example.movies.data.entity.Trailers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Tarik on 16.11.2019.
 */

interface API {
    @GET("discover/movie")
    fun getMovie(@Query("api_key") apikey: String,
                 @Query("language") language: String,
                 @Query("sort_by") sort_by: String,
                 @Query("include_adult") include_adult: Boolean,
                 @Query("include_video") include_video: Boolean,
                 @Query("page") page: Int): Call<BaseEntity>

    @GET("discover/tv")
    fun getTv(@Query("api_key") apikey: String,
              @Query("language") language: String,
              @Query("sort_by") sort_by: String,
              @Query("page") page: Int,
              @Query("timezone") timezone: String,
              @Query("include_null_first_air_dates") include_null_first_air_dates: Boolean): Call<BaseEntity>

    @GET("trending/all/day")
    fun getTrendAll(@Query("api_key") apikey: String): Call<BaseEntity>

    @GET("trending/movie/day")
    fun getTrendMovie(@Query("api_key") apikey: String): Call<BaseEntity>

    @GET("trending/tv/day")
    fun getTrendTv(@Query("api_key") apikey: String): Call<BaseEntity>

    @GET("trending/person/day")
    fun getTrendPerson(@Query("api_key") apikey: String): Call<TabPersonEntity>

    @GET("search/movie")
    fun getSearchMovie(@Query("api_key") apikey: String,
                       @Query("language") language: String,
                       @Query("query") query: String,
                       @Query("page") page: Int,
                       @Query("include_adult") includeAdult: Boolean): Call<BaseEntity>

    @GET("search/tv")
    fun getSearchTv(@Query("api_key") apikey: String,
                    @Query("language") language: String,
                    @Query("query") query: String,
                    @Query("page") page: Int): Call<BaseEntity>

    @GET("movie/{movie_id}/images")
    fun getBackdrops(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String,
            @Query("include_image_language") imageLanguage: String): Call<GalleryPhotoModel>


    @GET("movie/{movie_id}")
    fun getMovieDetail(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String): Call<DetailInfo>


    @GET("movie/{movie_id}/credits")
    fun getMovieCrew(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String): Call<MovieCrew>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String,
            @Query("page") page: Int): Call<BaseEntity>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendedMovies(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String,
            @Query("page") page: Int): Call<BaseEntity>

    @GET("movie/{movie_id}/videos")
    fun getTrailers(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String): Call<Trailers>

    @GET("person/{movie_id}")
    fun getActorsDetail(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String): Call<ActorsEntity>

    @GET("person/{movie_id}/images")
    fun getActorsPhotos(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String): Call<ActorsPhotoEntity>

    @GET("person/{movie_id}/movie_credits")
    fun getActorsMovie(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String): Call<ActorsMoviesEntity>

    @GET("person/{movie_id}/tv_credits")
    fun getActorsTv(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String): Call<ActorsMoviesEntity>

    @GET("person/{movie_id}/tagged_images")
    fun getActorsTaggedImages(
            @Path("movie_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String,
            @Query("page") page: Int): Call<ActorsTaggedImages>

    @GET("person/{person_id}/")
    fun getActorsPictures(
            @Path("person_id") id: Int,
            @Query("api_key") apikey: String,
            @Query("language") language: String): Call<PersonP>

}
