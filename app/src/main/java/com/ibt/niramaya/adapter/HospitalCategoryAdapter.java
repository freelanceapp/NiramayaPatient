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
import com.ibt.niramaya.modal.home.SystemSpecialization;
import com.ibt.niramaya.ui.activity.SpecialistDoctorActivity;
import com.ibt.niramaya.ui.activity.invoice_data.BookAppointmentActivityKt;

import java.util.List;

public class HospitalCategoryAdapter extends RecyclerView.Adapter<HospitalCategoryAdapter.MyViewHolder> {

    private List<SystemSpecialization> sHospitalList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public HospitalCategoryAdapter(List<SystemSpecialization> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.sHospitalList = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(sHospitalList.get(position).getSpecializationImage())
                .fitCenter()
                .into(holder.imgCategory);
        holder.txtCategoryName.setText(sHospitalList.get(position).getSpecializationTitle());
        holder.txtCategoryName.setTag(position);
        holder.txtCategoryName.setOnClickListener(onClickListener);
        /*holder.txtCategoryName.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, SpecialistDoctorActivity.class);
            mContext.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return sHospitalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCategoryName;
        public ImageView imgCategory;

        public MyViewHolder(View view) {
            super(view);
            txtCategoryName = view.findViewById(R.id.txtCategoryName);
            imgCategory = view.findViewById(R.id.imgCategory);
        }
    }

}
