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
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PahologyInvoiceListMainModal;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PathologyBillTest;

import java.util.List;

public class PathologyInvoiceListAdapter extends RecyclerView.Adapter<PathologyInvoiceListAdapter.MyViewHolder> {

    private List<BillDatum> pathologyBillTestList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private HospitalBillInformation hospitalBillInformation;

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
        hospitalBillInformation = billDatum.getHospitalBillInformation();

        holder.cardViewPathology.setTag(position);
        holder.cardViewPathology.setOnClickListener(onClickListener);

        holder.tvHospitalName.setText(hospitalBillInformation.getHospitalName());
        Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(holder.imgHospital);
        holder.tvHospitalLocation.setText(hospitalBillInformation.getHospitalStreetName());

        holder.tvInvoiceNumber.setText(billDatum.getBillInvoice());
        holder.tvTotalAmount.setText(billDatum.getBillAmount());
        holder.tvDiscount.setText(billDatum.getBillDiscount());
        holder.tvBillGenerateDAte.setText(billDatum.getBillCreatedDate());
        String strBillType = billDatum.getBillType();
        if (strBillType.equals("0")) {
            strBillType = "Paid";
        } else {
            strBillType = "Due";
        }
        holder.tvBillType.setText(strBillType);
        String strBillStatus = billDatum.getBillStatus();
        if (strBillStatus.equals("0")) {
            strBillStatus = "Unpaid";
        } else if (strBillStatus.equals("1")) {
            strBillStatus = "Paid";
        } else if (strBillStatus.equals("2")) {
            strBillStatus = "Uncomplete";
        } else {
            strBillStatus = "Cancel bill by hospital";
        }
        holder.tvBillStatus.setText(strBillStatus);
        holder.tvBIllGstNUmber.setText(billDatum.getBillGstNumber());

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
        private TextView tvInvoiceNumber, tvHospitalName, tvHospitalLocation;
        public TextView tvBillGenerateDAte, tvBIllGstNUmber, tvBillStatus, tvBillType, tvTotalAmount, tvDiscount;
        public ImageView imgHospital;
        private CardView cardViewPathology;

        public MyViewHolder(View view) {
            super(view);
            cardViewPathology = view.findViewById(R.id.cardViewPathology);
            imgHospital = view.findViewById(R.id.imgHospital);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvHospitalLocation = view.findViewById(R.id.tvHospitalLocation);
            tvInvoiceNumber = view.findViewById(R.id.tvInvoiceNumber);
            tvBillGenerateDAte = view.findViewById(R.id.tvBillGenerateDAte);
            tvBIllGstNUmber = view.findViewById(R.id.tvBIllGstNUmber);
            tvBillStatus = view.findViewById(R.id.tvBillStatus);
            tvBillType = view.findViewById(R.id.tvBillType);
            tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
            tvDiscount = view.findViewById(R.id.tvDiscount);
        }
    }

}
