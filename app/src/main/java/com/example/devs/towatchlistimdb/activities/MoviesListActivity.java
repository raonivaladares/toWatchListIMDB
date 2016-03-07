package com.example.devs.towatchlistimdb.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.devs.towatchlistimdb.R;
import com.example.devs.towatchlistimdb.activities.base.BaseActivity;
import com.example.devs.towatchlistimdb.adapter.DividerItemDecoration;
import com.example.devs.towatchlistimdb.adapter.MovieRecycleViewAdapter;
import com.example.devs.towatchlistimdb.database.DatabaseHelper;
import com.example.devs.towatchlistimdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_movies)
public class MoviesListActivity extends BaseActivity {

    private MovieRecycleViewAdapter adapter;

    @ViewById(R.id.recycleView)
    protected RecyclerView recyclerView;

    @ViewById(R.id.textViewLabel)
    protected TextView textViewLabel;

    @OrmLiteDao(helper = DatabaseHelper.class)
    protected Dao<Movie, Long> moviesDao;

    @AfterViews
    protected void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final List<Movie> movies = getDataSet();

        if(movies != null) {
            final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new MovieRecycleViewAdapter(movies);
            recyclerView.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);
        } else {
            recyclerView.setVisibility(View.GONE);
            textViewLabel.setVisibility(View.VISIBLE);
        }

        adapter.setOnItemClickListener(new MovieRecycleViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Movie movie;
                try {
                    movie = adapter.getItem(position);
                    if (movie != null) {
                        MovieDetailActivity_.intent(v.getContext())
                                .movie(movie)
                                .showButtonSave(false)
                                .start();
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                    showSnackBarMsg(textViewLabel, getString(R.string.generic_error));
                }
            }
        });

    }

    @Click(R.id.add_movie)
    public void onClickAddMovie(View view) {
        SearchActivity_.intent(this).start();
    }

    private List<Movie> getDataSet() {
        List<Movie> movies = null;
        try {
            movies =  moviesDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            showSnackBarMsg(textViewLabel, getString(R.string.generic_error));
        }
        return movies;
    }
}
