package com.example.devs.towatchlistimdb.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devs.towatchlistimdb.R;
import com.example.devs.towatchlistimdb.activities.base.BaseActivity;
import com.example.devs.towatchlistimdb.models.Movie;
import com.example.devs.towatchlistimdb.util.Constants;
import com.example.devs.towatchlistimdb.webservice.RestClient;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@EActivity(R.layout.activity_search)
public class SearchActivity extends BaseActivity {

    ProgressDialog progress;

    @ViewById(R.id.editText)
    EditText editText;

    @RestService
    protected RestClient restClient;


    @Click(R.id.button_search_movie)
    public void onClickSearchMovie() {
        searchMovie();
    }

    @Background
    protected void searchMovie() {
        if (checkNetworkStatus(editText)) {
            final View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            try {
                getMovie();
            } catch (Exception e) {
                e.getMessage();
                showSnackBarMsg(editText, getString(R.string.generic_error));
            }
        }
    }

    @Background
    protected void getMovie() {
        try {
            String title = URLEncoder.encode(editText.getText().toString(), Constants.UTF8);
            if (!editText.getText().toString().isEmpty()) {
                showProgressDialog();
                Movie movie = restClient.searchMovie(title);
                dismissProgressDialog();
                if(movie.getResponse().equals(Constants.FALSE)) {
                    showSnackBarMsg(editText, movie.getError());
                } else {
                    openDetails(movie);
                }
            } else {
                showSnackBarMsg(editText, getString(R.string.field_not_filled));
            }

        } catch (UnsupportedEncodingException e) {
            showSnackBarMsg(editText, getString(R.string.search_not_found));
        }
    }

    @UiThread
    public void showProgressDialog() {
        progress = ProgressDialog.show(this, getString(R.string.loading),
                getString(R.string.wait), true);
    }

    @UiThread
    public void dismissProgressDialog() {
        if(progress != null) {
            progress.dismiss();
        }
    }

    @UiThread
    public void openDetails(Movie movie) {
        MovieDetailActivity_.intent(this)
                .movie(movie)
                .showButtonSave(true)
                .start();
    }
}
