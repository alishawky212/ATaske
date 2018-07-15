package com.example.alishawky.androidtask.Domain.Network;

import com.example.alishawky.androidtask.Entitties.BaseResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alishawky on 14/07/18.
 */

public interface ApiInterface {
    @GET("articles")
    Single<BaseResponse> getArticles(@Query("source") String source,
                                     @Query("apiKey") String apiKey);
}
