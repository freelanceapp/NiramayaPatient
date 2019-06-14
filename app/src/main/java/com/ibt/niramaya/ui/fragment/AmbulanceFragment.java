package com.ibt.niramaya.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.patient_adapter.PatientListAdapter;
import com.ibt.niramaya.modal.patient_modal.PaitentProfile;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class AmbulanceFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private ConnectionDetector cd;
    private PatientListAdapter patientListAdapter;
    private List<PaitentProfile> patientList = new ArrayList<>();
    private RecyclerView rvPatientList;
    private LinearLayout llAddPatient;
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ambulance, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();


    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        rvPatientList = rootView.findViewById(R.id.rvPatientList);
        (rootView.findViewById(R.id.cvNormal)).setOnClickListener(this);
        (rootView.findViewById(R.id.cvEmergency)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvNormal:
                normalCall();
                break;
            case R.id.cvEmergency:
                emergencyCall();
                break;
        }
    }

    private void normalCall() {
        Alerts.show(mContext, "Normal");
    }

    private void emergencyCall() {
        Alerts.show(mContext, "Emergency");
    }


}
