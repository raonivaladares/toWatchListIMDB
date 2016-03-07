package com.example.devs.towatchlistimdb.util;

import android.content.Context;

import org.androidannotations.annotations.EBean;

import java.net.InetAddress;

@EBean
public class Util {

    public boolean isNetworkAvailable(final Context context) {
        try {
            InetAddress ipAddress = InetAddress.getByName("google.com");
            return !ipAddress.equals("");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
