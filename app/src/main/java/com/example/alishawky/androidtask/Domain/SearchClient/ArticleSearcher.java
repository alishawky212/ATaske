package com.example.alishawky.androidtask.Domain.SearchClient;

import com.example.alishawky.androidtask.Entitties.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleSearcher implements SearchListClient<Article> {
    @Override
    public List<Article> filterList(List<Article> listToBeSearched, String query) {
        List<Article> searchedList = new ArrayList<>();
        for (Article startup : listToBeSearched) {
            String stringToBeSearched = startup.getTitle() + startup.getAuthor();
            if (stringToBeSearched.toLowerCase().contains(query.toLowerCase())) {
                searchedList.add(startup);
            }
        }
        return searchedList;
    }
}
