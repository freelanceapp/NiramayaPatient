package com.ibt.niramaya.ui.fragment.home_detail_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.ui.activity.HospitalDepartmentDetailActivity;
import com.ibt.niramaya.utils.BaseFragment;

public class DepartmentsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_departments, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        rootView.findViewById(R.id.txtA).setOnClickListener(this);
        rootView.findViewById(R.id.txtB).setOnClickListener(this);
        rootView.findViewById(R.id.txtC).setOnClickListener(this);
        rootView.findViewById(R.id.txtD).setOnClickListener(this);
        rootView.findViewById(R.id.txtE).setOnClickListener(this);
        rootView.findViewById(R.id.txtF).setOnClickListener(this);
        rootView.findViewById(R.id.txtG).setOnClickListener(this);
        rootView.findViewById(R.id.txtH).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtA:
            case R.id.txtB:
            case R.id.txtC:
            case R.id.txtD:
            case R.id.txtE:
            case R.id.txtF:
            case R.id.txtG:
            case R.id.txtH:
                startActivity(new Intent(mContext, HospitalDepartmentDetailActivity.class));
                break;
        }
    }
}
