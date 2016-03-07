package com.example.devs.towatchlistimdb.activities.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.devs.towatchlistimdb.R;
import com.example.devs.towatchlistimdb.util.Constants;
import com.example.devs.towatchlistimdb.util.Util;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity
public class BaseActivity extends AppCompatActivity {

    @Bean
    protected Util util;

    protected boolean checkNetworkStatus(View view) {
        boolean result = true;

        if (util != null  && !util.isNetworkAvailable(this)) {
            Snackbar.make(view, getString(R.string.connect_verify), Snackbar.LENGTH_LONG).setAction(Constants.ACTION, null).show();
            result = false;
        }
        return result;
    }

    protected void showSnackBarMsg(final View view, final String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(Constants.ACTION, null).show();
    }
}
