package com.ibt.niramaya.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.PrescriptionListAdapter;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class PrescriptionsFragment extends BaseFragment implements View.OnClickListener {

    private List<String> prescriptionList = new ArrayList<>();
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_prescription, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        prescriptionListApi();
    }

    private void prescriptionListApi() {
        for (int i = 0; i < 10; i++) {
            prescriptionList.add("Name");
        }
        RecyclerView recyclerViewPrescription = rootView.findViewById(R.id.recyclerViewPrescription);
        recyclerViewPrescription.setHasFixedSize(true);
        recyclerViewPrescription.setLayoutManager(new GridLayoutManager(getActivity(),
                2, GridLayoutManager.VERTICAL, false));
        PrescriptionListAdapter prescriptionAdapter = new PrescriptionListAdapter(prescriptionList, mContext, this);
        recyclerViewPrescription.setAdapter(prescriptionAdapter);
        prescriptionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
