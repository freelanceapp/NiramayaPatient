package com.ibt.niramaya.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ibt.niramaya.R;

public class FragmentUtils {

    private FragmentManager fragmentManager;

    public FragmentUtils(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.home_frame, fragment,
                        tag).commit();
    }
}
