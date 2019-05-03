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
import com.ibt.niramaya.adapter.invoices_adapter.PharmacyInvoiceListAdapter;
import com.ibt.niramaya.adapter.invoices_adapter.PharmacyMedicineListAdapter;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyBillMedicine;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PharmacyMedicineBillActivity extends BaseActivity implements View.OnClickListener {
    List<PharmacyBillMedicine> billMedicineList = new ArrayList<>();
    private TextView tvcheck;
    private PharmacyMedicineListAdapter medicineListAdapter;
    private HospitalBillInformation hospitalBillInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_medicine_bill);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbarMedicineData);
        toolbar.setTitle("Medicne List");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getIntentData();
        medicineData();
    }

    private void medicineData() {
        RecyclerView recyclerViewInvoice = findViewById(R.id.recylerMecidine);
        recyclerViewInvoice.setHasFixedSize(true);
        recyclerViewInvoice.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        medicineListAdapter = new PharmacyMedicineListAdapter(billMedicineList, mContext);
        recyclerViewInvoice.setAdapter(medicineListAdapter);
        medicineListAdapter.notifyDataSetChanged();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        billMedicineList = intent.getParcelableArrayListExtra("medicineList");
        hospitalBillInformation = intent.getParcelableExtra("hospitalInformation");
        String strGst = intent.getStringExtra("gst");
        String strAmount = intent.getStringExtra("total");
        String strDiscount = intent.getStringExtra("discount");
        String strCreatedDate = intent.getStringExtra("date");
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
        String strBilltype = intent.getStringExtra("billType");
        if (strBilltype.equals("0")) {
            strBilltype = "Paid";
        } else {
            strBilltype = "Due";
        }


        Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(((ImageView) findViewById(R.id.imgHospital)));
        ((TextView) findViewById(R.id.tvHospitalName)).setText(hospitalBillInformation.getHospitalName());
        ((TextView) findViewById(R.id.tvHospitalLocation)).setText(hospitalBillInformation.getHospitalStreetName());
        ((TextView) findViewById(R.id.tvBIllCreateDate)).setText(strCreatedDate);
        ((TextView) findViewById(R.id.tvBIllGstNUmber)).setText(strGst);
        ((TextView) findViewById(R.id.tvTotalAmount)).setText(strAmount);
        ((TextView) findViewById(R.id.tvDiscountAmount)).setText(strDiscount);
        ((TextView) findViewById(R.id.tvBillStatus)).setText(strBillStatus);
        ((TextView) findViewById(R.id.tvBillType)).setText(strBilltype);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
