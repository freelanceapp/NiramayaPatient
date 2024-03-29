package com.ibt.niramaya.ui.fragment.invoice_list;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.invoices_adapter.OpdInvoiceListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.OpdInvoiceMainModal;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class IpdInvoiceFragment extends BaseFragment implements View.OnClickListener {

    private List<BillDatum> opdInformationList = new ArrayList<>();
    private View rootView;
    private OpdInvoiceListAdapter invoiceListAdapter;

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
        //opdInvoiceListApi();

    }

    /*private boolean _hasLoadedOnce = false; // your boolean field

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible_) {
        super.setUserVisibleHint(true);


        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isFragmentVisible_ && !_hasLoadedOnce) {
                opdInvoiceListApi();
                _hasLoadedOnce = true;
            }
        }
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardViewOpd:
                int position = (int) v.getTag();
               /* Intent intent = new Intent(mContext, OpdBillActivity.class);
                intent.putExtra("opdSchedule", opdInformationList.get(position).getOpdInformation());
                startActivity(intent);*/
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        opdInvoiceListApi();
    }

    private void opdInvoiceListApi() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            String strPatientId = AppPreference.getStringPreference(mContext, Constant.PATIENT_ID);
            RetrofitService.getOpdInvoiceList(new Dialog(mContext), retrofitApiClient.opdInvoiceList(strPatientId, strUserId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    opdInformationList.clear();
                    OpdInvoiceMainModal opdInvoiceMainModal = (OpdInvoiceMainModal) result.body();
                    if (opdInvoiceMainModal != null && !opdInvoiceMainModal.getError()) {
                        RecyclerView recyclerViewInvoice = rootView.findViewById(R.id.recyclerViewInvoice);
                        recyclerViewInvoice.setHasFixedSize(true);
                        recyclerViewInvoice.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        invoiceListAdapter = new OpdInvoiceListAdapter(opdInformationList, mContext, IpdInvoiceFragment.this);
                        recyclerViewInvoice.setAdapter(invoiceListAdapter);
                        opdInformationList.addAll(opdInvoiceMainModal.getBillData());
                        invoiceListAdapter.notifyDataSetChanged();
                        Alerts.show(mContext, opdInvoiceMainModal.getMessage());
                    } else {
                        Alerts.show(mContext, opdInvoiceMainModal.getMessage());
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
