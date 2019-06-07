package com.ibt.niramaya.ui.fragment.invoice_list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.InvoiceListAdapter;
import com.ibt.niramaya.adapter.invoices_adapter.PharmacyInvoiceListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyBillMedicine;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyInvoiceMainModal;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.invoice_data.PharmacyMedicineBillActivity;
import com.ibt.niramaya.ui.fragment.PatientFragment;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class PharmacyInvoiceFragment extends BaseFragment implements View.OnClickListener {

    private List<BillDatum> pharmacyBillMedicineList = new ArrayList<>();
    private View rootView;
    private PharmacyInvoiceListAdapter invoiceListAdapter;
    private HospitalBillInformation hospitalBillInformation;
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
        pharmacyInvoiceListApi();
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        prescriptionListApi();
    }

    /*private boolean _hasLoadedOnce = false; // your boolean field

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible_) {
        super.setUserVisibleHint(true);


        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isFragmentVisible_ && !_hasLoadedOnce) {
                pharmacyInvoiceListApi();
                _hasLoadedOnce = true;
            }
        }
    }*/

    private void prescriptionListApi() {
        RecyclerView recyclerViewInvoice = rootView.findViewById(R.id.recyclerViewInvoice);
        recyclerViewInvoice.setHasFixedSize(true);
        recyclerViewInvoice.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        invoiceListAdapter = new PharmacyInvoiceListAdapter(pharmacyBillMedicineList, mContext, this);
        recyclerViewInvoice.setAdapter(invoiceListAdapter);
        invoiceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        pharmacyInvoiceListApi();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardPharmacyBill:
                position = (int) v.getTag();
                Intent intent = new Intent(mContext, PharmacyMedicineBillActivity.class);
                intent.putExtra("gst", pharmacyBillMedicineList.get(position).getBillGstNumber());
                intent.putExtra("total", pharmacyBillMedicineList.get(position).getBillAmount());
                intent.putExtra("discount", pharmacyBillMedicineList.get(position).getBillDiscount());
                intent.putExtra("date", pharmacyBillMedicineList.get(position).getBillCreatedDate());
                intent.putExtra("billStatus", pharmacyBillMedicineList.get(position).getBillStatus());
                intent.putExtra("billType", pharmacyBillMedicineList.get(position).getBillType());
                intent.putExtra("medicineList", (ArrayList) pharmacyBillMedicineList.get(position).getPharmacyBillMedicine());
                intent.putExtra("hospitalInformation", pharmacyBillMedicineList.get(position).getHospitalBillInformation());
                startActivity(intent);
                break;
        }
    }

    private void pharmacyInvoiceListApi() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            String strPatientId = AppPreference.getStringPreference(mContext, Constant.PATIENT_ID);
            RetrofitService.getPharmacyInvoiceList(new Dialog(mContext), retrofitApiClient.pharmacyInvoiceList(strPatientId, strUserId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    pharmacyBillMedicineList.clear();
                    PharmacyInvoiceMainModal mainModal = (PharmacyInvoiceMainModal) result.body();
                    if (mainModal != null && !mainModal.getError()) {
                        pharmacyBillMedicineList.addAll(mainModal.getBillData());
                        invoiceListAdapter.notifyDataSetChanged();
                        Alerts.show(mContext, mainModal.getMessage());
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
