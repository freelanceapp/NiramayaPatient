package com.ibt.niramaya.ui.fragment.home_detail_fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.doctor_opd.DoctorOpdListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.doctor_opd.DoctorDatum;
import com.ibt.niramaya.modal.doctor_opd.DoctorOpdModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.DoctorActivity;
import com.ibt.niramaya.ui.activity.HospitalDetailActivity;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DoctorFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private DoctorOpdListAdapter doctorAdapter;
    private RecyclerView rvDoctorOpdList;
    private List<DoctorDatum> doctorList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_doctor, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        fetchDoctorList();

        initViews();
        return rootView;
    }

    private void fetchDoctorList() {
        String hospitalId = HospitalDetailActivity.hospitalId;
        String userId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
        if (cd.isNetworkAvailable()){
            RetrofitService.getOpdDoctorList(new Dialog(mContext), retrofitApiClient.doctorOpdList(
                    hospitalId, userId, ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    DoctorOpdModel doctorModel = (DoctorOpdModel)  result.body();
                    if (!doctorModel.getError() && doctorModel.getDoctorData().size()>0){
                        doctorList = doctorModel.getDoctorData();
                        rvDoctorOpdList = rootView.findViewById(R.id.rvDoctorOpdList);
                        doctorAdapter = new DoctorOpdListAdapter(doctorList, mContext, DoctorFragment.this);
                        rvDoctorOpdList.setLayoutManager(new LinearLayoutManager(mContext));
                        rvDoctorOpdList.setAdapter(doctorAdapter);
                        doctorAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }

    private void initViews() {
        rvDoctorOpdList = rootView.findViewById(R.id.rvDoctorOpdList);
        doctorAdapter = new DoctorOpdListAdapter(doctorList, mContext, DoctorFragment.this);
        rvDoctorOpdList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDoctorOpdList.setAdapter(doctorAdapter);
        doctorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.llDoctor){
            int tag = (int) v.getTag();
            startActivity(new Intent(mContext, DoctorActivity.class).putExtra("DoctorData", doctorList.get(tag)));
        }
    }
}
