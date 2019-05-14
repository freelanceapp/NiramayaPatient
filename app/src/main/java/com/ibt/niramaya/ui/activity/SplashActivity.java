package com.ibt.niramaya.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.firebase_service.MyFirebaseInstanceIDService;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;

public class SplashActivity extends BaseActivity {

    MyFirebaseInstanceIDService firebaseIDService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseIDService = new MyFirebaseInstanceIDService(mContext);


        Log.e("Login" , String.valueOf(AppPreference.getBooleanPreference(mContext , Constant.Is_Login)));
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Firebase ", "Refreshed token: " + refreshedToken);

        init();
    }

    private void init() {
        final boolean isLogin = AppPreference.getBooleanPreference(mContext, Constant.Is_Login);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                    finish();
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}
