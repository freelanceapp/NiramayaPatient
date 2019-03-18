package com.ibt.niramaya.ui.fragment.blood_donation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.BloodDonationPagerAdapter;
import com.ibt.niramaya.adapter.InvoiceListAdapter;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;
import static com.ibt.niramaya.ui.activity.HomeActivity.txtTitle;

public class BloodDonationFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private ViewPager viewPager;
    private TabLayout tab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_blood_donation, container, false);
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        txtTitle.setText("Blood Donation");
        setViewPager();

    }

    @Override
    public void onClick(View v) {

    }

    private void setViewPager() {

        viewPager = rootView.findViewById(R.id.viewPager);
        tab = rootView.findViewById(R.id.tabs);
        if (viewPager != null) {
            BloodDonationPagerAdapter adapter = new BloodDonationPagerAdapter(getActivity().getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            tab.setupWithViewPager(viewPager);
        }

        assert viewPager != null;
        viewPager.setOffscreenPageLimit(3);

    }

}
