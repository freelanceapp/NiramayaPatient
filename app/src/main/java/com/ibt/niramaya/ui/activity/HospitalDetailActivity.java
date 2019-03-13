package com.ibt.niramaya.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseActivity;

public class HospitalDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        init();
    }

    private void init() {
        String strFrom = getIntent().getStringExtra("from");
        if (strFrom.equalsIgnoreCase("detail")) {
            ((ImageView) findViewById(R.id.imgDetail)).setImageResource(R.drawable.img_detail_b);
        } else {
            ((ImageView) findViewById(R.id.imgDetail)).setImageResource(R.drawable.img_detail_a);
        }
    }
}
