package com.example.movies.data.service

import com.example.movies.core.util.Constants
import com.example.movies.data.entity.ActorsEntity
import com.example.movies.data.entity.ActorsMoviesEntity
import com.example.movies.data.entity.ActorsPhotoEntity
import com.example.movies.data.entity.ActorsTaggedImages
import com.example.movies.data.entity.BaseEntity
import com.example.movies.data.entity.DetailInfo
import com.example.movies.data.entity.GalleryPhotoModel
import com.example.movies.data.entity.MovieCrew
import com.example.movies.data.entity.PersonP
import com.example.movies.data.entity.Result
import com.example.movies.data.entity.TabPersonEntity
import com.example.movies.data.entity.Trailers

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Tarik on 16.11.2019.
 */

class RetroFitService {
    internal var retrofitApi = RetroFitService.getRetrofit().create(API::class.java)

    fun fetchMovieDetail(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getMovieDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchBackDrops(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getBackdrops(id, Constants.API_KEY, Constants.LANGUAGE_CODE, Constants.APPEND_TO_RESPONSE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchMovies(page: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getMovie(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                Constants.SORT_BY_POPULARITY_DESC,
                Constants.INCLUDE_ADULT,
                Constants.INCLUDE_VIDEO,
                page
        )
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchActorsPictures(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getActorsPictures(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchActorsDetail(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getActorsTaggedImages(id, Constants.API_KEY, Constants.LANGUAGE_CODE, 1)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun service(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getActorsDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchPhotos(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getActorsPhotos(id, Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchActorsMovie(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getActorsMovie(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchActorsTv(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getActorsTv(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchActorMovies(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getMovieCrew(id, Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchTrailers(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getTrailers(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchInfo(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getMovieDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchCrews(id: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getMovieCrew(id, Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchRecommendedMovie(id: Int, resultCallBack: ResultCallBack, page: Int) {
        val call = retrofitApi.getRecommendedMovies(id, Constants.API_KEY, Constants.LANGUAGE_CODE, page)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchSimilarMovies(id: Int, resultCallBack: ResultCallBack, page: Int) {
        val call = retrofitApi.getSimilarMovies(id, Constants.API_KEY, Constants.LANGUAGE_CODE, page)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchTv(page: Int, resultCallBack: ResultCallBack) {
        val call = retrofitApi.getTv(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                Constants.SORT_BY_POPULARITY_DESC,
                page,
                Constants.TIME_ZONE,
                false)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchTabAllFragment(resultCallBack: ResultCallBack) {
        val call = retrofitApi.getTrendAll(Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchTabMovie(resultCallBack: ResultCallBack) {
        val call = retrofitApi.getTrendMovie(Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchPerson(resultCallBack: ResultCallBack) {
        val call = retrofitApi.getTrendPerson(Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchTabTv(resultCallBack: ResultCallBack) {
        val call = retrofitApi.getTrendTv(Constants.API_KEY)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchNamesMovie(resultCallBack: ResultCallBack, text: String) {
        val call = retrofitApi.getSearchMovie(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                text,
                1,
                Constants.INCLUDE_ADULT)
        ApiResponse.callRetrofit(call, resultCallBack)
    }

    fun fetchTvNames(resultCallBack: ResultCallBack, text: String) {
        val call = retrofitApi.getSearchTv(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                text,
                1)
        ApiResponse.callRetrofit(call, resultCallBack)
    }


    interface ResultCallBack {
        fun getResult(result: Result<*>)
    }

    companion object {
        private val BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }
}
