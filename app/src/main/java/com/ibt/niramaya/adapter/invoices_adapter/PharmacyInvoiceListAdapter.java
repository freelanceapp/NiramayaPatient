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
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.BillDatum;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyBillMedicine;

import java.util.List;

public class PharmacyInvoiceListAdapter extends RecyclerView.Adapter<PharmacyInvoiceListAdapter.MyViewHolder> {

    private List<BillDatum> billMedicineList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private HospitalBillInformation hospitalBillInformation;

    public PharmacyInvoiceListAdapter(List<BillDatum> billMedicineList, Context mContext, View.OnClickListener onClickListener) {
        this.billMedicineList = billMedicineList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pharmacy_invoice_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BillDatum billDatum = billMedicineList.get(position);
        hospitalBillInformation = billDatum.getHospitalBillInformation();

        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        holder.tvHospitalName.setText(hospitalBillInformation.getHospitalName());
        Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(holder.imgHospital);
        holder.tvHospitalLocation.setText(hospitalBillInformation.getHospitalStreetName());

   /*     holder.tvMedicineName.setText(pharmacyBillMedicine.getMedicineName());
        holder.tvMedicineCategoryName.setText(pharmacyBillMedicine.getMedicineCompanyName());
        holder.tvMedicineExpireDate.setText(pharmacyBillMedicine.getMedicineExpireDate());
        holder.tvTotalAmount.setText(pharmacyBillMedicine.getMedicineAmount());*/



    /*    {
            "pharmacy_bill_medicine_id": "3",
                "medicine_name": "OPD created successfull",
                "medicine_id": "1",
                "medicine_quantity": "2",
                "medicine_mrp": "20",
                "medicine_amount": "20",
                "medicine_company_name": "20",
                "medicine_expire_date": "2019-12-14",
                "medicine_quantity_type": "2",
                "pharmacy_bill_medicine_created_date": "2019-04-10 19:45:16"
        }*/

    }

    @Override
    public int getItemCount() {
        return billMedicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHospitalName, tvHospitalLocation, tvMedicineName, tvMedicineCategoryName, tvMedicineExpireDate, tvMedicineQuantity, tvTotalAmount, tvPaidAmount, tvDueAmount;
        public ImageView imgHospital;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            tvHospitalLocation = view.findViewById(R.id.tvHospitalLocation);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvMedicineName = view.findViewById(R.id.tvMedicineName);
            tvMedicineCategoryName = view.findViewById(R.id.tvMedicineCategoryName);
            tvMedicineExpireDate = view.findViewById(R.id.tvMedicineExpireDate);
            tvMedicineQuantity = view.findViewById(R.id.tvMedicineQuantity);
            tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
            tvPaidAmount = view.findViewById(R.id.tvPaidAmount);
            tvDueAmount = view.findViewById(R.id.tvDueAmount);
            imgHospital = view.findViewById(R.id.imgHospital);
        }
    }

}
