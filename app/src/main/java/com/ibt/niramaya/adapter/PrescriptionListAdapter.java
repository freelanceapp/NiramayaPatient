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

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.prescription.OpdList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.MyViewHolder> {

    private List<OpdList> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public PrescriptionListAdapter(List<OpdList> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_prescription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtDate.setText(changeDateFormat(vendorLists.get(position).getOpdCreatedDate()));
        holder.txtHospitalName.setText(vendorLists.get(position).getHospitalName());
        holder.txtDoctorName.setText(vendorLists.get(position).getDoctorName());

        holder.txtOpen.setTag(position);
        holder.txtOpen.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDate, txtHospitalName, txtDoctorName, txtOpen;

        public MyViewHolder(View view) {
            super(view);
            txtDate = view.findViewById(R.id.txtDate);
            txtHospitalName = view.findViewById(R.id.txtHospitalName);
            txtDoctorName = view.findViewById(R.id.txtDoctorName);
            txtOpen = view.findViewById(R.id.txtOpen);
        }
    }

    public String changeDateFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
