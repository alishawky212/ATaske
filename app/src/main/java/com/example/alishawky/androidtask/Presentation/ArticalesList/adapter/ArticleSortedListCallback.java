package com.example.alishawky.androidtask.Presentation.ArticalesList.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import com.example.alishawky.androidtask.Entitties.Article;

import java.util.Comparator;

public class ArticleSortedListCallback extends SortedList.Callback<Article> {
    private RecyclerView.Adapter adapter;
    private Comparator<Article> comparator;

    public ArticleSortedListCallback(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        comparator = new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getPublishedAtDate().compareTo(o2.getPublishedAtDate());
            }
        };
    }

    @Override
    public int compare(Article o1, Article o2) {
        return comparator.compare(o1, o2);
    }

    @Override
    public void onChanged(int position, int count) {
        adapter.notifyItemRangeChanged(position, count);
    }

    @Override
    public boolean areContentsTheSame(Article oldItem, Article newItem) {
        return oldItem.getPublishedAtDate().equals(newItem.getPublishedAtDate());
    }

    @Override
    public boolean areItemsTheSame(Article item1, Article item2) {
        return item1.getPublishedAtDate().equals(item2.getPublishedAtDate());
    }

    @Override
    public void onInserted(int position, int count) {
        adapter.notifyItemRangeInserted(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        adapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        adapter.notifyItemRangeRemoved(fromPosition, toPosition);
    }

}
