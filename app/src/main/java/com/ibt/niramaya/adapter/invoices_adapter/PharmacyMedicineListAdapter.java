package com.ibt.niramaya.adapter.invoices_adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.HospitalBillInformation;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyBillMedicine;
import com.ibt.niramaya.ui.activity.invoice_data.PharmacyMedicineBillActivity;

import java.util.List;

public class PharmacyMedicineListAdapter extends RecyclerView.Adapter<PharmacyMedicineListAdapter.MyViewHolder> {

    private List<PharmacyBillMedicine> billMedicineList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private HospitalBillInformation hospitalBillInformation;

    public PharmacyMedicineListAdapter(List<PharmacyBillMedicine> billMedicineList, Context mContext) {
        this.billMedicineList = billMedicineList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.hospitalBillInformation = hospitalBillInformation;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pharmcy_medicine, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PharmacyBillMedicine medicine = billMedicineList.get(position);

     /*   Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(holder.imgHospital);
        holder.tvHospitalName.setText(hospitalBillInformation.getHospitalName());
        holder.tvHospitalLocation.setText(hospitalBillInformation.getHospitalStreetName());*/

        holder.tvMedicineName.setText(medicine.getMedicineName());
        holder.tvCompanyName.setText(medicine.getMedicineCompanyName());
        holder.tvExpireDate.setText(medicine.getMedicineExpireDate());
        holder.tvMedicineMrp.setText(medicine.getMedicineMrp());
        holder.tvMedicineQuantity.setText(medicine.getMedicineQuantity());
        holder.tvMedicineAmount.setText(medicine.getMedicineAmount());

    /*     "pharmacy_bill_medicine_id": "3",
                    "medicine_name": "OPD created successfull",
                    "medicine_id": "1",
                    "medicine_quantity": "2",
                    "medicine_mrp": "20",
                    "medicine_amount": "20",
                    "medicine_company_name": "20",
                    "medicine_expire_date": "2019-12-14",
                    "medicine_quantity_type": "2",
                    "pharmacy_bill_medicine_created_date": "2019-04-10 19:45:16",*/

    }

    @Override
    public int getItemCount() {
        return billMedicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMedicineName, tvExpireDate, tvCompanyName, tvMedicineMrp, tvMedicineQuantity, tvMedicineAmount, tvHospitalName, tvHospitalLocation;
        private ImageView imgHospital;

        public MyViewHolder(View view) {
            super(view);

            tvMedicineName = view.findViewById(R.id.tvMedicineName);
            tvExpireDate = view.findViewById(R.id.tvExpireDate);
            tvCompanyName = view.findViewById(R.id.tvCompanyName);
            tvMedicineMrp = view.findViewById(R.id.tvMedicineMrp);
            tvMedicineQuantity = view.findViewById(R.id.tvMedicineQuantity);
            tvMedicineAmount = view.findViewById(R.id.tvAmount);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvHospitalLocation = view.findViewById(R.id.tvHospitalLocation);
            imgHospital = view.findViewById(R.id.imgHospital);
        }
    }

}
