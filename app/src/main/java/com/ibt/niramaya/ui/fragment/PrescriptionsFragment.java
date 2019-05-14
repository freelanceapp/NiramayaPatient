package com.ibt.niramaya.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.PrescriptionListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.prescription.OpdList;
import com.ibt.niramaya.modal.prescription.PrescritionListModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.activity.PrescriptionActivity;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class PrescriptionsFragment extends BaseFragment implements View.OnClickListener {

    private List<OpdList> prescriptionList = new ArrayList<>();
    private View rootView;
    private PrescriptionListAdapter prescriptionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_prescription, container, false);
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
    }

    private void prescriptionListApi() {
        RecyclerView recyclerViewPrescription = rootView.findViewById(R.id.recyclerViewPrescription);

        fetchPrescriptionList(recyclerViewPrescription);
    }

    private void fetchPrescriptionList(final RecyclerView recyclerViewPrescription) {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            String patientId = AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID);
            RetrofitService.patientPrescriptionList(new Dialog(mContext), retrofitApiClient.patientPrescriptionList(strUserId, patientId
            ), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    PrescritionListModel mainModal = (PrescritionListModel) result.body();
                    if (!mainModal.getError()) {
                        Alerts.show(mContext, mainModal.getMessage());
                        if (mainModal.getOpdList().size()>0){
                            prescriptionList = mainModal.getOpdList();
                            recyclerViewPrescription.setHasFixedSize(true);
                            recyclerViewPrescription.setLayoutManager(new GridLayoutManager(getActivity(),
                                    2, GridLayoutManager.VERTICAL, false));
                            prescriptionAdapter = new PrescriptionListAdapter(prescriptionList, mContext, PrescriptionsFragment.this);
                            recyclerViewPrescription.setAdapter(prescriptionAdapter);
                            prescriptionAdapter.notifyDataSetChanged();
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


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.txtOpen){
            int tag = (int) v.getTag();
            Intent intent = new Intent(mContext, PrescriptionActivity.class);
            intent.putExtra("OPD_DATA", prescriptionList.get(tag));
            startActivity(intent);
        }
    }
}
