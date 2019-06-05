package com.ibt.niramaya.adapter.doctor_opd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.hospital_detail.DoctorDatum;

import java.util.List;

public class DoctorOpdListAdapter extends RecyclerView.Adapter<DoctorOpdListAdapter.MyViewHolder> {

    private List<DoctorDatum> doctorList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public DoctorOpdListAdapter(List<DoctorDatum> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.doctorList = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_peditrician, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DoctorDatum doctorData = doctorList.get(position);
        holder.tvDoctorName.setText(doctorData.getName());
        holder.tvDoctorSpecialization.setText(doctorData.getDoctorSpecialization().get(0).getSpecializationTitle());
        //holder.tvDoctorAddress.setText(doctorData.get);
        holder.tvDoctorRatings.setText(doctorData.getRating());
        holder.llDoctor.setTag(position);
        holder.llDoctor.setOnClickListener(onClickListener);

        StringBuilder specialization = new StringBuilder();
        int sCount = 0;

        for (int i = 0; i<doctorData.getDoctorSpecialization().size(); i++){
            if (sCount == 0){
                specialization = new StringBuilder(doctorData.getDoctorSpecialization().get(i).getSpecializationTitle().trim());
                sCount++;
            }else{
                specialization.append(", ").append(doctorData.getDoctorSpecialization().get(i).getSpecializationTitle().trim());
            }
        }
        holder.tvDoctorSpecialization.setText(specialization);

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
