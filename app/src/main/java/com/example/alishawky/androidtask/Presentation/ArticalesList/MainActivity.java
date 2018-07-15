package com.example.alishawky.androidtask.Presentation.ArticalesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alishawky.androidtask.Domain.Network.ApiHelper;
import com.example.alishawky.androidtask.Domain.Repo.RepoContract;
import com.example.alishawky.androidtask.Domain.Repo.RepoImpl;
import com.example.alishawky.androidtask.Domain.StaticData.NavItemsGenerator;
import com.example.alishawky.androidtask.Entitties.Article;
import com.example.alishawky.androidtask.Entitties.NavItem;
import com.example.alishawky.androidtask.Presentation.ArticalesList.adapter.ArticlesAdapter;
import com.example.alishawky.androidtask.Presentation.DetailActivity;
import com.example.alishawky.androidtask.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class MainActivity extends AppCompatActivity implements ArticlesAdapter.OnClickCardViewListener
        , ArticlesListContract.View {

    private Toolbar toolbar;

    private DrawerLayout drawer;

    private ArticlesAdapter articlesAdapter;

    private ProgressBar progressBar;

    private RecyclerView articlesRecyclerView;

    private ArticlesListContract.Presenter presenter;

    private ArrayList<Article> articles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavDrawer();
        articlesAdapter = new ArticlesAdapter();
        articlesAdapter.setCardViewClickListener(this);
        initArticleRecyclerView();
        initPresenter();

        progressBar = findViewById(R.id.pb);

        articles = new ArrayList<>();
        presenter.loadArticles(getString(R.string.source)
                , getString(R.string.apiKey));
        initSearchView();
    }

    private void initSearchView() {
        SearchView searchView = findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (articles != null) {
                    presenter.searchOn(articles, newText);
                    articlesRecyclerView.scrollToPosition(0);
                }
                return false;
            }
        });
    }

    private void initArticleRecyclerView() {
        articlesRecyclerView = findViewById(R.id.articles_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        articlesRecyclerView.setLayoutManager(linearLayoutManager);
        articlesRecyclerView.setAdapter(articlesAdapter);
        FadeInLeftAnimator fadeInLeftAnimator = new FadeInLeftAnimator();
        fadeInLeftAnimator.setAddDuration(500);
        articlesRecyclerView.setItemAnimator(fadeInLeftAnimator);
    }

    private void initNavDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initNavRecycler();
    }

    private void initNavRecycler() {
        RecyclerView navRecycler = findViewById(R.id.nav_recycler);
        NavAdapter adapter = new NavAdapter(NavItemsGenerator.provideNavItems());
        navRecycler.setAdapter(adapter);
        navRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.setNavItemSelectedListener(new NavAdapter.NavItemSelectedListener() {
            @Override
            public void onItemSelected(NavItem navItem) {
                Toast.makeText(MainActivity.this, navItem.getTitle(), Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(Gravity.START);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initPresenter() {
        RepoContract repoContract = new RepoImpl(ApiHelper.getClient(getString(R.string.base_url)));
        presenter = new MainListPresenter(repoContract);
        presenter.setView(this);
    }

    @Override
    public void onClickCardView(Article article, ImageView imageView) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("article", article);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,
                        imageView,
                        ViewCompat.getTransitionName(imageView));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void ShowError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Loading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void showSearchResult(List<Article> articles) {
        articlesAdapter.addFilteredList(articles);
    }

    @Override
    public void showArticles(ArrayList<Article> articles) {
        this.articles = new ArrayList<>(articles);
        articlesAdapter.add(this.articles);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}