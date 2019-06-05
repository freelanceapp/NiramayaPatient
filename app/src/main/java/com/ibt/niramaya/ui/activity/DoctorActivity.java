package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.DoctorReviewRatingAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.doctor_opd.DoctorData;
import com.ibt.niramaya.modal.doctor_opd.DoctorOpdModel;
import com.ibt.niramaya.modal.doctor_opd_model.DoctorOpdData;
import com.ibt.niramaya.modal.doctor_opd_model.DoctorOpdDataModel;
import com.ibt.niramaya.modal.hospital_detail.DoctorDatum;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.invoice_data.BookAppointmentActivityKt;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DoctorActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView rvdoctorAppointment;
    private DoctorReviewRatingAdapter reviewRatingAdapter;
    private List<String> reviewList = new ArrayList<>();
    private String doctorId;
    private DoctorOpdData doctorDetail;
    private TextView tvDoctorName, tvSpecialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        doctorId = getIntent().getExtras().getString("DoctorId");

        fetchDoctorData();

        rvdoctorAppointment = findViewById(R.id.rvdoctorAppointment);
        Button btnBookAppointment = findViewById(R.id.btnBookAppointment);
        btnBookAppointment.setOnClickListener(this);

        for (int i=0; i<1; i++) {
            reviewList.add("Mohit Rana ");
            reviewList.add("8 hours ago");
            reviewList.add("The doctor is very good with exellent skill.");
        }



        rvdoctorAppointment.setHasFixedSize(true);
        rvdoctorAppointment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        reviewRatingAdapter = new DoctorReviewRatingAdapter(mContext, reviewList);
        rvdoctorAppointment.setAdapter(reviewRatingAdapter);
        reviewRatingAdapter.notifyDataSetChanged();
        rvdoctorAppointment.scrollToPosition(0);
    }

    private void fetchDoctorData() {
        String userId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
        if (cd.isNetworkAvailable()){
            RetrofitService.doctorDetail(new Dialog(mContext), retrofitApiClient.doctorOpd(
                    "1", userId, "", doctorId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    DoctorOpdDataModel opdModel = (DoctorOpdDataModel) result.body();
                    if (!opdModel.getError()){
                        doctorDetail = opdModel.getDoctorOpdData();
                        initViews();
                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }

    private void initViews() {

        findViewById(R.id.ivBack).setOnClickListener(this);

        StringBuilder specialization = new StringBuilder();
        int sCount = 0;
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvSpecialization = findViewById(R.id.tvSpecialization);
        tvDoctorName.setText(doctorDetail.getName());
        for (int i = 0; i<doctorDetail.getDoctorSpecialization().size(); i++){
            if (sCount == 0){
                specialization = new StringBuilder(doctorDetail.getDoctorSpecialization().get(i).getSpecializationTitle().trim());
                sCount++;
            }else{
                specialization.append(", ").append(doctorDetail.getDoctorSpecialization().get(i).getSpecializationTitle().trim());
            }
        }
        tvSpecialization.setText(specialization);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBookAppointment :
                //startActivity(new Intent(mContext, BookAppointmentActivityKt.class));
                startActivity(new Intent(mContext, BookAppointmentActivityKt.class).putExtra("DoctorOpdData", doctorDetail));
            break;
            case R.id.ivBack :
                onBackPressed();
            break;
        }
    }
}
