package com.ibt.niramaya.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.home.HospitalDatum;
import com.ibt.niramaya.modal.hospital_detail.HospitalSpecialization;
import com.ibt.niramaya.ui.activity.HospitalDetailActivity;

import java.util.List;

public class HospitalDepartmentsListAdapter extends RecyclerView.Adapter<HospitalDepartmentsListAdapter.MyViewHolder> {

    private List<HospitalSpecialization> hospitalSpecializationList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public HospitalDepartmentsListAdapter(List<HospitalSpecialization> hospitalSpecializationList, Context mContext, View.OnClickListener onClickListener) {
        this.hospitalSpecializationList = hospitalSpecializationList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hospital_departments, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvDepartments.setText(hospitalSpecializationList.get(position).getSpecializationTitle());
        holder.tvDepartments.setTag(position);
        holder.tvDepartments.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return hospitalSpecializationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDepartments;

        public MyViewHolder(View view) {
            super(view);
            tvDepartments = view.findViewById(R.id.tvDepartments);
        }
    }

}
