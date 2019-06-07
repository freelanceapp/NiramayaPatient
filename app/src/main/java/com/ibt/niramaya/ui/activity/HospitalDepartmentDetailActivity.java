package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.doctor_opd.HospitalSpecialDoctorOpdListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.home.HospitalDatum;
import com.ibt.niramaya.modal.hospital_detail.HospitalSpecialization;
import com.ibt.niramaya.modal.specialization.hospital.HospitalSpecialistDoctorDatum;
import com.ibt.niramaya.modal.specialization.hospital.HospitalSpecialistDoctorModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;

import retrofit2.Response;

public class HospitalDepartmentDetailActivity extends BaseActivity implements View.OnClickListener {

    private HospitalSpecialization departments;
    private HospitalDatum hospitalData;
    private ArrayList<HospitalSpecialistDoctorDatum> doctorList = new ArrayList<>();
    private RecyclerView rvHospitalDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_department_detail);

        departments = getIntent().getParcelableExtra("DEPARTMENTS");
        hospitalData = getIntent().getParcelableExtra("HOSPITAL");

        rvHospitalDoctor = findViewById(R.id.rvHospitalDoctor);

        getHospitalDoctorSpecialist();

        init();
    }

    private void getHospitalDoctorSpecialist() {
        String userId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
        if (cd.isNetworkAvailable()){
            RetrofitService.getHospitalSpecializationDoctor(new Dialog(mContext), retrofitApiClient.getHospitalSpecialistDoctor(
                    departments.getSpecializationId(), hospitalData.getHospitalId(), userId, ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    HospitalSpecialistDoctorModel specialModel = (HospitalSpecialistDoctorModel) result.body();
                    if (!specialModel.getError()) {
                        if (specialModel.getDoctorData().size()>0) {
                            doctorList = (ArrayList<HospitalSpecialistDoctorDatum>) specialModel.getDoctorData();
                            rvHospitalDoctor.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                            HospitalSpecialDoctorOpdListAdapter specialAdapter = new HospitalSpecialDoctorOpdListAdapter(
                                    doctorList, mContext, HospitalDepartmentDetailActivity.this, departments.getSpecializationTitle()
                            );
                            rvHospitalDoctor.setAdapter(specialAdapter);
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

        ImageView ivHospital = findViewById(R.id.ivHospital);
        TextView tvHospitalName= findViewById(R.id.tvHospitalName);
        TextView tvAddress= findViewById(R.id.tvAddress);
        TextView tvDepartment= findViewById(R.id.tvDepartment);
        TextView tvDepartmentDescription= findViewById(R.id.tvDepartmentDescription);

        Glide.with(mContext)
                .load(hospitalData.getHospialLogo())
                .fitCenter()
                .into(ivHospital);
        tvHospitalName.setText(hospitalData.getHospitalName());
        tvAddress.setText(hospitalData.getHospitalStreetName()+", "+hospitalData.getHospitalCity());

        ((TextView) findViewById(R.id.txtTitle)).setText("Hospital");

        tvDepartment.setText(departments.getSpecializationTitle());
        tvDepartmentDescription.setText(departments.getSpecializationDescription());

        findViewById(R.id.imgBack).setOnClickListener(this);

        fetchDepartmentDoctor();
    }

    private void fetchDepartmentDoctor() {
        if (cd.isNetworkAvailable()){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.llRoot:
                int tag = (int) v.getTag();
                startActivity(new Intent(mContext, DoctorActivity.class).putExtra("DoctorId", doctorList.get(tag).getDoctorId()));
                break;
        }
    }
}
