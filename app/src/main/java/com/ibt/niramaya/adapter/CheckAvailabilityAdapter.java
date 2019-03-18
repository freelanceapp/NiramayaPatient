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

public class CheckAvailabilityAdapter extends RecyclerView.Adapter<CheckAvailabilityAdapter.ViewHolder> {
    private Context mContext;
    private List<String> chekcBloodAvailiblityList;

    public CheckAvailabilityAdapter(Context mContext, List<String> chekcBloodAvailiblityList) {
        this.mContext = mContext;
        this.chekcBloodAvailiblityList = chekcBloodAvailiblityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootview = li.inflate(R.layout.row_search_check_availability_blood, null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return chekcBloodAvailiblityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tVSerialNo, tvBloodBank, tvHospitalName, tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tVSerialNo = itemView.findViewById(R.id.tvCAserNo);
            tvBloodBank = itemView.findViewById(R.id.tvCaBloodBank);
            tvHospitalName = itemView.findViewById(R.id.tvCABloodGroup);
            tvQuantity = itemView.findViewById(R.id.tvCAQuantity);
        }
    }
}
