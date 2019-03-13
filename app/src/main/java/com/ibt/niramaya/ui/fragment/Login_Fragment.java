package com.ibt.niramaya.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.utils.ConnectionDetector;



@SuppressLint("ValidFragment")
public class Login_Fragment extends Fragment implements OnClickListener {

    private static View view;
    private static Button loginButton;
    private static TextView forgotPassword;
    private static LinearLayout signUp;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    ProgressBar loginProgress;
    String getEmailId, getPassword;

    Context ctx;
    ConnectionDetector connectionDetector;
    //SessionManager sessionManager;

    @SuppressLint("ValidFragment")
    public Login_Fragment(Context ctx) {
        this.ctx = ctx;
        //sessionManager = new SessionManager(ctx);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        loginButton = view.findViewById(R.id.loginBtn);
       // loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);

            forgotPassword.setTextColor(csl);
            //show_hide_password.setTextColor(csl);
            //signUp.setBackgroundResource(csl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                signUp.setBackgroundTintList(csl);
            }

        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
       /// forgotPassword.setOnClickListener(this);
        //signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
               // checkValidation();
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.login_frame, new SignUp_Fragment(ctx),
                                Constant.SignUpFragment).commit();
                break;
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
