package com.ibt.niramaya.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.DoctorReviewRatingAdapter;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends BaseActivity {
    private RecyclerView rvdoctorAppointment;
    private DoctorReviewRatingAdapter reviewRatingAdapter;
    private List<String> reviewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        rvdoctorAppointment = findViewById(R.id.rvdoctorAppointment);

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
}
