package com.example.alishawky.androidtask.Presentation.ArticalesList.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alishawky.androidtask.Entitties.Article;
import com.example.alishawky.androidtask.R;
import com.example.alishawky.androidtask.helper.DateParserUtils;

import java.util.List;

/**
 * Created by alishawky on 14/07/18.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private SortedList<Article> articles;
    private OnClickCardViewListener mListener;

    public ArticlesAdapter() {
        articles = new SortedList<>(Article.class, new ArticleSortedListCallback(this));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Article article = articles.get(position);

        DateParserUtils.loadImage(holder.article_img, article.getUrlToImage());
        holder.article_title.setText(article.getTitle());
        holder.author_tv.setText(String.format("By %s", article.getAuthor()));
        holder.date_tv.setText(DateParserUtils.dateFormat(article.getPublishedAt()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickCardView(article, holder.article_img);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        } else {
            return 0;
        }
    }

    public void setCardViewClickListener(OnClickCardViewListener listener) {
        this.mListener = listener;
    }

    public void add(List<Article> startupList) {
        articles.beginBatchedUpdates();
        for (int i = 0; i < startupList.size(); i++) {
            articles.add(startupList.get(i));
        }
        articles.endBatchedUpdates();
        notifyItemRangeInserted(0, startupList.size());
    }

    public void addFilteredList(List<Article> startups) {
        articles.replaceAll(startups);
    }

    public interface OnClickCardViewListener {
        void onClickCardView(Article article, ImageView imageView);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView article_img;
        private TextView article_title, author_tv, date_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            article_img = itemView.findViewById(R.id.article_img);
            article_title = itemView.findViewById(R.id.title_txt);
            author_tv = itemView.findViewById(R.id.author_txt);
            date_tv = itemView.findViewById(R.id.date_txt);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
