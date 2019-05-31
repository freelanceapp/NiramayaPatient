package com.ibt.niramaya.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.PatientFinanceListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.finance.PatientFinance;
import com.ibt.niramaya.modal.finance.PatientFinanceListModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.AddPatientFinanceDetailActivity;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.List;

import retrofit2.Response;

public class PatientFinanceDetailFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private RecyclerView rvFinance;
    private List<PatientFinance> financeList;
    private TextView tvMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_patient_finance_detail, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        initViews();
        return rootView;
    }

    private void initViews() {
        rvFinance = rootView.findViewById(R.id.rvFinance);
        tvMessage = rootView.findViewById(R.id.tvMessage);
        FloatingActionButton fabAddFinance = rootView.findViewById(R.id.fabAddFinance);
        fabAddFinance.setOnClickListener((v) -> startActivity(new Intent(mContext, AddPatientFinanceDetailActivity.class)
                .putExtra("FROM", "CREATE")));
    }

    @Override
    public void onResume() {
        super.onResume();
        opdInvoiceListApi();
    }

    private void opdInvoiceListApi() {
        if (cd.isNetworkAvailable()) {
            String strPatientId = AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID);
            RetrofitService.patientFinanceList(new Dialog(mContext), retrofitApiClient.patientFinanceList(strPatientId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    PatientFinanceListModel patientFinanceListModel = (PatientFinanceListModel) result.body();
                    if (!patientFinanceListModel.getResult()) {
                        tvMessage.setVisibility(View.VISIBLE);
                        if (patientFinanceListModel.getPatientFinance().size() > 0) {
                            tvMessage.setVisibility(View.GONE);
                            Alerts.show(mContext, patientFinanceListModel.getMessage());
                            financeList = patientFinanceListModel.getPatientFinance();
                            rvFinance.setHasFixedSize(true);
                            rvFinance.setLayoutManager(new LinearLayoutManager(mContext));
                            PatientFinanceListAdapter financeAdapter = new PatientFinanceListAdapter(
                                    financeList, mContext, PatientFinanceDetailFragment.this);
                            rvFinance.setAdapter(financeAdapter);
                            financeAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Alerts.show(mContext, patientFinanceListModel.getMessage());
                        tvMessage.setVisibility(View.VISIBLE);
                    }
/*                    if (!patientFinanceListModel.getResult() && patientFinanceListModel.getPatientFinance().size()>0) {

                        rvFinance.setHasFixedSize(true);
                        rvFinance.setLayoutManager(new LinearLayoutManager(mContext));
                        *//*invoiceListAdapter = new PatientFinanceListAdapter(opdInformationList, mContext, OpdInvoiceFragment.this);
                        recyclerViewInvoice.setAdapter(invoiceListAdapter);
                        opdInformationList.addAll(opdInvoiceMainModal.getBillData());
                        invoiceListAdapter.notifyDataSetChanged();
                        Alerts.show(mContext, opdInvoiceMainModal.getMessage());*//*
                    } else {
                        Alerts.show(mContext, patientFinanceListModel.getMessage());
                    }*/
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llRoot:
                int tag = (int) v.getTag();
                startActivity(new Intent(mContext, AddPatientFinanceDetailActivity.class)
                        .putExtra("FROM", "UPDATE")
                        .putExtra("FinanceData", financeList.get(tag)));
                break;
        }
    }
}
