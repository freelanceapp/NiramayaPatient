package com.ibt.niramaya.ui.fragment.blood_donation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.BloodDonationRecievedAdapter;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RecievedFragment extends BaseFragment {
    private View rootView;
    private BloodDonationRecievedAdapter bloodDonationRecievedAdapter;
    private List<String> recievedDonationList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recieved,container,false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        RecyclerView rvbloodDonationRecieved = rootView.findViewById(R.id.rv_bloodDonationRecieved);

        for (int i=0;  i<8; i++){
            recievedDonationList.add("Apollo Hospital");
        }

        rvbloodDonationRecieved.setHasFixedSize(true);
        rvbloodDonationRecieved.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        bloodDonationRecievedAdapter = new BloodDonationRecievedAdapter(mContext, recievedDonationList);
        rvbloodDonationRecieved.setAdapter(bloodDonationRecievedAdapter);
        bloodDonationRecievedAdapter.notifyDataSetChanged();
    }
}
