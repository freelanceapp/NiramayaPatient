package com.ibt.niramaya.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectionDetector {

    private Context mContext;

    public ConnectionDetector(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo==null || !activeNetworkInfo.isConnected()){
            Toast.makeText(mContext, "Internet connection not available!!!", Toast.LENGTH_SHORT).show();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}