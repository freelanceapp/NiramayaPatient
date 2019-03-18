package com.ibt.niramaya;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ibt.niramaya.ui.fragment.blood_donation.CheckAvailbilityFragment;
import com.ibt.niramaya.ui.fragment.blood_donation.DonationFragment;
import com.ibt.niramaya.ui.fragment.blood_donation.RecievedFragment;
import com.ibt.niramaya.utils.BaseActivity;

public class PushpendraTestActivity extends BaseActivity {
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushpendra_test);

        FrameLayout frameLayout = findViewById(R.id.frame_test_fragment);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null){
         fragmentManager.beginTransaction()
         .replace(R.id.frame_test_fragment,new RecievedFragment()).commit();

        }
    }
}
