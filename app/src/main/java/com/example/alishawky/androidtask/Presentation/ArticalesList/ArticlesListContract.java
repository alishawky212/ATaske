package com.example.alishawky.androidtask.Presentation.ArticalesList;

import com.example.alishawky.androidtask.Entitties.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alishawky on 14/07/18.
 */

public interface ArticlesListContract {

    interface View {
        void ShowError(String message);

        void Loading(boolean isLoading);

        void showArticles(ArrayList<Article> articles);

        void showSearchResult(List<Article> articles);
    }

    interface Presenter {
        void loadArticles(String source, String apiKey);

        void setView(View view);

        void searchOn(ArrayList<Article> articles, String query);

        void destroy();
    }
}
