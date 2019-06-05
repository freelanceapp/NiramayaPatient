package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.doctor_opd.SpecialDoctorOpdListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.home.SystemSpecialization;
import com.ibt.niramaya.modal.specialization.all.SpecialistDoctorDatum;
import com.ibt.niramaya.modal.specialization.all.SpecialistDoctorModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;

import retrofit2.Response;

public class SpecialistDoctorActivity extends BaseActivity implements View.OnClickListener {

    private SystemSpecialization specialization;
    private RecyclerView rvSpecialization;
    private ArrayList<SpecialistDoctorDatum> doctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peditrician);

        specialization = getIntent().getExtras().getParcelable("SPECIALIZATION");

        getAllSpecialDoctor();

        init();
    }

    private void getAllSpecialDoctor() {
        String userId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
        if (cd.isNetworkAvailable()){
            RetrofitService.getSpecializationDoctor(new Dialog(mContext), retrofitApiClient.getSpecialistDoctor(
                    specialization.getSpecializationId(), userId, ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SpecialistDoctorModel specialModel = (SpecialistDoctorModel) result.body();
                    if (!specialModel.getError()) {
                        if (specialModel.getDoctorData().size()>0) {
                            doctorList = (ArrayList<SpecialistDoctorDatum>) specialModel.getDoctorData();
                            rvSpecialization = findViewById(R.id.rvSpecialization);
                            rvSpecialization.setLayoutManager(new LinearLayoutManager(mContext));
                            SpecialDoctorOpdListAdapter specialAdapter = new SpecialDoctorOpdListAdapter(
                                   doctorList, mContext, SpecialistDoctorActivity.this, specialization.getSpecializationTitle()
                            );
                            rvSpecialization.setAdapter(specialAdapter);
                            specialAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }

    private void init() {
        findViewById(R.id.imgSearch).setVisibility(View.VISIBLE);
        findViewById(R.id.imgFilter).setVisibility(View.VISIBLE);

        TextView txtTitle = findViewById(R.id.txtTitle);
        ImageView imgBack = findViewById(R.id.imgBack);

        txtTitle.setText(specialization.getSpecializationTitle());
        imgBack.setOnClickListener((v -> onBackPressed()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llDoctor:
                int tag = (int) v.getTag();
                startActivity(new Intent(mContext, DoctorActivity.class).putExtra("DoctorId", doctorList.get(tag).getDoctorId()));
                break;
        }
    }
}
