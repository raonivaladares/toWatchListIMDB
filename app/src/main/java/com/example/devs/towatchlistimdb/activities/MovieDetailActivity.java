package com.example.devs.towatchlistimdb.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devs.towatchlistimdb.R;
import com.example.devs.towatchlistimdb.database.DatabaseHelper;
import com.example.devs.towatchlistimdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_movie_detail)
public class MovieDetailActivity extends AppCompatActivity {

    @Extra
    Movie movie;

    @Extra
    Boolean showButtonSave;

    @ViewById(R.id.save_movie)
    protected Button disable_button;

    @ViewById(R.id.poster)
    protected ImageView poster;

    @ViewById(R.id.title)
    protected TextView title;

    @ViewById(R.id.year)
    protected TextView year;

    @ViewById(R.id.runtime)
    protected TextView runtime;

    @ViewById(R.id.director)
    protected TextView director;

    @ViewById(R.id.genre)
    protected TextView genre;

    @ViewById(R.id.plot)
    protected TextView plot;

    @ViewById(R.id.actors)
    protected TextView actors;

    @ViewById(R.id.awards)
    protected TextView awards;

    @ViewById(R.id.metascore)
    protected TextView metascore;

    @ViewById(R.id.imdb_rating)
    protected TextView imdb_rating;

    @ViewById(R.id.imdb_votes)
    protected TextView imdb_votes;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Movie, Long> moviesDao;

    @AfterViews
    protected void afterViews() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        String posterUrl = movie.getPoster();

        if (posterUrl != null) {
            imageLoader.displayImage(movie.getPoster(), poster, options);
        }

        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        runtime.setText(movie.getRuntime());
        director.setText(movie.getDirector());
        genre.setText(movie.getGenre());
        plot.setText(movie.getPlot());
        actors.setText(movie.getActors());
        awards.setText(movie.getAwards());
        metascore.setText(movie.getMetaScore());
        imdb_rating.setText(movie.getImdbRating());
        imdb_votes.setText(movie.getImdbVotes());

        if (!showButtonSave) {
            disable_button.setVisibility(Button.GONE);
        }
    }

    public void buttonSaveOnClick(View view) {
        String msg = getString(R.string.successful_save);

        try {
            final List<Movie> movies = moviesDao.queryForEq(Movie.TITLE, movie.getTitle());

            if(!movies.isEmpty()) {
                final Movie localMoview = movies.get(0);
                movie.setId(localMoview.getId());
                msg = getString(R.string.exists_movie);
            }

            moviesDao.createOrUpdate(movie);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            MoviesListActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}