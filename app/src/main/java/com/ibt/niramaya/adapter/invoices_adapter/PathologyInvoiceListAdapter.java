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
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PahologyInvoiceListMainModal;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PathologyBillTest;

import java.util.List;

public class PathologyInvoiceListAdapter extends RecyclerView.Adapter<PathologyInvoiceListAdapter.MyViewHolder> {

    private List<BillDatum> pathologyBillTestList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public PathologyInvoiceListAdapter(List<BillDatum> pathologyBillTestList, Context mContext, View.OnClickListener onClickListener) {
        this.pathologyBillTestList = pathologyBillTestList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pathology_invoice_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BillDatum billDatum = pathologyBillTestList.get(position);
        holder.tvInvoiceNumber.setText(billDatum.getBillInvoice());
        holder.tvPatientNamme.setText(billDatum.getPatientName());
        holder.tvPatientAge.setText(billDatum.getPatientAge());
        holder.tvPatientGender.setText(billDatum.getPatientGender());
        holder.tvTotalAmount.setText(billDatum.getBillAmount());
        holder.tvDiscount.setText(billDatum.getBillDiscount());
        holder.tvBillGenerateDAte.setText(billDatum.getBillCreatedDate());

     /*   PathologyBillTest pathologyBillTest = pathologyBillTestList.get(position);
        holder.cardViewPathology.setOnClickListener(onClickListener);
        holder.cardViewPathology.setTag(position);

        holder.tvTestDate.setText(pathologyBillTest.getTestTypeCreatedDate());
        holder.tvTotalAmount.setText(pathologyBillTest.getTestCost());
        holder.tvTestDiscount.setText(pathologyBillTest.getTestDiscount());
        holder.tvSchedule.setText(pathologyBillTest.getTestSchedule());
        holder.tvTestCode.setText(pathologyBillTest.getTestCode());
        holder.tvTestSample.setText(pathologyBillTest.getTestSample());
*/
    }

    @Override
    public int getItemCount() {
        return pathologyBillTestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvInvoiceNumber;
        public TextView tvBillGenerateDAte, tvPatientNamme, tvPatientAge, tvPatientGender, tvTotalAmount, tvDiscount;
        public ImageView rc_img;
        private CardView cardViewPathology;

        public MyViewHolder(View view) {
            super(view);
            cardViewPathology = view.findViewById(R.id.cardViewPathology);
            tvInvoiceNumber = view.findViewById(R.id.tvInvoiceNumber);
            tvBillGenerateDAte = view.findViewById(R.id.tvBillGenerateDAte);
            tvPatientNamme = view.findViewById(R.id.tvPatientNamme);
            tvPatientAge = view.findViewById(R.id.tvPatientAge);
            tvPatientGender = view.findViewById(R.id.tvPatientGender);
            tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
            tvDiscount = view.findViewById(R.id.tvDiscount);
        }
    }

}
