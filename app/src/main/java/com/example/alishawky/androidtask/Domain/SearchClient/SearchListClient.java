package com.example.alishawky.androidtask.Domain.SearchClient;

import java.util.List;

public interface SearchListClient<T> {
    List<T> filterList(List<T> listToBeSearched, String query);
}
