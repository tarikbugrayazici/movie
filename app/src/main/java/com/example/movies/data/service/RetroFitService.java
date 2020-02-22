package com.example.movies.data.service;

import com.example.movies.core.util.Constants;
import com.example.movies.data.entity.ActorsEntity;
import com.example.movies.data.entity.ActorsMoviesEntity;
import com.example.movies.data.entity.ActorsPhotoEntity;
import com.example.movies.data.entity.ActorsTaggedImages;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.DetailInfo;
import com.example.movies.data.entity.GalleryPhotoModel;
import com.example.movies.data.entity.MovieCrew;
import com.example.movies.data.entity.PersonP;
import com.example.movies.data.entity.Result;
import com.example.movies.data.entity.TabPersonEntity;
import com.example.movies.data.entity.Trailers;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tarik on 16.11.2019.
 */

public class RetroFitService {
    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit;
    API retrofitApi = RetroFitService.getRetrofit().create(API.class);

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void fetchMovieDetail(int id, final ResultCallBack resultCallBack) {
        Call<DetailInfo> call = retrofitApi.getMovieDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchBackDrops(int id, final ResultCallBack resultCallBack) {
        Call<GalleryPhotoModel> call = retrofitApi.getBackdrops(id, Constants.API_KEY, Constants.LANGUAGE_CODE, Constants.APPEND_TO_RESPONSE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchMovies(int page, final ResultCallBack resultCallBack) {
        Call<BaseEntity> call = retrofitApi.getMovie(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                Constants.SORT_BY_POPULARITY_DESC,
                Constants.INCLUDE_ADULT,
                Constants.INCLUDE_VIDEO,
                page
        );
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchActorsPictures(int id, final ResultCallBack resultCallBack) {
        Call<PersonP> call = retrofitApi.getActorsPictures(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchActorsDetail(int id, final ResultCallBack resultCallBack) {
        Call<ActorsTaggedImages> call = retrofitApi.getActorsTaggedImages(id, Constants.API_KEY, Constants.LANGUAGE_CODE, 1);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void service(int id, final ResultCallBack resultCallBack) {
        Call<ActorsEntity> call = retrofitApi.getActorsDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchPhotos(int id, final ResultCallBack resultCallBack) {
        Call<ActorsPhotoEntity> call = retrofitApi.getActorsPhotos(id, Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchActorsMovie(int id, final ResultCallBack resultCallBack) {
        Call<ActorsMoviesEntity> call = retrofitApi.getActorsMovie(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchActorsTv(int id, final ResultCallBack resultCallBack) {
        Call<ActorsMoviesEntity> call = retrofitApi.getActorsTv(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchActorMovies(int id, final ResultCallBack resultCallBack) {
        Call<MovieCrew> call = retrofitApi.getMovieCrew(id, Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchTrailers(int id, final ResultCallBack resultCallBack) {
        Call<Trailers> call = retrofitApi.getTrailers(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchInfo(int id, final ResultCallBack resultCallBack) {
        Call<DetailInfo> call = retrofitApi.getMovieDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchCrews(int id, final ResultCallBack resultCallBack) {
        Call<MovieCrew> call = retrofitApi.getMovieCrew(id, Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchRecommendedMovie(int id, final ResultCallBack resultCallBack, int page) {
        Call<BaseEntity> call = retrofitApi.getRecommendedMovies(id, Constants.API_KEY, Constants.LANGUAGE_CODE, page);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchSimilarMovies(int id, final ResultCallBack resultCallBack, int page) {
        Call<BaseEntity> call = retrofitApi.getSimilarMovies(id, Constants.API_KEY, Constants.LANGUAGE_CODE, page);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchTv(int page, ResultCallBack resultCallBack) {
        Call<BaseEntity> call = retrofitApi.getTv(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                Constants.SORT_BY_POPULARITY_DESC,
                page,
                Constants.TIME_ZONE,
                false);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchTabAllFragment(final ResultCallBack resultCallBack) {
        Call<BaseEntity> call = retrofitApi.getTrendAll(Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchTabMovie(final ResultCallBack resultCallBack) {
        Call<BaseEntity> call = retrofitApi.getTrendMovie(Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchPerson(final ResultCallBack resultCallBack) {
        Call<TabPersonEntity> call = retrofitApi.getTrendPerson(Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchTabTv(final ResultCallBack resultCallBack) {
        Call<BaseEntity> call = retrofitApi.getTrendTv(Constants.API_KEY);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchNamesMovie(final ResultCallBack resultCallBack, String text) {
        Call<BaseEntity> call = retrofitApi.getSearchMovie(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                text,
                1,
                Constants.INCLUDE_ADULT);
        ApiResponse.callRetrofit(call, resultCallBack);
    }

    public void fetchTvNames(final ResultCallBack resultCallBack, String text) {
        Call<BaseEntity> call = retrofitApi.getSearchTv(
                Constants.API_KEY,
                Constants.LANGUAGE_CODE,
                text,
                1);
        ApiResponse.callRetrofit(call, resultCallBack);
    }


    public interface ResultCallBack {
        void getResult(Result result);
    }
}
