package com.example.movies.data.service;

import android.support.annotation.NonNull;

import com.example.movies.data.entity.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ApiResponse {

    static <T> void callRetrofit(Call<T> call, final RetroFitService.ResultCallBack resultCallBack) {

        final Result<T> result = new Result<>();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.body() != null) {
                    result.setData(response.body());
                } else {
                    result.setErrorMessage(response.message());
                }

                resultCallBack.getResult(result);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}

