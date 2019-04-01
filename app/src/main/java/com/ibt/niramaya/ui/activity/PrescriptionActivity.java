package com.ibt.niramaya.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.DoctorReviewRatingAdapter;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
    }
}
