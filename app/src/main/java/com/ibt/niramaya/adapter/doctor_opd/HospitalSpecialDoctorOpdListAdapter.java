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
import com.ibt.niramaya.modal.specialization.all.SpecialistDoctorDatum;
import com.ibt.niramaya.modal.specialization.hospital.HospitalSpecialistDoctorDatum;

import java.util.List;

public class HospitalSpecialDoctorOpdListAdapter extends RecyclerView.Adapter<HospitalSpecialDoctorOpdListAdapter.MyViewHolder> {

    private List<HospitalSpecialistDoctorDatum> doctorList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private String spcl;

    public HospitalSpecialDoctorOpdListAdapter(List<HospitalSpecialistDoctorDatum> vendorLists, Context mContext, View.OnClickListener onClickListener, String spcl) {
        this.doctorList = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.spcl = spcl;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hospital_special_doctor, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HospitalSpecialistDoctorDatum doctorData = doctorList.get(position);
        holder.tvDoctorName.setText(doctorData.getName());
//        holder.tvDoctorSpecialization.setText(spcl);
        //holder.tvDoctorAddress.setText(doctorData.get);
      //  holder.tvDoctorRatings.setText(doctorData.getRating());
        holder.llRoot.setTag(position);
        holder.llRoot.setOnClickListener(onClickListener);

        Glide.with(mContext)
                .load(doctorList.get(position).getProfileImage())
                .into(holder.ivProfile);



    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDoctorName, tvDoctorSpecialization, tvDoctorAddress, tvDoctorRatings;
        public ImageView ivProfile;
        private LinearLayout llRoot;

        public MyViewHolder(View view) {
            super(view);
            llRoot = view.findViewById(R.id.llRoot);

            tvDoctorName = view.findViewById(R.id.tvDoctorName);

            ivProfile = view.findViewById(R.id.ivProfile);
        }
    }

}
