package com.ibt.niramaya.ui.fragment.invoice_list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.invoices_adapter.PathologyInvoiceListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PahologyInvoiceListMainModal;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PathologyBillTest;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.invoice_data.PathologTestBillActivity;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class PathologyInvoiceFragment extends BaseFragment implements View.OnClickListener {
    private PathologyInvoiceListAdapter invoiceListAdapter;
    private View rootView;
    private List<BillDatum> pathologyInvoiceList = new ArrayList<>();
    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_invoices_list, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        prescriptionListApi();
        pathologyInvoiceListApi();
    }

    private boolean _hasLoadedOnce = false; // your boolean field

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible_) {
        super.setUserVisibleHint(true);


        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isFragmentVisible_ && !_hasLoadedOnce) {
                pathologyInvoiceListApi();
                _hasLoadedOnce = true;
            }
        }
    }

    private void prescriptionListApi() {
        RecyclerView recyclerViewInvoice = rootView.findViewById(R.id.recyclerViewInvoice);
        recyclerViewInvoice.setHasFixedSize(true);
        recyclerViewInvoice.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        invoiceListAdapter = new PathologyInvoiceListAdapter(pathologyInvoiceList, mContext, this);
        recyclerViewInvoice.setAdapter(invoiceListAdapter);
        invoiceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardViewPathology:
                position = (int) v.getTag();
                Intent intent = new Intent(mContext, PathologTestBillActivity.class);
                intent.putExtra("gst", pathologyInvoiceList.get(position).getBillGstNumber());
                intent.putExtra("total", pathologyInvoiceList.get(position).getBillAmount());
                intent.putExtra("discount", pathologyInvoiceList.get(position).getBillDiscount());
                intent.putExtra("date", pathologyInvoiceList.get(position).getBillCreatedDate());
                intent.putExtra("billType", pathologyInvoiceList.get(position).getBillType());
                intent.putExtra("billStatus", pathologyInvoiceList.get(position).getBillStatus());
                intent.putExtra("testList", (ArrayList) pathologyInvoiceList.get(position).getPathologyBillTest());
                intent.putExtra("hospitalInformation", pathologyInvoiceList.get(position).getHospitalBillInformation());
                startActivity(intent);
                break;
        }
    }

    private void pathologyInvoiceListApi() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            String strPatientId = AppPreference.getStringPreference(mContext, Constant.PATIENT_ID);
            RetrofitService.getPathologyInvoiceList(new Dialog(mContext), retrofitApiClient.pathologyInvoiceList("2", strUserId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    pathologyInvoiceList.clear();
                    PahologyInvoiceListMainModal mainModal = (PahologyInvoiceListMainModal) result.body();
                    if (mainModal != null && !mainModal.getError()) {
                        if (mainModal.getBillData().get(position).getPathologyBillTest() != null) {
                            pathologyInvoiceList.addAll(mainModal.getBillData());
                            invoiceListAdapter.notifyDataSetChanged();
                            Alerts.show(mContext, mainModal.getMessage());

                        } else {
                            Alerts.show(mContext, mainModal.getMessage());
                        }
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

}
