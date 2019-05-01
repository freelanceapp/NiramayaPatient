package com.ibt.niramaya.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.patient_adapter.PatientListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.patient_modal.PaitentProfile;
import com.ibt.niramaya.modal.patient_modal.PatientMainModal;
import com.ibt.niramaya.modal.patient_modal.User;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.patient.AddNewPatientActivity;
import com.ibt.niramaya.ui.activity.patient.PatientDetailActivity;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Response;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class PatientFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private ConnectionDetector cd;
    private PatientListAdapter patientListAdapter;
    private List<PaitentProfile> patientList = new ArrayList<>();
    private RecyclerView rvPatientList;
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_patient, container, false);
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

        patientListApi();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llPatient:
                position = (int) v.getTag();
                Intent intent = new Intent(mContext, PatientDetailActivity.class);
                intent.putExtra("patientDetail", patientList.get(position));
                startActivity(intent);
                break;
        }
    }

    private void patientListApi() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            RetrofitService.getPatientList(new Dialog(mContext), retrofitApiClient.patientList(strUserId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    PatientMainModal mainModal = (PatientMainModal) result.body();
                    if (mainModal != null) {
                        patientList = mainModal.getUser().getPaitentProfile();
                        String strPatientId = mainModal.getUser().getPaitentProfile().get(position).getPatientId();
                        AppPreference.setStringPreference(mContext, Constant.PATIENT_ID, strPatientId);

                        rvPatientList.setHasFixedSize(true);
                        rvPatientList.setLayoutManager(new GridLayoutManager(mContext, 3));
                        patientListAdapter = new PatientListAdapter(patientList, mContext, PatientFragment.this);
                        rvPatientList.setAdapter(patientListAdapter);
                        patientListAdapter.notifyDataSetChanged();
                        PaitentProfile paitentProfile1 = new PaitentProfile();
                        paitentProfile1.setPatientId("0");
                        paitentProfile1.setPatientName("Add New Patient");
                        patientList.add(patientList.size(), paitentProfile1);

                        Alerts.show(mContext, mainModal.getMessage());
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }


}
