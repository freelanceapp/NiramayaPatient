package com.ibt.niramaya.adapter.patient_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.patient_modal.PaitentProfile;
import com.ibt.niramaya.ui.activity.patient.AddNewPatientActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    private List<PaitentProfile> patientList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public PatientListAdapter(List<PaitentProfile> patientList, Context mContext, View.OnClickListener onClickListener) {
        this.patientList = patientList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PaitentProfile paitentProfile = patientList.get(position);

        String strPatientId = paitentProfile.getPatientId();
        if (strPatientId.equals("0")) {
            holder.llPatient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, AddNewPatientActivity.class));
                }
            });
        } else {

            holder.tvPatientProfile.setText(paitentProfile.getPatientName());
            String strImageUrl = paitentProfile.getPatientProfilePicture();
            if (strImageUrl != null) {
                Glide.with(mContext).load(strImageUrl).into(holder.ivPatientProfile);
            } else {
                holder.ivPatientProfile.setVisibility(View.GONE);
            }
            holder.llPatient.setTag(position);
            holder.llPatient.setOnClickListener(onClickListener);
        }

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPatientProfile;
        public CircleImageView ivPatientProfile;
        private LinearLayout llPatient, llAddNewPatient;

        public MyViewHolder(View view) {
            super(view);
            llPatient = view.findViewById(R.id.llPatient);
            ///llAddNewPatient = view.findViewById(R.id.llAddNewPatient);
            ivPatientProfile = view.findViewById(R.id.ivPatientProfile);
            tvPatientProfile = view.findViewById(R.id.tvPatientProfile);
        }
    }

}
