package com.example.alishawky.androidtask.Presentation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alishawky.androidtask.Entitties.Article;
import com.example.alishawky.androidtask.R;
import com.example.alishawky.androidtask.helper.DateParserUtils;

public class DetailActivity extends AppCompatActivity {

    private Button open_link_btn;
    private ImageView article_img;
    private TextView tv_title, tv_author, tv_description, tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initToolbar();

        Article mArticle = getIntent().getParcelableExtra("article");


        initView();
        updateView(mArticle);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateView(final Article article) {
        DateParserUtils.loadImage(article_img, article.getUrlToImage());
        tv_title.setText(article.getTitle());
        tv_date.setText(DateParserUtils.dateFormat(article.getPublishedAt()));
        tv_author.setText(String.format("By %s", article.getAuthor()));
        tv_description.setText(article.getDescription());

        open_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(article.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        open_link_btn = findViewById(R.id.open_link_btn);
        article_img = findViewById(R.id.article_img);
        tv_date = findViewById(R.id.date_txt);
        tv_title = findViewById(R.id.title_text);
        tv_author = findViewById(R.id.author_txt);
        tv_description = findViewById(R.id.desc_txt);
    }

}
