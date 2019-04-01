package com.ibt.niramaya.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ibt.niramaya.ui.fragment.AddUserFragment;
import com.ibt.niramaya.ui.fragment.blood_donation.CheckAvailbilityFragment;
import com.ibt.niramaya.ui.fragment.blood_donation.DonationFragment;
import com.ibt.niramaya.ui.fragment.blood_donation.RecievedFragment;

public class BloodDonationPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 3;

    public BloodDonationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0 :
                fragment = new CheckAvailbilityFragment();
                break;
            case 1 :
                fragment = new DonationFragment();
                break;
            case 2 :
                fragment = new RecievedFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0 :
                title = "Check Availability";
                break;
            case 1 :
                title = "Donations";
                break;
            case 2 :
                title = "Received";
                break;
        }
        return title;
    }

}
