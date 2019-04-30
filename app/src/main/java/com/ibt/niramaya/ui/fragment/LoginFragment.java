package com.ibt.niramaya.ui.fragment;

import android.annotation.SuppressLint;
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
import com.ibt.niramaya.utils.ConnectionDetector;


@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment implements OnClickListener {

    private static View view;
    private static Button loginButton;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private static EditText etMobileNumber, etAadharNumber;
    Context ctx;
    ConnectionDetector connectionDetector;
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
        // loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Toast.makeText(ctx, "Mobile Number", Toast.LENGTH_SHORT).show();

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
        /// forgotPassword.setOnClickListener(this);
        //signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                // checkValidation();
                Fragment frag = new OtpFragment();
                Bundle args = new Bundle();
                    args.putString("OTP", "111111");
                    frag.setArguments(args);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.login_frame, frag,
                                Constant.Otp_Fragment).commit();
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
