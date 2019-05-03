package com.ibt.niramaya.ui.activity.invoice_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.invoices_adapter.PathologyTestListAdapter;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PathologyBillTest;
import com.ibt.niramaya.utils.BaseActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PathologTestBillActivity extends BaseActivity implements View.OnClickListener {
    private List<PathologyBillTest> billTest = new ArrayList<>();
    private PathologyTestListAdapter testListAdapter;
    private HospitalBillInformation hospitalBillInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patholog_test_bill);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbarPathTest);
        toolbar.setTitle("Pathologoy Bill ");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getIntentData();
        pathData();
    }

    private void pathData() {
        RecyclerView recyclerViewInvoice = findViewById(R.id.recylerTest);
        recyclerViewInvoice.setHasFixedSize(true);
        recyclerViewInvoice.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        testListAdapter = new PathologyTestListAdapter(billTest, mContext, this);
        recyclerViewInvoice.setAdapter(testListAdapter);
        testListAdapter.notifyDataSetChanged();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        billTest = intent.getParcelableArrayListExtra("testList");
        hospitalBillInformation = intent.getParcelableExtra("hospitalInformation");
        String strGst = intent.getStringExtra("gst");
        String strAmount = intent.getStringExtra("total");
        String strDiscount = intent.getStringExtra("discount");
        String strCreatedDate = intent.getStringExtra("date");
        String strBillType = intent.getStringExtra("billType");
        if (strBillType.equals("0")) {
            strBillType = "Paid";
        } else {
            strBillType = "Due";
        }

        String strBillStatus = intent.getStringExtra("billStatus");
        if (strBillStatus.equals("0")) {
            strBillStatus = "Unpaid";
        } else if (strBillStatus.equals("1")) {
            strBillStatus = "Paid";
        } else if (strBillStatus.equals("2")) {
            strBillStatus = "Uncomplete";
        } else {
            strBillStatus = "Cancel bill by hospital";
        }
        Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(((ImageView) findViewById(R.id.imgHospital)));
        ((TextView) findViewById(R.id.tvHospitalName)).setText(hospitalBillInformation.getHospitalName());
        ((TextView) findViewById(R.id.tvHospitalLocation)).setText(hospitalBillInformation.getHospitalStreetName());
        ((TextView) findViewById(R.id.tvBIllCreateDate)).setText(strCreatedDate);
        ((TextView) findViewById(R.id.tvBIllGstNUmber)).setText(strGst);
        ((TextView) findViewById(R.id.tvTotalAmount)).setText(strAmount);
        ((TextView) findViewById(R.id.tvDiscountAmount)).setText(strDiscount);
        ((TextView) findViewById(R.id.tvBillType)).setText(strBillType);
        ((TextView) findViewById(R.id.tvBillStatus)).setText(strBillStatus);
    }

    @Override
    public void onClick(View v) {
    }
}
