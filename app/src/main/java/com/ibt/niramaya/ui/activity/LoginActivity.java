package com.ibt.niramaya.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.ui.fragment.LoginFragment;
import com.ibt.niramaya.utils.BaseActivity;


public class LoginActivity extends BaseActivity {

    private static FragmentManager fragmentManager;
    Context ctx;
    //SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ctx = this;
        // checkSession();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.login_frame, new LoginFragment(ctx), Constant.LoginFragment).commit();
        }
    }

    /*private void checkSession() {
        sessionManager = new SessionManager(ctx);
        boolean login = AppPreference.getBooleanPreference(ctx, Constant.IS_LOGIN);
        if (login) {
            startActivity(new Intent(ctx, MainActivity.class));
            finish();
        }
    }*/

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.login_frame, new LoginFragment(ctx),
                        Constant.LoginFragment).commit();
    }

    @Override
    public void onBackPressed() {
        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = fragmentManager
                .findFragmentByTag(Constant.SignUpFragment);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(Constant.Otp_Fragment);

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}


