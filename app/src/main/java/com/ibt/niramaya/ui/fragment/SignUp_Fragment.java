package com.ibt.niramaya.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.utils.ConnectionDetector;

@SuppressLint("ValidFragment")
public class SignUp_Fragment extends Fragment implements OnClickListener {

    private static View view;
    private static EditText userName_et, emailId_et, mobileNumber_et, displayname_et,
            password_et, confirmPassword_et;
    private static LinearLayout login_tv;
    private static Button signUpButton_bt;
    private static CheckBox terms_conditions_cb;
    private ProgressBar progressBar;
    String getUserName, getdisplayName, getEmailId, getPassword, getMobileNumber, getConfirmPassword;

    Context ctx;
    ConnectionDetector connectionDetector;

    @SuppressLint("ValidFragment")
    public SignUp_Fragment(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        userName_et = (EditText) view.findViewById(R.id.userName);
        emailId_et = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber_et = (EditText) view.findViewById(R.id.mobileNumber);
        password_et = (EditText) view.findViewById(R.id.password);
        confirmPassword_et = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton_bt = (Button) view.findViewById(R.id.signUpBtn);
        //login_tv = (LinearLayout) view.findViewById(R.id.already_user);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

           // login_tv.setTextColor(csl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //login_tv.setBackgroundTintList(csl);
            }
            terms_conditions_cb.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton_bt.setOnClickListener(this);
        //login_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.login_frame, new OtpFragment(),
                                Constant.Otp_Fragment).commit();
                break;

          /*  case R.id.already_user:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.login_frame, new LoginFragment(ctx),
                                Constant.SignUpFragment).commit();
                break;*/
        }

    }

   /* private void checkValidation() {
        getUserName = userName_et.getText().toString();
        getdisplayName = displayname_et.getText().toString();
        getEmailId = emailId_et.getText().toString();
        getMobileNumber = mobileNumber_et.getText().toString();
        getPassword = password_et.getText().toString();
        getConfirmPassword = confirmPassword_et.getText().toString();

        Pattern p = Pattern.compile(ConstantData.regEx);
        Matcher m = p.matcher(getEmailId);

        if (getUserName.equals("") || getUserName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");

            // Check if email id valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");

            // Check if both password_et should be equal
        else if (!getConfirmPassword.equals(getPassword))
            new CustomToast().Show_Toast(getActivity(), view,
                    "Both password doesn't match.");

            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions_cb.isChecked())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Please select Terms and Conditions.");

            // Else do signup or do your stuff
        else {
            boolean internet = connectionDetector.isConnected();
            if (internet) {
                regsiterUser();
            } else {
                new CustomToast().Show_Toast(getActivity(), view,
                        ctx.getString(R.string.no_internet));
            }
        }
    }*/


}
