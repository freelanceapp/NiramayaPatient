package com.ibt.niramaya.firebase_service;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.retrofit.RetrofitApiClient;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.ConnectionDetector;


public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseIDService";
    public Context mContext;
    public RetrofitApiClient retrofitApiClient;
    public ConnectionDetector cd;

    public MyFirebaseInstanceIDService() {

    }

    public MyFirebaseInstanceIDService(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        Log.e("Token", "...");
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
        if (!(AppPreference.getStringPreference(mContext, Constant.FIREBASE_TOKEN)).isEmpty()) {
            tokenApi(refreshedToken);
        }
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        Log.e(TAG, "sendRegistrationToServer: " + token);
        if (!(AppPreference.getStringPreference(mContext, Constant.FIREBASE_TOKEN)).isEmpty()) {
            tokenApi(token);
        }

    }

    private void tokenApi(String strToken) {
        /*Looper.prepare();
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String strId = AppPreference.getStringPreference(mContext, Constant.User_Id);
        if (cd.isNetworkAvailable()) {
            RetrofitService.getSignData(new Dialog(mContext), retrofitApiClient.updateToken(strId, strToken, android_id, "user"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SignUpModel loginModal = (SignUpModel) result.body();
                    assert loginModal != null;
                    if (!loginModal.getError()) {
                        Alerts.show(mContext, loginModal.getMessage());
                    } else {
                        Alerts.show(mContext, loginModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }*/
    }
}
