package com.ibt.niramaya.adapter.invoices_adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.BillDatum;

import java.util.List;

public class OpdInvoiceListAdapter extends RecyclerView.Adapter<OpdInvoiceListAdapter.MyViewHolder> {

    private List<BillDatum> opdInformationList;
    private Context mContext;
    private View.OnClickListener onClickListener;

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
        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        holder.tvpatientName.setText(datum.getPatientName());
        holder.tvPatientGender.setText(datum.getPatientGender());
        holder.tvPatientContact.setText(datum.getPatinetContact());
        holder.tvAppoiontmentAmount.setText(datum.getAppointmentAmount());
        holder.tvDoctorName.setText(datum.getAppointmentReferredDoctorName());
        holder.tvAppoiontmentDate.setText(datum.getAppointmentBookingDate());

    }

    @Override
    public int getItemCount() {
        return opdInformationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvpatientName, tvPatientGender, tvPatientContact, tvAppoiontmentAmount, tvDoctorName, tvAppoiontmentDate;
        public ImageView rc_img;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            tvpatientName = view.findViewById(R.id.tvpatientName);
            tvPatientGender = view.findViewById(R.id.tvPatientGender);
            tvPatientContact = view.findViewById(R.id.tvPatientContact);
            tvAppoiontmentAmount = view.findViewById(R.id.tvAppoiontmentAmount);
            tvDoctorName = view.findViewById(R.id.tvDoctorName);
            tvAppoiontmentDate = view.findViewById(R.id.tvAppoiontmentDate);
        }
    }

}
