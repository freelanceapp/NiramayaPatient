package com.ibt.niramaya.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.HospitalCategoryAdapter;
import com.ibt.niramaya.adapter.HospitalListAdapter;
import com.ibt.niramaya.adapter.ViewPagerAdapter;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private ViewPagerAdapter adapter;
    private List<Integer> successImagesList = new ArrayList<>();

    private HospitalListAdapter hospitalListAdapter, popularHospitalAdapter;
    private List<String> hospitalList = new ArrayList<>();
    private List<String> popularHospitalList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return rootView;
    }

    private void init() {
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        initViewPager();
        nearByHospitalList();
        popularHospitalList();
        hospitalCategoryList();
    }

    private void initViewPager() {
        pagerSuccess = rootView.findViewById(R.id.viewPager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pagerSuccess, true);

        successImagesList.clear();
        for (int i = 0; i < 6; i++) {
            successImagesList.add(R.drawable.default_img_pager);
        }

        adapter = new ViewPagerAdapter(mContext, successImagesList, this);
        pagerSuccess.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                pagerSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);
    }

    public void pagerSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 3000);
        } else {
            pagerSuccess.setCurrentItem(0);
            imageHandler.postDelayed(imageRunnable, 3000);
        }
    }

    /**********************************************/
    /*
     * Hospital list
     * */
    private void nearByHospitalList() {
        for (int i = 0; i < 10; i++) {
            hospitalList.add("Name");
        }
        RecyclerView recyclerViewNear = rootView.findViewById(R.id.recyclerViewNear);
        recyclerViewNear.setHasFixedSize(true);
        recyclerViewNear.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        hospitalListAdapter = new HospitalListAdapter(hospitalList, mContext, this);
        recyclerViewNear.setAdapter(hospitalListAdapter);
    }

    /*
     * Hospital list
     * */
    private void popularHospitalList() {
        for (int i = 0; i < 10; i++) {
            popularHospitalList.add("Name");
        }
        RecyclerView recyclerViewPopularity = rootView.findViewById(R.id.recyclerViewPopularity);
        recyclerViewPopularity.setHasFixedSize(true);
        recyclerViewPopularity.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        popularHospitalAdapter = new HospitalListAdapter(popularHospitalList, mContext, this);
        recyclerViewPopularity.setAdapter(popularHospitalAdapter);
        popularHospitalAdapter.notifyDataSetChanged();
    }

    /*
     * Hospital Category list
     * */
    private void hospitalCategoryList() {
        for (int i = 0; i < 10; i++) {
            popularHospitalList.add("Name");
        }
        RecyclerView recyclerViewCategory = rootView.findViewById(R.id.recyclerViewCategory);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(getActivity(),
                2, GridLayoutManager.HORIZONTAL, false));
        HospitalCategoryAdapter popularHospitalAdapter = new HospitalCategoryAdapter(popularHospitalList, mContext, this);
        recyclerViewCategory.setAdapter(popularHospitalAdapter);
        popularHospitalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
