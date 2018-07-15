package com.example.alishawky.androidtask.Presentation.ArticalesList;

import com.example.alishawky.androidtask.Domain.Repo.RepoContract;
import com.example.alishawky.androidtask.Domain.SearchClient.ArticleSearcher;
import com.example.alishawky.androidtask.Entitties.Article;
import com.example.alishawky.androidtask.Entitties.BaseResponse;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by alishawky on 14/07/18.
 */

public class MainListPresenter implements ArticlesListContract.Presenter {

    private RepoContract repository;
    private Disposable disposable;
    private ArticlesListContract.View view;
    private ArticleSearcher articleSearcher;

    public MainListPresenter(RepoContract repository) {
        this.repository = repository;
        articleSearcher = new ArticleSearcher();
    }

    @Override
    public void loadArticles(String source, String apiKey) {

        view.Loading(true);

        repository.getArticles(source, apiKey)
                .subscribe(new SingleObserver<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        if (baseResponse.getStatus().equalsIgnoreCase("ok")) {
                            view.Loading(false);
                            view.showArticles(baseResponse.getArticles());
                        } else {
                            view.Loading(false);
                            view.ShowError(baseResponse.getStatus());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.Loading(false);
                        view.ShowError(e.getLocalizedMessage());
                    }
                });

    }

    @Override
    public void setView(ArticlesListContract.View view) {
        this.view = view;
    }

    @Override
    public void searchOn(ArrayList<Article> articles, String query) {
        view.showSearchResult(articleSearcher.filterList(articles, query));
    }

    @Override
    public void destroy() {
        this.view = null;
        this.disposable.dispose();
    }
}
