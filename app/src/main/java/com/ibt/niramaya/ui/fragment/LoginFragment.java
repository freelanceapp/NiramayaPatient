package com.ibt.niramaya.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;


@SuppressLint("ValidFragment")
public class LoginFragment extends BaseFragment implements OnClickListener {

    private static View view;
    private static Button loginButton;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private static EditText etMobileNumber, etAadharNumber;
    Context ctx;
    ConnectionDetector cd;
    //SessionManager sessionManager;

    @SuppressLint("ValidFragment")
    public LoginFragment(Context ctx) {
        this.ctx = ctx;
        //sessionManager = new SessionManager(ctx);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        loginButton = view.findViewById(R.id.loginBtn);
        etMobileNumber = view.findViewById(R.id.etMobileNumber);
        etAadharNumber = view.findViewById(R.id.etAadharNumber);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //  Toast.makeText(ctx, "Mobile Number", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etAadharNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(ctx, "Aadhar Number", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                userLoginApi();
                break;
        }

    }

    private void userLoginApi() {
        if (cd.isNetworkAvailable()) {
            final String strMobile = etMobileNumber.getText().toString();
            if (strMobile.isEmpty()) {
                etMobileNumber.setError("Mobile number should not be empty!!!");
            } else if (etMobileNumber.length() < 10) {
                etMobileNumber.setError("You have entered mobile number is invalid!!!");
            } else {
                RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.usersLogin(strMobile), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                AppPreference.setStringPreference(mContext, Constant.USER_CONTACT, strMobile);
                                OtpFragment otpFragment = new OtpFragment();
                                Bundle data = new Bundle();
                                data.putString("Mobile", strMobile);
                                otpFragment.setArguments(data);
                                fragmentManager
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                                        .add(R.id.login_frame, otpFragment).commit();
                                Alerts.show(mContext, jsonObject.toString());
                            } else {
                                Alerts.show(mContext, jsonObject.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
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


    /*private void checkValidation() {
        getEmailId = emailid_et.getText().toString();
        getPassword = password_et.getText().toString();
        Pattern p = Pattern.compile(ConstantData.regEx);
        Matcher m = p.matcher(getEmailId);
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                   "Enter both credentials.");
        } else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
        } else {
            boolean internet = connectionDetector.isConnected();
            if (internet) {
                loginUser();
            } else {
                new CustomToast().Show_Toast(getActivity(), view, ctx.getString(R.string.no_internet));
            }
        }
    }*/
}
