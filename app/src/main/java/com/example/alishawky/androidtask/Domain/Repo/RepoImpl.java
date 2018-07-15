package com.example.alishawky.androidtask.Domain.Repo;

import com.example.alishawky.androidtask.Domain.Network.ApiInterface;
import com.example.alishawky.androidtask.Entitties.BaseResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by alishawky on 14/07/18.
 */

public class RepoImpl implements RepoContract {

    private Retrofit retrofit;

    public RepoImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Single<BaseResponse> getArticles(String source, String apiKey) {
        return retrofit.create(ApiInterface.class)
                .getArticles(source, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
