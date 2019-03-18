package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;

import java.util.List;

public class BloodDonationRecievedAdapter extends RecyclerView.Adapter<BloodDonationRecievedAdapter.ViewHolder> {
    private Context mContext;
    private List<String> recievedDonationList;

    public BloodDonationRecievedAdapter(Context mContext, List<String> recievedDonationList) {
        this.mContext = mContext;
        this.recievedDonationList = recievedDonationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootView = li.inflate(R.layout.row_blood_donation_recieved, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return recievedDonationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHospitalName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalName = itemView.findViewById(R.id.tv_blood_revieved_hospitalname);
        }
    }
}
