package com.ibt.niramaya.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.ui.fragment.home_detail_fragment.DepartmentsFragment;
import com.ibt.niramaya.ui.fragment.home_detail_fragment.FacilitiesFragment;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.FragmentUtils;

public class HospitalDetailActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private FragmentUtils fragmentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        fragmentUtils.replaceFragment(new FacilitiesFragment(), Constant.FacilitiesFragment, R.id.frameLayout);

        ((TextView) findViewById(R.id.txtTitle)).setText("Hospital");
        findViewById(R.id.imgBack).setOnClickListener(this);

        findViewById(R.id.txtFacilities).setOnClickListener(this);
        findViewById(R.id.txtDepartments).setOnClickListener(this);
        findViewById(R.id.txtDoctors).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtFacilities:
                onTextviewClick(R.id.txtFacilities, R.id.txtDepartments, R.id.txtDoctors);
                fragmentUtils.replaceFragment(new FacilitiesFragment(), Constant.FacilitiesFragment, R.id.frameLayout);
                break;
            case R.id.txtDepartments:
                onTextviewClick(R.id.txtDepartments, R.id.txtFacilities, R.id.txtDoctors);
                fragmentUtils.replaceFragment(new DepartmentsFragment(), Constant.DepartmentsFragment, R.id.frameLayout);
                break;
            case R.id.txtDoctors:
                onTextviewClick(R.id.txtDoctors, R.id.txtFacilities, R.id.txtDepartments);
                fragmentUtils.replaceFragment(new FacilitiesFragment(), Constant.FacilitiesFragment, R.id.frameLayout);
                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }

    private void onTextviewClick(int a, int b, int c) {
        ((TextView) findViewById(a)).setBackground(getResources().getDrawable(R.drawable.layout_bg5));
        ((TextView) findViewById(b)).setBackground(getResources().getDrawable(R.drawable.layout_bg14));
        ((TextView) findViewById(c)).setBackground(getResources().getDrawable(R.drawable.layout_bg14));

        ((TextView) findViewById(a)).setTextColor(getResources().getColor(R.color.white));
        ((TextView) findViewById(b)).setTextColor(getResources().getColor(R.color.text_color_c));
        ((TextView) findViewById(c)).setTextColor(getResources().getColor(R.color.text_color_c));
    }
}
