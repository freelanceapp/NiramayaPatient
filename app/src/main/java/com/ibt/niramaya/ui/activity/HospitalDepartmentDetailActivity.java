package com.ibt.niramaya.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseActivity;

public class HospitalDepartmentDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_department_detail);

        init();
    }

    private void init() {
        ((TextView) findViewById(R.id.txtTitle)).setText("Hospital");
        findViewById(R.id.imgBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
