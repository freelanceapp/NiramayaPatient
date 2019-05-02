package com.ibt.niramaya.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ibt.niramaya.ui.fragment.invoice_list.OpdInvoiceFragment;
import com.ibt.niramaya.ui.fragment.invoice_list.PathologyInvoiceFragment;
import com.ibt.niramaya.ui.fragment.invoice_list.PharmacyInvoiceFragment;

public class InvoicePagerAdapter extends FragmentPagerAdapter {
    private int COUNT = 3;

    public InvoicePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new PharmacyInvoiceFragment();
                break;
            case 1:
                fragment = new PathologyInvoiceFragment();
                break;
            case 2:
                fragment = new OpdInvoiceFragment();
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
        switch (position) {
            case 0:
                title = "Pharmacy";
                break;
            case 1:
                title = "Pathology";
                break;
            case 2:
                title = "Opd";
                break;
        }
        return title;
    }
}
