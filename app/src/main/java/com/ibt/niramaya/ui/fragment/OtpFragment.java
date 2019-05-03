package com.ibt.niramaya.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.otp_verifacation_modal.OtpVerificationMainModal;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.HomeActivity;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;
import com.ibt.niramaya.utils.pinview.Pinview;

import retrofit2.Response;

public class OtpFragment extends BaseFragment implements OnClickListener {
    private ConnectionDetector cd;
    private View view;
    private TextView submit;
    private Pinview pinview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.otp_layout, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        initViews();
        setListeners();
        return view;
    }

    // Initialize the views
    private void initViews() {
        submit = view.findViewById(R.id.btnSubmit);
        pinview = view.findViewById(R.id.pinview1);
    }

    private void setListeners() {
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            userOtpVerificationApi();
        }
    }

    private void userOtpVerificationApi() {
        if (cd.isNetworkAvailable()) {
            assert getArguments() != null;
            final String strMobile = getArguments().getString("Mobile");
            String strOtpTest = pinview.getValue();
            if (strOtpTest.isEmpty()) {
                Alerts.show(mContext, "OTP should not be empty.");
            } else {
                RetrofitService.getOtpResponse(new Dialog(mContext), retrofitApiClient.fatchOtp(strMobile, strOtpTest), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        OtpVerificationMainModal responseBody = (OtpVerificationMainModal) result.body();
                        assert responseBody != null;
                        if (!responseBody.getError()) {
                            String strUserId = responseBody.getUser().getId();
                            String strContact = responseBody.getUser().getUserContact();
                            AppPreference.setStringPreference(mContext, Constant.USER_CONTACT, strContact);
                            AppPreference.setStringPreference(mContext, Constant.USER_ID, strUserId);
                            startActivity(new Intent(mContext, HomeActivity.class));
                            Alerts.show(mContext, responseBody.getMessage());
                        } else {
                            Alerts.show(mContext, responseBody.getMessage());
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            }

        } else {
            cd.show(mContext);
        }
    }

}