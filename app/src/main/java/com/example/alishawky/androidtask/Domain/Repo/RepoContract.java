package com.example.alishawky.androidtask.Domain.Repo;

import com.example.alishawky.androidtask.Entitties.BaseResponse;

import io.reactivex.Single;

/**
 * Created by alishawky on 14/07/18.
 */

public interface RepoContract {
    Single<BaseResponse> getArticles(String source, String apiKey);
}
