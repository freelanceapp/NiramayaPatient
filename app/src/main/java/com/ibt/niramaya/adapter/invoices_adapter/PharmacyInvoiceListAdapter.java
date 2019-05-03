package com.ibt.niramaya.adapter.invoices_adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

public class PharmacyInvoiceListAdapter extends RecyclerView.Adapter<PharmacyInvoiceListAdapter.MyViewHolder> {
    private PharmacyMedicineListAdapter medicineListAdapter;
    private List<BillDatum> billMedicineList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private HospitalBillInformation hospitalBillInformation;
    private List<PharmacyBillMedicine> billMedicines = new ArrayList<>();

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

        holder.cardPharmacyBill.setTag(position);
        holder.cardPharmacyBill.setOnClickListener(onClickListener);

/*        holder.tvHospitalName.setText(hospitalBillInformation.getHospitalName());
        Glide.with(mContext).load(hospitalBillInformation.getHospialLogo()).into(holder.imgHospital);
        holder.tvHospitalLocation.setText(hospitalBillInformation.getHospitalStreetName());*/

        holder.tvTotalAmount.setText(billDatum.getBillAmount());
        holder.tvDiscountAmount.setText(billDatum.getBillAmount());
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
        //  holder.tvBIllGstNUmber.setText(billDatum.getBillGstNumber());

       /* billMedicines = (List<PharmacyBillMedicine>) billMedicineList.get(position).getPharmacyBillMedicine();

        holder.recyclerViewInvoice.setHasFixedSize(true);
        holder.recyclerViewInvoice.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        medicineListAdapter = new PharmacyMedicineListAdapter(billMedicines, mContext);
        holder.recyclerViewInvoice.setAdapter(medicineListAdapter);
        medicineListAdapter.notifyDataSetChanged();*/

   /*     holder.tvMedicineName.setText(pharmacyBillMedicine.getMedicineName());
        holder.tvMedicineCategoryName.setText(pharmacyBillMedicine.getMedicineCompanyName());
        holder.tvMedicineExpireDate.setText(pharmacyBillMedicine.getMedicineExpireDate());
        holder.tvTotalAmount.setText(pharmacyBillMedicine.getMedicineAmount());*/



    /*    "bill_id": "2",
            "bill_invoice": "",
            "bill_patient_id": "1",
            "user_id": "1",
            "patient_name": "Hitesh KUmawat",
            "patient_age": "22",
            "patient_gender": "Male",
            "opd_id": "1",
            "bill_type": "0",
            "bill_amount": "120",
            "bill_discount": "20",
            "bill_hospital_id": "1",
            "bill_gst_number": "ABCD1234ASBCD12",
            "gst": "12",
            "bill_status": "0",
            "bill_created_date": "2019-04-10 19:45:16",*/

    }

    @Override
    public int getItemCount() {
        return billMedicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHospitalName, tvHospitalLocation, tvDiscountAmount, tvBillType, tvBillGenerateDAte, tvBillStatus, tvTotalAmount, tvBIllGstNUmber;
        public ImageView imgHospital;
        private CardView cardPharmacyBill;
        private RecyclerView recyclerViewInvoice;

        public MyViewHolder(View view) {
            super(view);
            cardPharmacyBill = view.findViewById(R.id.cardPharmacyBill);
            //   tvHospitalLocation = view.findViewById(R.id.tvHospitalLocation);
            // tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvDiscountAmount = view.findViewById(R.id.tvDiscountAmount);
            tvBillGenerateDAte = view.findViewById(R.id.tvBillGenerateDAte);
            tvBillType = view.findViewById(R.id.tvBillType);
            //      tvBIllGstNUmber = view.findViewById(R.id.tvBIllGstNUmber);
            tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
            tvBillStatus = view.findViewById(R.id.tvBillStatus);
          /*  imgHospital = view.findViewById(R.id.imgHospital);
            recyclerViewInvoice = view.findViewById(R.id.recylerMecidine);*/
        }
    }

}
