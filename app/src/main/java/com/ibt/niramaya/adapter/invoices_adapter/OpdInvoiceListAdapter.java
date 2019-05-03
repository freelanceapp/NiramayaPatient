package com.ibt.niramaya.adapter.invoices_adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.OpdInformation;

import java.util.List;

public class OpdInvoiceListAdapter extends RecyclerView.Adapter<OpdInvoiceListAdapter.MyViewHolder> {

    private List<BillDatum> opdInformationList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private HospitalBillInformation hospitalBillInformation;
    private OpdInformation opdInformation;

    public OpdInvoiceListAdapter(List<BillDatum> opdInformationList, Context mContext, View.OnClickListener onClickListener) {
        this.opdInformationList = opdInformationList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_opd_invoice_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BillDatum datum = opdInformationList.get(position);
        //   hospitalBillInformation = datum.getHospitalBillInformation();

        holder.cardViewOpd.setTag(position);
        holder.cardViewOpd.setOnClickListener(onClickListener);
/*
        holder.tvHospitalName.setText(hospitalBillInformation.getHospitalName());
        Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(holder.imgHospital);
        holder.tvHospitalLocation.setText(hospitalBillInformation.getHospitalStreetName())*/
        ;

        holder.tvAppoiontmentAmount.setText(" : " + datum.getAppointmentAmount());
        holder.tvAppoiontmentDate.setText(datum.getAppointmentBookingDate());
        holder.tvDoctorName.setText(datum.getAppointmentReferredDoctorName());
        holder.tvAppoiontmentAmount.setText(datum.getAppointmentAmount());
        holder.tvPaymentType.setText(datum.getAppointmentType());
        holder.tvBillStatus.setText(datum.getAppointmentStatus());
        holder.tvOpdTitle.setText(datum.getOpdInformation().getTitle());
        holder.tvOpdAmount.setText(datum.getOpdInformation().getAmount());
        holder.tvOpdDescription.setText(datum.getOpdInformation().getDescription());
    }

    @Override
    public int getItemCount() {
        return opdInformationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHospitalName, tvHospitalLocation, tvInvoiceNumber, tvDoctorName, tvAppoiontmentAmount, tvAppoiontmentDate, tvPaymentType, tvBillStatus, tvOpdTitle, tvOpdAmount, tvOpdDescription;
        public ImageView imgHospital;
        private CardView cardViewOpd;

        public MyViewHolder(View view) {
            super(view);
            cardViewOpd = view.findViewById(R.id.cardViewOpd);
            imgHospital = view.findViewById(R.id.imgHospital);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvHospitalLocation = view.findViewById(R.id.tvHospitalLocation);
            tvInvoiceNumber = view.findViewById(R.id.tvInvoiceNumber);
            tvAppoiontmentAmount = view.findViewById(R.id.tvAppoiontmentAmount);
            tvDoctorName = view.findViewById(R.id.tvDoctorName);
            tvAppoiontmentDate = view.findViewById(R.id.tvAppoiontmentDate);
            tvPaymentType = view.findViewById(R.id.tvPaymentType);
            tvBillStatus = view.findViewById(R.id.tvBillStatus);
            tvOpdTitle = view.findViewById(R.id.tvOpdTitle);
            tvOpdAmount = view.findViewById(R.id.tvOpdAmount);
            tvOpdDescription = view.findViewById(R.id.tvOpdDescription);
        }
    }

}
