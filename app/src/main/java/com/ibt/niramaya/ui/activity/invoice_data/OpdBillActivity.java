package com.ibt.niramaya.ui.activity.invoice_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.OpdInformation;
import com.ibt.niramaya.utils.BaseActivity;

public class OpdBillActivity extends BaseActivity implements View.OnClickListener {
    private OpdInformation opdInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opd_bill);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbarOpdData);
        toolbar.setTitle("Opd Information");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getIntentData();
    }


    private void getIntentData() {
        Intent intent = getIntent();
        opdInformation = (OpdInformation) intent.getExtras().get("opdSchedule");
    }

    @Override
    public void onClick(View v) {
    }
}
