package com.ibt.niramaya.ui.fragment.home_detail_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.HospitalDepartmentsListAdapter;
import com.ibt.niramaya.modal.home.HospitalDatum;
import com.ibt.niramaya.modal.hospital_detail.HospitalSpecialization;
import com.ibt.niramaya.ui.activity.HospitalDepartmentDetailActivity;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.ArrayList;

public class DepartmentsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private RecyclerView rvDepartments;

    private ArrayList<HospitalSpecialization> hospitalSpecializationList = new ArrayList<>();
    private HospitalDatum hospitalData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_departments, container, false);
        init();
        return rootView;
    }

    private void init() {

        hospitalSpecializationList = getArguments().getParcelableArrayList("DEPARTMENTS");
        hospitalData = getArguments().getParcelable("HOSPITAL");

        mContext = getActivity();
        rvDepartments = rootView.findViewById(R.id.rvDepartments);
        rvDepartments.setLayoutManager(new GridLayoutManager(mContext,2));
        HospitalDepartmentsListAdapter departementAdapter = new HospitalDepartmentsListAdapter(
                hospitalSpecializationList, mContext, DepartmentsFragment.this);
        rvDepartments.setAdapter(departementAdapter);
        departementAdapter.notifyDataSetChanged();

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
            case R.id.tvDepartments:
                int tag = (int) v.getTag();
                startActivity(new Intent(mContext, HospitalDepartmentDetailActivity.class)
                .putExtra("DEPARTMENTS", hospitalSpecializationList.get(tag))
                .putExtra("HOSPITAL", hospitalData));
                break;
        }
    }
}
