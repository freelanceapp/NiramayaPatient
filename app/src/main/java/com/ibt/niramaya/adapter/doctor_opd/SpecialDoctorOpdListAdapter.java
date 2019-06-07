package com.ibt.niramaya.adapter.doctor_opd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.hospital_detail.DoctorDatum;
import com.ibt.niramaya.modal.specialization.all.SpecialistDoctorDatum;

import java.util.List;

public class SpecialDoctorOpdListAdapter extends RecyclerView.Adapter<SpecialDoctorOpdListAdapter.MyViewHolder> {

    private List<SpecialistDoctorDatum> doctorList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private String spcl;

    public SpecialDoctorOpdListAdapter(List<SpecialistDoctorDatum> vendorLists, Context mContext, View.OnClickListener onClickListener, String spcl) {
        this.doctorList = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.spcl = spcl;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_peditrician, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpecialistDoctorDatum doctorData = doctorList.get(position);
        holder.tvDoctorName.setText(doctorData.getName());
        holder.tvDoctorSpecialization.setText(spcl);
        //holder.tvDoctorAddress.setText(doctorData.get);
        holder.tvDoctorRatings.setText(doctorData.getRating());
        holder.llDoctor.setTag(position);
        holder.llDoctor.setOnClickListener(onClickListener);

        Glide.with(mContext)
                .load(doctorData.getProfileImage())
                .into(holder.civDoctorProfile);

    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDoctorName, tvDoctorSpecialization, tvDoctorAddress, tvDoctorRatings;
        public ImageView civDoctorProfile;
        private LinearLayout llDoctor;

        public MyViewHolder(View view) {
            super(view);
            llDoctor = view.findViewById(R.id.llDoctor);

            tvDoctorName = view.findViewById(R.id.tvDoctorName);
            tvDoctorSpecialization = view.findViewById(R.id.tvDoctorSpecialization);
            tvDoctorAddress = view.findViewById(R.id.tvDoctorAddress);
            tvDoctorRatings = view.findViewById(R.id.tvDoctorRatings);

            civDoctorProfile = view.findViewById(R.id.civDoctorProfile);
        }
    }

}
